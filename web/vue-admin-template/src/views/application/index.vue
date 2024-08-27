<template>
  <div class="app-container">
    <el-button type="success" @click="openCreateApplicationDrawer">添加应用</el-button>
    <br><br>
    <template>
      <el-table
        v-loading="listLoading"
        :data="list"
        style="width: 100%"
      >
        <el-table-column prop="name" label="姓名" width="180"></el-table-column>
        <el-table-column prop="code" label="编码" width="180"></el-table-column>
        <el-table-column prop="type" label="类型"></el-table-column>
      </el-table>
    </template>
    <el-drawer
      :before-close="handleClose"
      :visible.sync="createApplicationDrawer"
      title="应用嘻嘻">
      <el-form :label-position="labelPosition" label-width="80px" :model="applicationFrom">
        <el-form-item label="编码">
          <el-input v-model="applicationFrom.code"></el-input>
        </el-form-item>
        <el-form-item label="应用名称">
          <el-input v-model="applicationFrom.name"></el-input>
        </el-form-item>
        <el-form-item label="分类">
          <el-input v-model="applicationFrom.type"/>
        </el-form-item>
        <el-button type="success" @click="createApplication">添加应用</el-button>
      </el-form>
    </el-drawer>
  </div>
</template>

<script>
import {list, saveApplication} from '@/api/application'

export default {
  data() {
    return {
      list: null,
      listLoading: true,
      createApplicationDrawer: false,
      applicationFrom: {}
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      list().then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {
        })
    },
    openCreateApplicationDrawer() {
      this.createApplicationDrawer = true
    },
    createApplication() {
      saveApplication(this.applicationFrom).then(response => {
        this.createApplicationDrawer = false
        this.applicationFrom = {}
        this.fetchData()
      })
    }
  }
}
</script>
