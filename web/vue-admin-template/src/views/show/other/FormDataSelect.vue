<script>
import {list} from "@/api/show"
import {getTableById} from '@/api/formTable'
import {MethodParser, hasMethod, ArgParser} from '@/utils/FormulaParser'
export default {
  data() {
    return {
      inputValue: this.info.options["default"] || "",
      dataSourceFrom: this.info.options["business"].dataSourceFrom,
      dataSourceDialogVisible: false,
      list:[],
      columns:[],
      reload: false,
      dynamicProps: this.info.options["x-component-props"],
      selectionRow:[]
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
    onSubmit(){
      this.dataSourceDialogVisible = false
      let method = this.dataSourceFrom.fromBackValue;
      this.inputValue = hasMethod(method) ? MethodParser(method,this.selectionRow) : this.selectionRow[0][ArgParser[method]];
    },
    handleSelectionChange(selection){
      this.selectionRow = selection
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
          <el-table :data="list" style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column v-bind="dynamicProps" v-if="dynamicProps"></el-table-column>
            <el-table-column  v-for="item in columns" :prop="item.propColumn" :label="item.labelColumn"></el-table-column>
          </el-table>
          <el-button @click="onSubmit">选择</el-button>
        </template>
      </el-dialog>
    </template>
  </div>
</template>
