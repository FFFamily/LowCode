<template>
  <div class="app-container">
    <el-button @click="dataSourceDialogVisible = true">添加</el-button>
    <el-table :data="list" style="width: 100%">
      <el-table-column prop="id" label="ID" width="180"></el-table-column>
      <el-table-column prop="name" label="数据源名称" width="180"></el-table-column>
      <el-table-column prop="type" label="数据源类型"></el-table-column>
      <el-table-column prop="status" label="数据源状态"></el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template v-slot="scope">
          <el-button @click="goToFieldPage(scope.row.id)" type="text" size="small">配置</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-dialog
        title="创建数据源"
        :visible.sync="dataSourceDialogVisible"
        width="30%"
        :before-close="handleClose">
        <el-form ref="form" :model="coreDataSourceForm" label-width="90px">
          <el-form-item label="数据源名称">
            <el-input v-model="coreDataSourceForm.name"></el-input>
          </el-form-item>
          <el-form-item label="数据源类型">
            <el-input v-model="coreDataSourceForm.type"></el-input>
          </el-form-item>
          <el-form-item label="URL">
            <el-input v-model="coreDataSourceForm.url"></el-input>
          </el-form-item>
          <el-form-item label="username">
            <el-input v-model="coreDataSourceForm.username"></el-input>
          </el-form-item>
          <el-form-item label="password">
            <el-input v-model="coreDataSourceForm.password"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-input v-model="coreDataSourceForm.status"></el-input>
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
import { getDataSourceList, saveDataSource } from '@/api/data_source/dataSource'

export default {
  data() {
    return {
      list: [],
      dataSourceDialogVisible: false,
      coreDataSourceForm: {}
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
    goToFieldPage(id) {
      this.$router.push({ name: 'Field', params: { data: id }})
    },
    onSubmit() {
      saveDataSource(this.coreDataSourceForm).then(response => {
        this.dataSourceDialogVisible = false
        this.coreDataSourceForm = {}
        this.$message({
          message: '创建成功',
          type: 'success'
        })
        this.getList()
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

