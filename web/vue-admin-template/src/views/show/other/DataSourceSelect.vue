<script>
import {list} from "@/api/show"
export default {
  data() {
    return {
      inputValue: this.info.options["default"] || "",
      dataSourceFrom: this.info.options["business"].dataSourceFrom,
      name: this.info.name || "",
      dataSourceDialogVisible: false,
      dataSourceDatatable:[],
      dataSourceDatatableColumns:[]
    }
  },
  created() {
    // console.log("========= 数据源");
    // console.log(this.info);
  },
  props:{
    info:Object
  },
  methods:{
    search(){
      console.log(this.info.options["business"].dataSourceFrom);
      this.dataSourceDialogVisible = true
      list(this.dataSourceFrom).then(res => {
        this.dataSourceDatatable = res.data
        if (this.dataSourceDatatable.length > 0){
          let item  = this.dataSourceDatatable[0];
          Object.keys(item).forEach(key => {
            this.dataSourceDatatableColumns.push({
              propColumn: key,
              labelColumn: key
            })
          })
        }else {
          console.log("素组为空")
        }
      })
    },
    RowClick(row,column,event){
      console.log(row)
      this.dataSourceDialogVisible = false
      this.inputValue = row.id
    }
  }
}
</script>

<template>
  <div>
    <template>
<!--      <el-input v-model="inputValue" placeholder="请输入内容"></el-input>-->
      <el-select v-model="inputValue" placeholder="请选择" @focus=search></el-select>
    </template>
    <template>
      <el-dialog title="提示" :visible.sync="dataSourceDialogVisible" width="50%">
        <template>
          <el-table :data="dataSourceDatatable" style="width: 100%" @row-click="RowClick">
            <el-table-column v-for="item in dataSourceDatatableColumns" :prop="item.propColumn" :label="item.labelColumn"></el-table-column>
          </el-table>
        </template>
      </el-dialog>
    </template>
  </div>
</template>
