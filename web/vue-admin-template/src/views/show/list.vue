<template>
  <div>
      <el-table :data="tableList">
          <el-table-column v-for="item in showConfig" :prop="item.fieldCode" :key="item.fieldCode" :label="item.fieldName" ></el-table-column>
      </el-table>
  </div>
</template>

<script >
import {list,config} from '@/api/show'

export default {
  data() {
    return {
      formId:"3f9d4d2d90c138e83858db0ea560454c",
      type:"list_page",
      tableName:'uuu',
      config:{},
      showConfig: [],
      tableList: []
    }
  },
  watch: {

  },
  created() {
    this.getTableConfig()
    this.getTable()
  },
  methods: {
    getTableConfig(){
        config(this.formId,this.type).then(response => {
          this.config = response.data
          for(var i=0;i<response.data.length;i++){
            if(response.data[i].type === "show"){
              this.showConfig = JSON.parse(response.data[i].options)
            }
          }
        })
    },
    getTable(){
      list(this.tableName).then(response => {
        this.tableList = response.data
        console.log(this.tableList)
      })
      console.log(this.tableList)
    }
  }
}
</script>

<style scoped lang="scss">

</style>
