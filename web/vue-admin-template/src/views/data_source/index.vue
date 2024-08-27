<template>
  <div class="app-container">
    <el-table
      :data="list"
      style="width: 100%">
      <el-table-column prop="id" label="ID" width="180"></el-table-column>
      <el-table-column prop="name" label="数据源名称" width="180"></el-table-column>
      <el-table-column prop="type" label="数据源类型"></el-table-column>
      <el-table-column prop="status" label="数据源状态"></el-table-column>
    </el-table>
  </div>
</template>

<script>

export default {
  data() {
    return {
      list: []
    }
  },
  methods: {
    getList(){

    },
    onSubmit() {
      this.$message('submit!')
    },
    onCancel() {
      this.$message({
        message: 'cancel!',
        type: 'warning'
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

