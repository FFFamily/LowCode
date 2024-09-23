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
import Input from '../show/input/Input.vue'
import Input_Number from '../show/input/Input_Number.vue'
import DataSourceSelect from '../show/other/DataSourceSelect.vue'
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
    DataSourceSelect
  },
  watch: {},
  created() {
    if(this.formId){
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
          let item = response.data.fields[field];
          if (item){
            this.schemaList = this.schemaList.concat(item)
          }
        })
        this.schemaList.forEach(item => {
          item.options = JSON.parse(item.options)
        })
      })
      config(this.formId,"view_page").then(response => {
        this.buttonSchemaList = response.data
      })
    },
    buttonAction(item){

    }
  }
}
</script>
