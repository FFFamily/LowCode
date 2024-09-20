<template>
  <div class="app-container">
    <el-button  @click="addView">新增视图</el-button>
    <el-table :data="viewForm" >
      <el-table-column label="视图类型">
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
      <el-table-column prop="systemType" label="配置类型" />
      <el-table-column fixed="right" label="操作" width="100">
        <template v-slot="scope">
          <el-button v-if="scope.row.id !== undefined" @click="editField(scope.row.id)" type="text" size="small">配置</el-button>
          <el-button v-if="scope.row.id === undefined" @click="onSubmit(scope.row)" type="text" size="small">保存</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import {viewList,addViewForm} from "@/api/view";
export default {
  data() {
    return {
      formId : this.$route.params.data,
      viewForm:[]
    }
  },
  created(){
    if(this.formId){
      this.getFormView(this.formId)
    }else{
      this.$router.push({ name: 'DataSourceField'})
    }
  },
  methods: {
    getFormView(formId){
      viewList(formId).then(response => {
        this.viewForm = response.data;
      }).catch(_ => {
        this.$router.push({ name: 'DataSourceField'})
      })
    },
    editField(id){
      this.$router.push({ name: 'ViewConfig', params: { data: id,formId:this.formId }})
    },
    addView(){
      this.viewForm.push({
        systemType:"custom",
        formId:this.formId
      })
    },
    onSubmit(viewForm){
      addViewForm(viewForm).then(response => {
        this.getFormView(this.formId)
      })
    }
  }
}
</script>
<style scoped lang="scss">

</style>
