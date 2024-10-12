<template>
  <div class="app-container">
    <el-button type="text" @click="createGroupDialogVisible = true">新增模块</el-button>
    <el-form  ref="dynamicValidateForm" label-width="100px" class="demo-dynamic">
      <div v-for="item in group">
        <el-divider content-position="left">{{ item.name }}</el-divider>
        <el-form-item
          v-for="(domain, index) in item.configs"
          :label="'配置' + index"
          :prop="'domains.' + index + '.value'">
          <el-input v-model="domain.name"></el-input>
          <!--        <el-input v-model="domain.type"></el-input>-->
          <el-select v-model="domain.type" placeholder="请选择">
            <el-option  label="固定值" value="fixedValue"></el-option>
            <el-option  label="手动录入" value="manualInput"></el-option>
            <el-option  label="公式计算" value="formula"></el-option>
            <el-option  label="数据映射" value="dataMapping"></el-option>
            <el-option  label="系统配置" value="systemConfig"></el-option>
          </el-select>
          <div  v-if="domain.type === 'dataMapping'">
            <el-select v-model="domain.otherPro.mappingTarget" @change="dataMappingChange($event,domain)"  placeholder="请选择" value="">
              <el-option  label="用户信息" value="user"></el-option>
            </el-select>
            <el-select v-model="domain.otherPro.mappingProperties" @change="dataMappingChange($event,domain)" placeholder="请选择" value="">
              <el-option  label="基本工资" value="money"></el-option>
            </el-select>
          </div>
          <el-input v-if="domain.type === 'fixedValue'" v-model="domain.value"></el-input>
          <div v-if="domain.type === 'formula'">
            <div class="buttons">
              <el-button @click="pushOperator('+',domain)">+</el-button>
              <el-button @click="pushOperator('-',domain)">-</el-button>
              <el-button @click="pushOperator('*',domain)">*</el-button>
              <el-button @click="pushOperator('/',domain)">/</el-button>
              <el-button @click="pushParam(12,domain)">数值</el-button>
<!--              <el-button v-for="(item,index) in dynamicValidateForm.configs" @click="pushParam(item.name,domain)">配置{{item.name}}</el-button>-->
              <span>公式：</span> <el-tag v-for="tag in domain.value">{{tag.formulaValue}}</el-tag>
            </div>
          </div>


<!--          <el-button @click.prevent="removeDomain(domain)">删除</el-button>-->
        </el-form-item>
        <el-button @click="addDomain(item,item.id)">新增配置</el-button>
<!--        <el-button @click="resetForm('dynamicValidateForm')">重置</el-button>-->
      </div>

        <el-button type="primary" @click="submitForm('dynamicValidateForm')">提交</el-button>

    </el-form>

    <el-dialog
      title="新增模块"
      :visible.sync="createGroupDialogVisible"
      width="30%"
      :before-close="handleClose">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="模块名称">
          <el-input v-model="formInline.name" placeholder="模块名称"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="createGroup">添加</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
import {addGroup} from "@/api/salary/salaryGroup"
import {addConfig,getConfig} from "@/api/salary/salaryConfig"
export default {
  data() {
    return {
      formInline:{},
      group:[
        {
          configs:[
            {
              value: {} || [],
              otherPro:{
                mappingTarget:"",
                mappingProperties:""
              }
            }
          ]
        }
      ],
      groupOrder:0,
      dialogVisible: false,
      createGroupDialogVisible: false
    }
  },
  created() {
    this.fetchConfig()
  },
  methods: {
    fetchConfig() {
      getConfig().then(res=>{
        res.data.salaryGroups.forEach(group=>{
          group.configs = []
          for (let i = 0; i < res.data.configs.length; i++) {
            let config = res.data.configs[i]
            // console.log(config)
            if (config.groupId === group.id){
              group.configs.push({
                ...config,
                otherPro:{}
              })
            }
          }
        })
        this.group = res.data.salaryGroups;
        // this.dynamicValidateForm.configs = res.data.configs
        console.log(this.group)
      })
    },
    dataMappingChange(value,domain){

    },
    createGroup(){
      this.formInline ={
        ...this.formInline,
        order:this.groupOrder
      }
      addGroup(this.formInline).then(res => {
        this.formInline = {}
        this.createGroupDialogVisible = false
      })
    },
    pushOperator(operator,domain){
      if (!domain.value){
        domain.value = []
      }
      domain.value.push({
        formulaType:"operator",
        formulaValue:operator,
      })
      console.log(domain)
    },
    pushParam(param,domain){
      if (!domain.value){
        domain.value = []
      }
      domain.value.push({
        formulaType:"param",
        formulaValue:param,
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // this.dynamicValidateForm.configs.forEach(item => {item.value = JSON.stringify(item.value)})

          let combinedArray = this.group.reduce((accumulator, currentValue) => {
            return accumulator.concat(currentValue.configs);
          }, []);
          let form = {
            configs: combinedArray
          };
          console.log(form)
          addConfig(form).then(res => {
              this.fetchConfig()
          })
        } else {
          return false;
        }
      });
    },
    // resetForm(formName) {
    //   this.$refs[formName].resetFields();
    // },
    // removeDomain(item) {
    //   var index = this.dynamicValidateForm.configs.indexOf(item)
    //   if (index !== -1) {
    //     this.dynamicValidateForm.configs.splice(index, 1)
    //   }
    // },
    addDomain(domainEntity,groupId) {
      if (!domainEntity.configs){
        domainEntity.configs = []
      }
      domainEntity.configs.push({
        value: '',
        groupId:groupId,
        key: Date.now()
      });
      console.log(this.group)
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
