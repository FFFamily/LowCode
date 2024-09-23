<template>
  <div class="app-container">
    <el-button @click="releaseForm" type="text" size="small">发布</el-button>
    <el-button @click="opendialogVisible" type="text" size="small">添加表</el-button>
    <el-tabs  v-model="activeName" class="demo-tabs" @tab-click="handleClick">
      <el-tab-pane label="表单信息" name="first">{{ formInfo.form }}</el-tab-pane>
      <el-tab-pane label="数据模型" name="second">
        <el-table :data="formInfo.formTables" style="width: 100%">
          <el-table-column type="expand">
            <template  v-slot="scope">
              <el-table :data="scope.row.fields" :border="true">
                <el-table-column label="组件编码"  >
                  <template v-slot="scope">
                    <el-input type="text" v-model="scope.row.code"/>
                  </template>
                </el-table-column>
                <el-table-column label="组件名称" prop="name" >
                  <template v-slot="scope">
                    <el-input type="text" v-model="scope.row.name"/>
                  </template>
                </el-table-column>
                <el-table-column label="组件类型"  >
                  <template v-slot="scope">
                    <el-input type="text" v-model="scope.row.interfaceType"/>
                  </template>
                </el-table-column>
                <el-table-column label="字段类型"  >
                  <template v-slot="scope">
                    <el-input :disabled="true" type="text" v-model="scope.row.type"/>
                  </template>
                </el-table-column>
                <el-table-column label="字段状态"  >
                  <template v-slot="scope">
                    <el-input :disabled="true" type="text" v-model="scope.row.status"/>
                  </template>
                </el-table-column>
                <el-table-column label="字段配置"  >
                  <template v-slot="scope">
                    <el-input type="text" v-model="scope.row.options"/>
                  </template>
                </el-table-column>
              </el-table>
            </template>
          </el-table-column>
          <el-table-column prop="name" label="表名" width="180" />
          <el-table-column prop="tableName" label="编码" width="180" />
          <el-table-column prop="type" label="类型" />
          <el-table-column fixed="right" label="操作" width="100">
            <template v-slot="scope">
              <el-button @click="addField(scope.row)" type="text" size="small">添加字段</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="表单设计" name="third">表单设计</el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import {saveDataSourceTable} from "@/api/data_source/dataSourceTable";
  import {getFormInfo,release} from '@/api/form'
  import {map} from "core-js/internals/array-iteration";
  export default {
    data() {
      return {
        // 当前标签页
        activeName: 'second',
        formInfo: {},
        tableForm: {},
        dialogVisible : false,
        formId : this.$route.params.data,
        dataSourceTableDialogVisible: false
      }
    },
    created() {
      if(this.formId){
        this.getFormInfo(this.formId)
      }else{
        this.$router.push({ name: 'DataSourceField'})
      }
    },
    methods: {
      addField(table){
        this.$nextTick(() => {
          table.fields?.push({
            name:"name",
            code:"code",
            interfaceType:"Input",
            type:"String",
            formId:table.formId,
            formTableId:table.id,
            status:"created",
            options:"{\"x-component\":\"Input\"}"
          })
        })
      },
      opendialogVisible(){
        this.dialogVisible = true
      },
      handleClick(tab,event) {

      },
      releaseForm(){
        let fields = {}
        this.formInfo.formTables.forEach(item => {
          fields[item.id] = item.fields
        })
        this.formInfo.fields = fields
        release({...this.formInfo}).then(response => {
          this.$message.success("发布成功")
          this.getFormInfo(this.formId)
        });
      },
      getFormInfo(formId) {
        getFormInfo(formId).then(response => {
          this.formInfo = response.data
          let mapper = this.formInfo.fields
          this.formInfo.formTables.forEach(item => {
            if (mapper[item.id]){
              item.fields = mapper[item.id]
            }else {
              item.fields = item.fields || [];
            }
          })
        })
      },
      onSubmit(){
        this.tableForm.codeDataformId = this.formId
        saveDataSourceTable(this.tableForm).then(response => {
          this.dataSourceTableDialogVisible = false
          this.tableForm = {}
          this.getformInfo(this.formId);
        })
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      }
    }
  }
</script>

<style scoped lang="scss">

</style>
