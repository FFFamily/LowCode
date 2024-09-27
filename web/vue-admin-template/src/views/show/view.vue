<template>
  <div class="login-container">
    <!--内容区域-->
    <el-form ref="form" :model="submitInfo" label-width="80px">
      <el-form-item v-for="item in schemaList" :label="item.name">
        <component  @modelValue="changeFetch($event,item.code)" :is='item.options["x-component"]' :info="item"></component>
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
import Select from "@/views/show/other/Select.vue";
import Upload from "@/views/show/other/Upload.vue";
import DatePicker from "@/views/show/other/DatePicker.vue";
import RelatedQuery from "@/views/show/other/RelatedQuery.vue";
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
    FormDataSelect,
    Select,
    Upload,
    DatePicker,
    RelatedQuery
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
          if (table.type === "child"){
            this.schemaList = this.schemaList.concat({
              ...table,
              code:table.tableName,
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
          // console.log(item.options["x-component"]);
        })
      })
      config(this.formId,"view_page").then(response => {
        this.buttonSchemaList = response.data[0].options || []
      })
    },
    changeFetch(val,data){
      console.log(val);
      console.log(data)
      this.submitInfo[data] = val
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
      console.log(this.schemaList)
      console.log(this.submitInfo)
    }
  }
}
</script>
