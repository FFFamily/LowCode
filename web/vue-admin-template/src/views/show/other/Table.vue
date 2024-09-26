<script>
import {list} from '@/api/show'
import {getTableById} from '@/api/formTable'
import Input from '@/views/show/input/Input.vue'
import Input_Number from '@/views/show/input/Input_Number.vue'
import DataSourceSelect from '@/views/show/other/DataSourceSelect.vue'
import Table from '@/views/show/other/Table.vue'
import StaticText  from "@/views/show/other/StaticText.vue";
import FormDataSelect from "@/views/show/other/FormDataSelect.vue";
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
    console.log("========明细表=========")
    console.log(this.info)
    console.log("========明细表=========")
    this.getChildTable()
    this.getTableInfo()
  },
  props:{
    info:Object
  },
  components: {
    Input,
    Input_Number,
    DataSourceSelect,
    Table,
    StaticText,
    FormDataSelect
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
    },
    getTableInfo(){
      let tableId = this.info.id
      getTableById(tableId).then(response => {
          let fields = response.data.fields
          fields.forEach(item => {
            this.tableColumns.push({
              columnProp: item.code,
              columnLabel: item.name,
              options: JSON.parse(item.options)
            })
          })
      })
    },
    addData(){
      this.tableData.push({
        indexValue: this.tableData.length
      })
    },
    delRow(row){
      this.tableData = this.tableData.filter(item => item.indexValue !== row.indexValue)
    },
    print(){
      console.log((this.tableData))
    }
  }
}
</script>

<template>
  <div>
    <template>
      <el-button @click="addData" type="text" size="small">添加行</el-button>
      <el-button @click="print" type="text" size="small">打印</el-button>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column v-for="(item) in tableColumns" :prop="item.columnProp" :label="item.columnLabel" >
          <template v-slot="scope">
            <component v-model ="scope.row[item.columnProp]" :is='item.options["x-component"]' :info="item"></component>
          </template>
        </el-table-column>
        <el-table-column label="操作" >
          <template v-slot="scope">
            <el-button @click="delRow(scope.row)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>
  </div>

</template>

<style scoped lang="scss">

</style>
