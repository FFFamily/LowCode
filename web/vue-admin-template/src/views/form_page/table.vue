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
                    <el-select @change="interfaceTypeChange($event, scope.row)" :disabled="scope.row.status === 'published'" v-model="scope.row.interfaceType" placeholder="请选择">
                      <el-option label="文本" value="input"></el-option>
                      <el-option label="下拉选择" value="select"></el-option>
                      <el-option label="文件上传" value="upload"></el-option>
                      <el-option label="时间" value="date_picker"></el-option>
                      <el-option label="文字" value="text"></el-option>
                      <el-option label="数据源选择" value="data_source_select"></el-option>
                      <el-option label="表单关联" value="form_data_select"></el-option>
                      <el-option label="关联查询" value="related_query"></el-option>
                    </el-select>
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
          <el-table-column prop="name" label="表名" width="180" >
            <template v-slot="scope">
              <el-input v-if="scope.row.id === undefined" type="text" v-model="scope.row.name"/>
              <span v-else>{{scope.row.name}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="tableName" label="编码" width="180" >
            <template v-slot="scope">
              <el-input v-if="scope.row.id === undefined" type="text" v-model="scope.row.tableName"/>
              <span v-else>{{scope.row.tableName}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="type" label="类型" >
            <template v-slot="scope">
              <el-input v-if="scope.row.id === undefined" type="text" v-model="scope.row.type"/>
              <span v-else>{{scope.row.type}}</span>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template v-slot="scope">
              <el-button v-if="scope.row.id !== undefined" @click="addField(scope.row)" type="text" size="small">添加字段</el-button>
              <el-button v-else @click="createChildTable(scope.row)" type="text" size="small">生成明细表</el-button>
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
  import {createChildTable} from '@/api/formTable'
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
      interfaceTypeChange(value,data){
        console.log(data)
        console.log(value)
        if (value === 'input'){
          data.options ="{\"x-component\":\"Input\"}"
        }else if(value === 'select'){
          data.options = '{\"x-component\":\"Select\",\"items\":[{\"label\":\"选项1\",\"value\":\"1\"}]}';
        }else if(value === 'upload'){
          data.options ="{\"x-component\":\"Upload\"}"
        }else if(value === 'date_picker'){
          data.options ="{\"x-component\":\"DatePicker\"}"
        }else if(value === 'text'){
          data.options ="{\"x-component\":\"StaticText\",\"default\":\"我是一个描述\"}"
        }else if(value === 'data_source_select'){

        }else if(value === 'form_data_select'){
          data.options = `{\"x-component\":\"FormDataSelect\",\"x-component-props\":{\"type\":\"selection\"},\"business\":{\"dataSourceFrom\":{\"fromType\":\"formTableId\",\"fromTarget\":\"${data.formTableId}\",\"fromBackValue\":\"#sum($id$)#\"}}}`;
        }else if (value === 'related_query'){
          data.options = `{\"x-component\":\"RelatedQuery\",\"business\":{\"tableName\":\"form\",\"tableFiled\":[\"count(name)\"],\"filterCondition\":[{\"field\":\"name\",\"operator\":\"=\",\"value\":\"\\\"公司\\\"\"}]}}`
        }
      },
      opendialogVisible(){
        this.dialogVisible = true
        this.formInfo.formTables.push({
          formId: this.formId
        })
      },
      createChildTable(data){
        console.log(data)
        createChildTable(data).then(res => {
          this.getFormInfo(this.formId)
        })
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
