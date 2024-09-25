<template>
  <div class="login-container">
    <!--内容区域-->
    <el-form ref="form" :model="submitInfo" label-width="80px">
      <el-form-item v-for="item in schemaList" :label="item.name">
        <component v-model ="item.inputValue" :is='item.options["x-component"]' :info="item"></component>
      </el-form-item>
    </el-form>
    <!--底部按钮区域-->
    <el-button v-for="item in buttonSchemaList" @click = "buttonAction(item.buttonType)">{{item.name}}</el-button>
  </div>
</template>

<script>
import {getFormInfo} from '@/api/form'
import {config} from '@/api/show'
import {getRuleByType} from '@/api/form_rule/formRule'
import Input from '../show/input/Input.vue'
import Input_Number from '../show/input/Input_Number.vue'
import DataSourceSelect from '../show/other/DataSourceSelect.vue'
import Table from '../show/other/Table.vue'
import StaticText  from "../show/other/StaticText.vue";
import FormDataSelect from "@/views/show/other/FormDataSelect.vue";
export default {
  data() {
    return {
      formId : this.$route.params.formId,
      form:{},
      submitInfo:{},
      schemaList: [],
      // 按钮
      buttonSchemaList:[]
    }
  },
  components: {
    Input,
    Input_Number,
    DataSourceSelect,
    Table,
    StaticText,
    FormDataSelect
  },
  watch: {},
  created() {
    if(this.formId){
      this.getFormShowRule()
      this.getFormInfo()
    }else{
      this.$router.push({ name: 'Show'})
    }
  },
  methods: {
    getFormInfo() {
      getFormInfo(this.formId).then(response => {
        this.form = response.data
        Object.keys(response.data.fields).forEach(field => {
          let table =  response.data.formTables.filter(_ => _.id === field)[0];
          // console.log("对应的表为： ");
          // console.log(table)
          if (table.type === "child"){
            this.schemaList = this.schemaList.concat({
              ...table,
              options: "{\"x-component\": \"Table\"}"
            })
          }else {
            let item = response.data.fields[field];
            if (item){
              this.schemaList = this.schemaList.concat(item)
            }
          }
        })
        this.schemaList.forEach(item => {
          item.options = JSON.parse(item.options)
          console.log(item.options["x-component"]);
        })
      })
      config(this.formId,"view_page").then(response => {
        this.buttonSchemaList = response.data
      })
    },
    getFormShowRule(){
      getRuleByType(this.formId,"show").then(response => {
        let showRules = response.data
        showRules.forEach(item => {
          let {fieldId,calculationSymbols,value} = JSON.parse(item.preConditionNode)
        })
      })
    },
    buttonAction(item){

    }
  }
}
</script>
