<template>
  <div>
    <p>当前表单：{{ $route.params.data }}</p>
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
      width="30%"
      :before-close="handleClose">
      <el-form ref="form" :model="formConditionActionInfo" label-width="80px">
        <el-form-item label="条件名称">
          <el-input v-model="formConditionActionInfo.name"></el-input>
        </el-form-item>
        <el-form-item label="场景类型">
          <el-radio-group v-model="formConditionActionInfo.resource" @input="changeRadio">
            <el-radio label="data">数据触发</el-radio>
          </el-radio-group>
        </el-form-item>
        <template v-if="formConditionActionInfo.resource === 'data'">
          <el-form-item label="触发类型">
            <el-radio-group v-model="formConditionActionInfo.cdConfig" @input="changeRadio">
              <el-radio label="insert">新增数据时</el-radio>
              <el-radio label="update">更新数据时</el-radio>
              <el-radio label="delete">删除数据时</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="源表单">
              <el-select v-model="formConditionActionInfo.region" placeholder="请选择监听表单">
                <el-option label="区域一" value="shanghai"></el-option>
              </el-select>
          </el-form-item>

          <template>
            <el-table
              :data="formConditionActionInfo.tableData"
              style="width: 100%">
              <el-table-column
                prop="date"
                label="日期"
                width="180">
              </el-table-column>
            </el-table>
          </template>
        </template>
        <el-form-item>
          <el-button type="primary" @click="onSubmit" >立即创建</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
import {getView,addView} from "@/api/form";
import {all} from "@/api/action";
export default {
  data() {
    return {
      formId : this.$route.params.data,
      formConditionActionInfo:{},
      viewForm:[],
      dialogVisible: false
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
      })
    },
    editField(id){
      this.$router.push({ name: 'ViewConfig', params: { data: id,formId:this.formId }})
    },
    changeRadio(radioValue){
      console.log(radioValue)
    },
    addFormCondition(){
      this.dialogVisible = true
    },
    onSubmit(viewForm){
      addView(viewForm).then(response => {
        this.getFormView(this.formId)
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
