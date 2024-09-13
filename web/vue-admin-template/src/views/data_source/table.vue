<template>
  <div class="app-container">
    <p>当前表单：{{ $route.params.data }}</p>
    <el-button @click="releaseForm" type="text" size="small">发布</el-button>
    <el-button @click="opendialogVisible" type="text" size="small">添加表</el-button>


<!--    <el-button @click="dataSourceTableDialogVisible = true">添加数据表</el-button>-->
<!--    <el-table :data="formInfo" style="width: 100%">-->
<!--      <el-table-column prop="id" label="ID" width="180"></el-table-column>-->
<!--      <el-table-column prop="name" label="数据源名称" width="180"></el-table-column>-->
<!--      <el-table-column prop="type" label="数据源类型"></el-table-column>-->
<!--      <el-table-column prop="status" label="数据源状态"></el-table-column>-->
<!--      <el-table-column fixed="right" label="操作" width="100">-->
<!--        <template v-slot="scope">-->
<!--          <el-button @click="goToFieldPage(scope.row.id)" type="text" size="small">配置字段</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->
<!--    <el-dialog-->
<!--      title="添加数据表"-->
<!--      :visible.sync="dataSourceTableDialogVisible"-->
<!--      width="30%"-->
<!--      :before-close="handleClose">-->
<!--      <el-form ref="form" :model="tableForm" label-width="90px">-->
<!--        <el-form-item label="数据库名称">-->
<!--          <el-input v-model="tableForm.name"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="数据库类型">-->
<!--          <el-input v-model="tableForm.type"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="数据库标识">-->
<!--          <el-input v-model="tableForm.tableName"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item>-->
<!--          <el-button type="primary" @click="onSubmit">立即创建</el-button>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--    </el-dialog>-->
    <el-tabs  class="demo-tabs" @tab-click="handleClick">
      <el-tab-pane label="表单信息" name="first">{{ formInfo.form }}</el-tab-pane>
      <el-tab-pane label="数据模型" name="second">
        <el-table :data="formInfo.formTables" style="width: 100%">
          <el-table-column type="expand">
            <template #default="props">
              <el-table :data="props.row.fields" :border="childBorder">
                <el-table-column label="字段编码" prop="name" />
                <el-table-column label="类型" prop="state" />
                <el-table-column label="名称" prop="city" />
              </el-table>
            </template>
          </el-table-column>
          <el-table-column prop="name" label="表名" width="180" />
          <el-table-column prop="tableName" label="编码" width="180" />
          <el-table-column prop="type" label="类型" />
          <el-table-column fixed="right" label="操作" width="100">
            <template v-slot="scope">
              <el-button type="text" size="small">配置</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="表单设计" name="third">表单设计</el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import {saveDataSourceTable} from "@/api/data_source/dataSourceTable";
  import {getFormInfo,release} from '@/api/form'
  export default {
    data() {
      return {
        formInfo: {},
        tableForm: {},
        dialogVisible : false,
        formId : this.$route.params.data,
        dataSourceTableDialogVisible: false
      }
    },
    created() {
      if(this.formId){
        this.getformInfo(this.formId)
      }else{
        this.$router.push({ name: 'DataSourceField'})
      }

    },
    methods: {
      goToFieldPage(id) {
        this.$router.push({ name: 'DataSourceField', params: { data: id }})
      },
      opendialogVisible(){
        this.dialogVisible = true
        console.log(this.dialogVisible)
      },
      handleClick(tab,event) {
        console.log(tab, event)
      },
      releaseForm(){
        release(this.formInfo).then(response => {
          alert(response)
        });
      },
      getformInfo(formId) {
        getFormInfo(formId).then(response => {
          this.formInfo = response.data
        })
      },
      onSubmit(){
        this.tableForm.codeDataformId = this.formId
        saveDataSourceTable(this.tableForm).then(response => {
          this.dataSourceTableDialogVisible = false
          this.tableForm = {}
          this.getformInfo(this.formId);
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
