<template>
  <div class="app-container">
    <el-button @click="dataSourceDialogVisible = true">添加</el-button>
    <el-table :data="list" style="width: 100%">
      <el-table-column prop="name" label="表单名称" width="180"></el-table-column>
      <el-table-column prop="type" label="表单类型"></el-table-column>
      <el-table-column fixed="right" label="操作">
        <template v-slot="scope">
          <el-button @click="goToTablePage(scope.row.id)" type="text" size="small">表单设计</el-button>
          <el-button @click="goToTablePage(scope.row.id)" type="text" size="small">表单配置</el-button>
          <el-button @click="goToActionPage(scope.row.id)" type="text" size="small">动作配置</el-button>
          <el-button @click="goViewConfigPage(scope.row.id)" type="text" size="small">视图配置</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-dialog
        title="创建表单"
        :visible.sync="dataSourceDialogVisible"
        width="50%"
        :before-close="handleClose">
        <el-form ref="form" :model="coreDataSourceForm" label-width="90px">
          <el-form-item label="表单名称">
            <el-input v-model="coreDataSourceForm.form.name"></el-input>
          </el-form-item>
          <el-form-item label="表单编码">
            <el-input v-model="coreDataSourceForm.formTables.tableName"></el-input>
          </el-form-item>
          <el-form-item label="表单类型">
              <el-select v-model="coreDataSourceForm.form.type" placeholder="请选择">
                <el-option key="normal" label="普通表单" value="normal"></el-option>
              </el-select>
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
import {all,create,release} from '@/api/form'
export default {
  data() {
    return {
      list: [],
      dataSourceDialogVisible: false,
      coreDataSourceForm: {
        form:{},
        formTables:{}
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      all().then(response => {
        this.list = response.data
      })
    },
    goToTablePage(id) {
      this.$router.push({ name: 'DataSourceTable', params: { data: id }})
    },
    goViewConfigPage(id){
      this.$router.push({ name: 'ViewForm', params: { data: id }})
    },
    goToActionPage(id){
      this.$router.push({ name: 'FormAction', params: { data: id }})
    },
    onSubmit() {
      let table = this.coreDataSourceForm.formTables
      this.coreDataSourceForm.formTables = [table]
      console.log(this.coreDataSourceForm)
      create(this.coreDataSourceForm).then(response => {
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

