<template>
  <div>
    <el-table :data="viewFormConfigList" >
      <el-table-column label="视图配置类型">
        <template v-slot="scope">
          <span v-if="scope.row.id !== undefined">{{ scope.row.type }}</span>
          <el-input v-else type="text" v-model="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="名称">
        <template v-slot="scope">
          <span v-if="scope.row.id !== undefined">{{ scope.row.name }}</span>
          <el-input v-else type="text" v-model="scope.row.name"/>
        </template>
      </el-table-column>
      <el-table-column prop="systemType" label="类型" />
      <el-table-column prop="isShow" label="是否展示" />
      <el-table-column prop="showConfig" label="显示规则" />
      <el-table-column fixed="right" label="操作" width="100">
        <template v-slot="scope">
          <el-button @click="showConfigRadio(scope.row)" type="text" size="small">配置</el-button>
          <el-button v-if="scope.row.id === undefined" @click="onSubmit(scope.row)" type="text" size="small">保存</el-button>
        </template>
      </el-table-column>
    </el-table>

      <el-drawer :visible.sync="columnShowConfigRadio" title="显示列表配置" :with-header="true">
<!--        {{columnShowFieldList}}-->
          <el-table :data="columnShowFieldList" style="width: 100%">
            <el-table-column prop="name" label="字段名称" ></el-table-column>
            <el-table-column prop="name" label="是否展示" >
              <template v-slot="scope">
                <el-switch v-model="scope.row.isShow" class="ml-2" style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"/>
              </template>
            </el-table-column>
          </el-table>
        <el-button @click="saveConfig()">保存</el-button>
      </el-drawer>


    <el-drawer :visible.sync="listButtonConfigRadio" title="列表按钮配置" :with-header="true">
      <!--        {{columnShowFieldList}}-->
      <span>目前按钮配置都是假配置</span>
      <el-table :data="buttonShowFieldList" style="width: 100%">
        <el-table-column prop="buttonName" label="按钮名称" ></el-table-column>
        <el-table-column prop="name" label="是否展示" >
          <template v-slot="scope">
            <el-switch v-model="scope.row.isShow" class="ml-2" style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"/>
          </template>
        </el-table-column>
      </el-table>
      <el-button @click="saveButtonConfig()">保存</el-button>
    </el-drawer>
  </div>
</template>
<script>
import {addView,getFormInfo} from "@/api/form";
import {getViewConfig,updateViewConfig} from "@/api/view";
export default {
  data() {
    return {
      viewFormConfigListId : this.$route.params.data,
      formId : this.$route.params.formId,
      viewFormConfigList:[],
      // 按钮配置
      buttonShowFieldList:[],
      // 列表渲染配置页面开关
      columnShowConfigRadio:false,
      // 列表按钮配置页面开关
      listButtonConfigRadio:false,
      columnShowFieldList:[],
      viewFormConfig:{}
    }
  },
  created(){
    if(this.viewFormConfigListId){
      this.getFormView(this.viewFormConfigListId)
    }else{
      this.$router.push({ name: 'DataSourceField'})
    }
  },
  methods: {
    getFormView(viewFormConfigListId){
      getViewConfig(viewFormConfigListId).then(response => {
        this.viewFormConfigList = response.data;
      })
    },
    goViewConfigPage(viewId){

    },
    saveConfig(){
      let options = [];
      this.columnShowFieldList.forEach(item=>{
        if(item.isShow){
          options.push({
            fieldCode:item.code,
            fieldName:item.name,
          })
        }
      })
      if (options.length > 0){
        this.viewFormConfig.options = JSON.stringify(options)
      }
      console.log(this.viewFormConfig)
      updateViewConfig(this.viewFormConfig).then(response => {
          alert("保存成功")
      })
    },
    saveButtonConfig(){
      let options = [];
      this.buttonShowFieldList.forEach(item=>{
        if(item.isShow){
          options.push({
            buttonName:item.buttonName,
            buttonType:item.buttonType,
          })
        }
      })
      if (options.length > 0){
        this.viewFormConfig.options = JSON.stringify(options)
      }
      updateViewConfig(this.viewFormConfig).then(response => {
        alert("保存成功")
      })
    },
    showConfigRadio(viewConfig){
      if (viewConfig.type === "list_show"){
        this.columnShowFieldList = [];
        let filedList = viewConfig.options ? JSON.parse(viewConfig.options).map(item=> item.fieldCode) :[];
        getFormInfo(this.formId).then(response => {
          Object.keys(response.data.fields).forEach(_ =>{
            response.data.fields[_].forEach(field=>{
              if (filedList.includes(field.code)){
                this.columnShowFieldList.push({
                  ...field,
                  isShow: true,
                })
              }else {
                this.columnShowFieldList.push(field)
              }
            })
          })
          this.columnShowConfigRadio = true;
        })
      }else if(viewConfig.type === "list_button"){
        this.buttonShowFieldList = []
        this.buttonShowFieldList = this.buttonShowFieldList.concat([
          {
            buttonName:"新增",
            buttonType:"add"
          },
          {
            buttonName:"删除",
            buttonType:"del"
          }
        ])
        this.listButtonConfigRadio = true
      }else if(viewConfig.type === "detail_button"){
        this.buttonShowFieldList = []
        this.buttonShowFieldList = this.buttonShowFieldList.concat([
          {
            buttonName:"保存",
            buttonType:"save"
          }
        ])
        this.listButtonConfigRadio = true
      }
      this.viewFormConfig = viewConfig;
    },
    addView(){
      this.viewFormConfigList.push({
        systemType:"custom",
        viewFormConfigListId:this.viewFormConfigListId
      })
    },
    onSubmit(viewFormConfigList){
      addView(viewFormConfigList).then(response => {
        this.getFormView(this.viewFormConfigListId)
      })
    }
  }
}
</script>
<style scoped lang="scss">

</style>
