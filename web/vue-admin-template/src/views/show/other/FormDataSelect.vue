<script>
import {list} from "@/api/show"
import {getTableById} from '@/api/formTable'
export default {
  data() {
    return {
      inputValue: this.info.options["default"] || "",
      dataSourceFrom: this.info.options["business"].dataSourceFrom,
      dataSourceDialogVisible: false,
      list:[],
      columns:[],
      reload: false,
      dynamicProps: this.info.options["x-component-props"]
    }
  },
  created() {
    console.log("========= 表单关联 ===========");
    console.log(this.info);
  },
  props:{
    info:Object
  },
  methods:{
    search(){
      this.dataSourceDialogVisible = true
      if (!this.reload){
        if (this.dataSourceFrom.fromType === 'formTableId'){
          getTableById(this.dataSourceFrom.fromTarget).then(res => {
            console.log(res)
            let fields = res.data.fields;
            fields.forEach(item => {
              this.columns.push({
                propColumn:item.code,
                labelColumn: item.name
              })
            })
            let tableName = res.data.table.tableName;
            list(tableName).then(res => {
              this.list = res.data
            })
          })
        }
        this.reload = true;
      }else{
        getTableById(this.dataSourceFrom.fromTarget).then(res => {
          let tableName = res.data.table.tableName;
          list(tableName).then(res => {
            this.list = res.data
          })
        })
      }
    },
    RowClick(row,column,event){
      this.dataSourceDialogVisible = false
      console.log(row[this.dataSourceFrom.fromBackValue])
      this.inputValue = row.id
    }
  }
}
</script>

<template>
  <div>
    <template>
      <el-select v-model="inputValue" placeholder="请选择" @focus=search></el-select>
    </template>
    <template>
      <el-dialog title="提示" :visible.sync="dataSourceDialogVisible" width="70%">
        <template>
          <el-table :data="list" style="width: 100%" @row-click="RowClick">
            <el-table-column v-bind="dynamicProps" v-if="dynamicProps"></el-table-column>
            <el-table-column  v-for="item in columns" :prop="item.propColumn" :label="item.labelColumn"></el-table-column>
          </el-table>
          <el-button>选择</el-button>
        </template>
      </el-dialog>
    </template>
  </div>
</template>
