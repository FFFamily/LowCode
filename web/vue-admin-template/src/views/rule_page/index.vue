<template>
  <div class="app-container">
    <el-tabs  v-model="activeName" class="demo-tabs" @tab-click="handleClick">
      <el-tab-pane label="校验规则" name="check"></el-tab-pane>
      <el-tab-pane label="显示规则" name="show">
        <el-button @click="addShowRule" type="text" size="small">添加规则</el-button>
        <template>
          <el-table :data="ruleList" style="width: 100%">
            <el-table-column label="规则名称" >
              <template v-slot="scope">
                <el-input v-model = "scope.row.name"></el-input>
              </template>
            </el-table-column>
            <el-table-column label="前置条件" >
              <template v-slot="scope">
                <el-input v-model = "scope.row.preConditionNode"></el-input>
              </template>
            </el-table-column>
            <el-table-column label="执行动作" >
              <template v-slot="scope">
                <el-input v-model = "scope.row.action"></el-input>
              </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作">
              <template v-slot="scope">
                <el-button v-if="scope.row.id === undefined" @click="createShowRule(scope.row)" type="text" size="small">保存</el-button>
              </template>
            </el-table-column>
          </el-table>
        </template>
      </el-tab-pane>
      <el-tab-pane label="必填规则" name="required"></el-tab-pane>
      <el-tab-pane label="计算规则" name="calculate"></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import {getRuleByType,addRule} from '@/api/form_rule/formRule'
  export default {
    data() {
      return {
        // 当前标签页
        activeName: 'show',
        // 显示规则配置
        ruleList:[],
        tableForm: {},
        dialogVisible : false,
        formId : this.$route.params.data,
        dataSourceTableDialogVisible: false
      }
    },
    created() {
      if(this.formId){
        this.getRules(this.formId)
      }else{
        this.$router.push({ name: 'DataSourceField'})
      }
    },
    methods: {
      addShowRule(){
        this.ruleList.push({
          formId: this.formId
        })
      },
      handleClick(tab,event) {
        this.activeName = tab
      },
      createShowRule(data){
        addRule(this.activeName,data).then(response => {
          this.getRules(this.formId)
        })
      },
      getRules(formId) {
        getRuleByType(formId,this.activeName).then(response => {
          this.ruleList = response.data
        })
      },
    }
  }
</script>

<style scoped lang="scss">

</style>
