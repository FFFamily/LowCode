<template>
  <div>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%">
      <el-form :inline="true" :model="selectForm" class="demo-form-inline">
        <el-form-item label="表单">
          <el-select v-model="selectForm.formId" @change="selectChange" placeholder="已发布的表单">
            <el-option v-for="item in formList" :label="item.name" :value="item.id" ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="showList">查询</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-button v-for="item in showButtonConfig" @click = "buttonAction(item.buttonType)">{{item.buttonName}}</el-button>
      <el-table :data="tableList">
          <el-table-column v-for="item in showConfig" :prop="item.fieldCode" :key="item.fieldCode" :label="item.fieldName" ></el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template v-slot="scope">
              <el-button @click="edit(scope.row)" type="text" size="small">查看</el-button>
            </template>
          </el-table-column>
      </el-table>
  </div>
</template>

<script >
import {list,config} from '@/api/show'
import {all,getFormInfo} from '@/api/form'
export default {
  data() {
    return {
      // 视图配置
      config:{},
      showConfig: [],
      showButtonConfig:[],
      tableList: [],
      dialogVisible: true,
      selectForm:{
        type:"list_page"
      },
      formList:[]
    }
  },
  watch: {

  },
  created() {
    this.getAllForm()
  },
  methods: {
    showList(){
      this.dialogVisible = false
      this.getTableConfig()
      this.getTable()
    },
    edit(data){
      this.$router.push({ name: 'VIEW', params: { formId: this.selectForm.formId }})
    },
    getAllForm(){
      all().then(response => {
        this.formList = response.data
      })
    },
    selectChange(formId){
      this.selectForm.formId = formId;
      getFormInfo(formId).then(response => {
        this.selectForm.tableName = response.data.formTables[0].tableName
      })
    },
    getTableConfig(){
        config(this.selectForm.formId,this.selectForm.type).then(response => {
          this.config = response.data
          for(var i=0;i<response.data.length;i++){
            if(response.data[i].type === "list_show"){
              this.showConfig = JSON.parse(response.data[i].options)
              console.log( this.showConfig)
            }else if(response.data[i].type === "list_button"){
              this.showButtonConfig = JSON.parse(response.data[i].options)
            }
          }
        })
    },
    getTable(){
      list(this.selectForm.tableName).then(response => {
        this.tableList = response.data
      })
    },
    buttonAction(buttonType){
      if(buttonType === "add"){
        console.log("新增")
        this.$router.push({ name: 'VIEW', params: { formId: this.formId }})
      }else if(buttonType === "del"){
        console.log("删除")
      }
    }
  }
}
</script>

<style scoped lang="scss">

</style>
