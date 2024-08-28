<template>
  <div class="app-container">
    <p>接收到的参数值：{{ $route.params.data }}</p>
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
  </div>
</template>

<script>
  import {getDataSourceTableList} from "@/api/data_source/dataSourceTable";
  export default {
    data() {
      return {
        list: [],
        sourceId : this.$route.params.data
      }
    },
    created() {
      this.getList(this.sourceId)
    },
    methods: {
      goToFieldPage(id) {

      },
      getList(sourceId) {
        getDataSourceTableList(sourceId).then(response => {
          this.list = response.data
        })
      }
    }
  }
</script>

<style scoped lang="scss">

</style>
