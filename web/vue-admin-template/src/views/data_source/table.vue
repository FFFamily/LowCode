<template>
  <div class="app-container">
    <p>接收到的参数值：{{ $route.params.data }}</p>
    <el-button @click="dataSourceTableDialogVisible = true">添加数据表</el-button>
    <el-table :data="list" style="width: 100%">
      <el-table-column prop="id" label="ID" width="180"></el-table-column>
      <el-table-column prop="name" label="数据源名称" width="180"></el-table-column>
      <el-table-column prop="type" label="数据源类型"></el-table-column>
      <el-table-column prop="status" label="数据源状态"></el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template v-slot="scope">
          <el-button @click="goToFieldPage(scope.row.id)" type="text" size="small">配置字段</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="添加数据表"
      :visible.sync="dataSourceTableDialogVisible"
      width="30%"
      :before-close="handleClose">
      <el-form ref="form" :model="tableForm" label-width="90px">
        <el-form-item label="数据库名称">
          <el-input v-model="tableForm.name"></el-input>
        </el-form-item>
        <el-form-item label="数据库类型">
          <el-input v-model="tableForm.type"></el-input>
        </el-form-item>
        <el-form-item label="数据库标识">
          <el-input v-model="tableForm.tableName"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即创建</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import {getDataSourceTableList,saveDataSourceTable} from "@/api/data_source/dataSourceTable";
  export default {
    data() {
      return {
        list: [],
        tableForm: {},
        sourceId : this.$route.params.data,
        dataSourceTableDialogVisible: false
      }
    },
    created() {
      if(this.sourceId){
        this.getList(this.sourceId)
      }else{
        this.$router.push({ name: 'DataSourceField'})
      }

    },
    methods: {
      goToFieldPage(id) {
        this.$router.push({ name: 'DataSourceField', params: { data: id }})
      },
      getList(sourceId) {
        getDataSourceTableList(sourceId).then(response => {
          this.list = response.data
        })
      },
      onSubmit(){
        this.tableForm.codeDataSourceId = this.sourceId
        saveDataSourceTable(this.tableForm).then(response => {
          this.dataSourceTableDialogVisible = false
          this.tableForm = {}
          this.getList(this.sourceId);
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
