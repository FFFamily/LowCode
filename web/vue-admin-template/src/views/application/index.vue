<template>
  <div class="app-container">
    <template>
      <el-button @click="fetchData">生成</el-button>
      <el-table v-loading="listLoading" :data="list" style="width: 100%">
        <el-table-column prop="id" label="Id" ></el-table-column>
        <el-table-column v-for="item in listConfig" :prop="item" :label="item" ></el-table-column>
        <el-table-column fixed="right" label="操作" width="100">
          <template v-slot="scope">
            <el-button @click="edit(scope.row)" type="text" size="small">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>
  </div>
</template>

<script>
import {list} from '@/api/salary'

export default {
  data() {
    return {
      list: null,
      listConfig: [],
      listLoading: true,
      createApplicationDrawer: false,
      applicationFrom: {}
    }
  },
  created() {
    // this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      list().then(response => {

        if (response.data.length > 0) {
          let item = response.data[0]
          let data = JSON.parse(item.data)
          this.listConfig = data.map(_ => _.fieldName)
          console.log(this.listConfig)
        }

        let res = response.data.map(_ => {
          let data = JSON.parse(_.data) ;
          delete _.data
          return data.reduce((acc, item) => {
            acc[item.fieldName] = item.fieldValue;
            return acc;
          }, {..._})
        })
        console.log(res);
        this.list = res
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
