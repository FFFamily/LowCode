<template>
  <div class="app-container">
    <el-button @click="dataSourceDialogVisible = true">添加</el-button>
    <el-table :data="list" style="width: 100%">
      <el-table-column prop="dsName" label="数据源名称"></el-table-column>
      <el-table-column prop="name" label="表名"></el-table-column>
      <el-table-column prop="type" label="数据源类型"></el-table-column>
      <el-table-column prop="url" label="数据源地址"></el-table-column>
      <el-table-column prop="username" label="账号"></el-table-column>
      <el-table-column prop="password" label="密码"></el-table-column>
    </el-table>
    <div>
      <el-dialog
        title="创建表单"
        :visible.sync="dataSourceDialogVisible"
        width="50%"
        :before-close="handleClose">
        <el-form ref="form" :model="coreDataSource" label-width="90px">
          <el-form-item label="数据源名称">
            <el-input v-model="coreDataSource.dsName"></el-input>
          </el-form-item>
          <el-form-item label="表名">
            <el-input v-model="coreDataSource.name"></el-input>
          </el-form-item>
          <el-form-item label="数据源类型">
              <el-select v-model="coreDataSource.type" placeholder="请选择">
                <el-option key="mysql" label="Mysql" value="mysql"></el-option>
              </el-select>
          </el-form-item>
          <el-form-item label="数据源地址">
            <el-input v-model="coreDataSource.url"></el-input>
          </el-form-item>
          <el-form-item label="账号">
            <el-input v-model="coreDataSource.username"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="coreDataSource.password"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="onSubmit">立即创建</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import {getDataSourceList,saveDataSource} from '@/api/data_source/dataSource'
export default {
  data() {
    return {
      list: [],
      dataSourceDialogVisible: false,
      coreDataSource: {}
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      getDataSourceList().then(response => {
        this.list = response.data
      })
    },
    onSubmit() {
      console.log(this.coreDataSource)
      saveDataSource(this.coreDataSource).then(response => {
        this.dataSourceDialogVisible = false
        this.getList()
        this.$message({
          message: '创建成功',
          type: 'success'
        })
      })
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

