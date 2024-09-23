<template>
  <div class="app-container">
    <el-button @click="dataSourceConfigDialogVisible = true">添加</el-button>
    <el-table :data="list" style="width: 100%">
      <el-table-column prop="name" label="配置名称"></el-table-column>
      <el-table-column prop="sql" label="过滤SQL"></el-table-column>
    </el-table>
    <div>
      <el-dialog
        title="创建表单"
        :visible.sync="dataSourceConfigDialogVisible"
        width="50%"
        :before-close="handleClose">
        <el-form ref="form" :model="coreDataSourceConfig" label-width="90px">
          <el-form-item label="表名">
            <el-input v-model="coreDataSourceConfig.config.name"></el-input>
          </el-form-item>
          <el-form-item label="数据源">
              <el-select v-model="coreDataSourceConfig.dataSourceIdList" multiple  placeholder="请选择">
                <el-option v-for="item in dataSourceList" :key="item.id" :label="item.dsName" :value="item.id"></el-option>
              </el-select>
          </el-form-item>
          <el-form-item label="过滤SQL">
            <el-input v-model="coreDataSourceConfig.config.sqlStatement"></el-input>
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
import {getDataSourceList,getDataSourceConfigList,saveDataSourceConfig} from '@/api/data_source/dataSource'
export default {
  data() {
    return {
      list: [],
      dataSourceList:[],
      dataSourceConfigDialogVisible: false,
      coreDataSourceConfig: {
        config:{},
        dataSourceIdList: []
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      getDataSourceConfigList().then(response => {
        this.list = response.data
      })
      getDataSourceList().then(response => {
        this.dataSourceList = response.data
      })
    },
    onSubmit() {
      saveDataSourceConfig(this.coreDataSourceConfig).then(response => {
        this.dataSourceConfigDialogVisible = false
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

