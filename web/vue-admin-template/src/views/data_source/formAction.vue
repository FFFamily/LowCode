<template>
  <div>
    <p>当前表单：{{ formId }}</p>
    <p>配置信息：{{ viewForm }}</p>
    <el-button @click="addFormCondition">新增视图</el-button>
    <el-table :data="viewForm" >
      <el-table-column label="条件场景名称">
        <template v-slot="scope">
          <span v-if="scope.row.id !== undefined">{{ scope.row.cdName }}</span>
          <el-input v-else type="text" v-model="scope.row.cdName"/>
        </template>
      </el-table-column>
      <el-table-column label="动作类型">
        <template v-slot="scope">
          <span v-if="scope.row.id !== undefined">{{ scope.row.cdType }}</span>
          <el-input v-else type="text" v-model="scope.row.cdType"/>
        </template>
      </el-table-column>
      <el-table-column label="配置内容">
        <template v-slot="scope">
          <span v-if="scope.row.id !== undefined">{{ scope.row.cdConfig }}</span>
          <el-input v-else type="text" v-model="scope.row.cdConfig"/>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" >
        <template v-slot="scope">
          <el-button @click="editField(scope.row.id)" type="text" size="small">配置</el-button>
          <el-button v-if="scope.row.id === undefined" @click="onSubmit(scope.row)" type="text" size="small">保存</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="60%"
      :before-close="handleClose">
      <el-form ref="form" :model="formConditionActionInfo" label-width="80px">
        <el-form-item label="条件名称">
          <el-input v-model="formConditionActionInfo.formCondition.cdName"></el-input>
        </el-form-item>
        <el-form-item label="场景类型">
          <el-radio-group v-model="formConditionActionInfo.formCondition.cdType" @input="changeRadio">
            <el-radio label="data">数据触发</el-radio>
          </el-radio-group>
        </el-form-item>
        <template v-if="formConditionActionInfo.formCondition.cdType === 'data'">
          <el-form-item label="触发类型">
            <el-radio-group v-model="formConditionActionInfo.formCondition.cdConfig.trEvent">
              <el-radio label="insert">新增数据时</el-radio>
              <el-radio label="update">更新数据时</el-radio>
              <el-radio label="delete">删除数据时</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="源表单">
              <el-select v-model="formConditionActionInfo.formCondition.cdConfig.trTarget" placeholder="请选择监听表单">
                <el-option v-for="item in formList" :label="item.tableName" :value="item.tableName"></el-option>
              </el-select>
          </el-form-item>
          <el-button @click="actionDialogVisible = true">新增动作</el-button>
          <template>
            <el-table
              :data="formConditionActionInfo.formActions"
              style="width: 100%">
              <el-table-column prop="id" label="ID" width="180"></el-table-column>
              <el-table-column prop="acName" label="名称" width="180"></el-table-column>
              <el-table-column prop="acType" label="动作类型" width="180"></el-table-column>
              <el-table-column prop="acResource" label="源表单" width="180"></el-table-column>
              <el-table-column prop="acTarget" label="目标表单" width="180"></el-table-column>
              <el-table-column prop="acConfig" label="动作配置" width="180"></el-table-column>
            </el-table>
          </template>
        </template>
        <el-form-item>
          <el-button type="primary" @click="onSubmit" >立即创建</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>


    <el-dialog
      title="提示"
      :visible.sync="actionDialogVisible"
      width="30%"
      :before-close="handleClose">
      <el-form ref="form" :model="actionConfig" label-width="80px">
        <el-form-item label="名称">
          <el-input v-model="actionConfig.acName"></el-input>
        </el-form-item>
        <el-form-item label="动作类型">
          <el-input v-model="actionConfig.acType"></el-input>
        </el-form-item>
        <el-form-item label="目标表单">
          <el-input v-model="actionConfig.acTarget"></el-input>
        </el-form-item>
        <el-form-item label="动作配置">
          <el-input v-model="actionConfig.acConfig"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="createAction">创建动作</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>
<script>
import {getAllFormTable} from "@/api/formTable"
import {add} from '@/api/formAction'
import {all,addFormCondition} from "@/api/action";
import fa from "element-ui/src/locale/lang/fa";
export default {
  data() {
    return {
      formId : this.$route.params.data,
      formConditionActionInfo:{
        formCondition:{
          "formId": this.$route.params.data,
          cdEnable:"Y",
          cdConfig:{}
        },
        formActions:[]
      },
      viewForm:[],
      formList:[],
      dialogVisible: false,
      actionConfig:{
        acName:"动作1",
        acType:"insert",
        acResource:"",
        acTarget:"uuu",
        acConfig:JSON.stringify({
          "dataList": [
            {
              "fieldName": "id",
              "assignmentMode":"fix_value",
              "fieldValue": "11"
            }
          ]
        })
      },
      actionDialogVisible: false
    }
  },
  created(){
    if(this.formId){
      this.getFormCondition(this.formId)
    }else{
      this.$router.push({ name: 'DataSourceField'})
    }
  },
  methods: {
    getFormCondition(formId){
      all(formId).then(response => {
        this.viewForm = response.data;
      }).catch(_ => {
        this.$router.push({ name: 'DataSourceField'})
      })
    },
    editField(id){
      this.$router.push({ name: 'ViewConfig', params: { data: id,formId:this.formId }})
    },
    changeRadio(radioValue){
      getAllFormTable().then(response => {
        this.formList = response.data
      })
      console.log(radioValue)
    },
    createAction(){
      add(this.actionConfig).then(response => {
        this.actionDialogVisible = false
        this.formConditionActionInfo.formActions.push({
          id:response.data,
          ...this.actionConfig
        })
      })
    },
    addFormCondition(){
      this.dialogVisible = true
    },
    onSubmit(){
      this.formConditionActionInfo.formCondition.cdConfig = JSON.stringify(this.formConditionActionInfo.formCondition.cdConfig)
      console.log(this.formConditionActionInfo)
      addFormCondition(this.formConditionActionInfo).then(res => {
        this.dialogVisible = false
        this.getFormCondition(this.formId)
      })
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    }
  }
}
</script>
<style scoped lang="scss">

</style>
