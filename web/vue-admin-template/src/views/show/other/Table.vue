<script>
import {list} from '@/api/show'
export default {
  data() {
    return {
      inputValue: this.info.options["default"] || "",
      name: this.info.name || "",
      tableName: this.info.tableName || "",
      tableData:[],
      tableColumns:[]
    }
  },
  created() {
    console.log(this.info)
    this.getChildTable()
  },
  props:{
    info:Object
  },
  methods:{
    getChildTable(){
      list(this.tableName).then(res => {
        this.tableData = res.data
        if (this.tableData.length > 0){
           Object.keys(this.tableData[0]).forEach(key => {
             this.tableColumns.push({
               columnProp: key,
               columnLabel: key
             })
           })
        }
      })
    }
  }
}
</script>

<template>
  <div>
<!--    <span>{{name}}</span>-->
<!--    <el-input v-model="inputValue" placeholder="请输入内容"></el-input>-->
    <template>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column v-for="item in tableColumns" :prop="item.columnProp" :label="item.columnLabel" width="180"></el-table-column>
        <el-table-column label="操作" width="180">
          <template v-slot="scope">
            <el-button type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>

  </div>

</template>

<style scoped lang="scss">

</style>
