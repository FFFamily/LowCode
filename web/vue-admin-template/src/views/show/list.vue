<template>
  <div>
    <el-button v-for="item in showButtonConfig" @click = "buttonAction(item.buttonType)">{{item.buttonName}}</el-button>
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
      formId:"f7f70be639bc6557f43ebafe844d3809",
      type:"list_page",
      tableName:'user',
      // 视图配置
      config:{},
      showConfig: [],
      showButtonConfig:[],
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
            }else if(response.data[i].type === "button"){
              this.showButtonConfig = JSON.parse(response.data[i].options)
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
    },
    buttonAction(buttonType){
      if(buttonType === "add"){
        console.log("新增")
        this.$router.push({ name: 'VIEW', params: { formId: this.formId }})
      }else if(buttonType === "del"){
        console.log("删除")
      }
    }
  }
}
</script>

<style scoped lang="scss">

</style>
