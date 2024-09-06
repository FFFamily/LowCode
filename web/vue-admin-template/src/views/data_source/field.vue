<template>
  <div class="app-container">
    <p>接收到的参数值：{{ $route.params.data }}</p>
    <el-dropdown @command="handleCommand">
      <el-button type="primary">
        添加数据表字段<i class="el-icon-arrow-down el-icon--right"></i>
      </el-button>
      <el-dropdown-menu>
        <el-dropdown-item command="input">文本</el-dropdown-item>
        <el-dropdown-item command="number">数值</el-dropdown-item>
        <el-dropdown-item command="radio">单选框</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <el-table :data="list" style="width: 100%">

      <el-table-column prop="id" label="ID" width="180"></el-table-column>
      <el-table-column prop="name" label="字段名称" width="180"></el-table-column>
      <el-table-column prop="type" label="字段组件类型" width="180"></el-table-column>
      <el-table-column prop="fieldName" label="字段标识"></el-table-column>
      <el-table-column prop="fieldType" label="字段类型"></el-table-column>
      <el-table-column prop="fieldComment" label="字段描述"></el-table-column>
      <el-table-column prop="fieldDefault" label="字段默认值"></el-table-column>
      <el-table-column prop="fieldLength" label="字段长度"></el-table-column>
      <el-table-column prop="fieldIndex" label="字段索引"></el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template v-slot="scope">
          <el-button @click="editField(scope.row.id)" type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="添加表字段"
      :visible.sync="dataSourceFieldDialogVisible"
      width="30%"
      :before-close="handleClose">
      <el-form ref="form" :model="fieldForm" label-width="90px">
        <el-form-item v-for="item in fieldFormHtml.html" :label="item.label" >
            <el-input v-if="item.type === 'input'"  v-model=item.propValue></el-input>
            <el-switch v-else-if="item.type === 'switch'" v-model=item.propValue class="ml-2" style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"/>
            <el-input-number  v-else-if="item.type === 'number'" v-model=item.propValue />
            <div v-else-if="item.type === 'input.list'">
              <el-form
                ref="formRef"
                style="max-width: 600px"
                label-width="auto"
                class="demo-dynamic">
                <el-form-item
                  v-for="(enumValue, index) in item.propValue"
                  :label="'枚举'">
                  <el-input v-model="enumValue.value" />
                  <el-button class="mt-2" @click.prevent="removeDomain(enumValue,item.propValue)">
                    Delete
                  </el-button>
                </el-form-item>
                <el-form-item>
                  <el-button @click="addDomain(item.propValue)">新增枚举</el-button>
                </el-form-item>
              </el-form>
            </div>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即创建</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import {getDataSourceFieldList,saveDataSourceField} from "@/api/data_source/dataSourceField";
  import {generateTableFieldInfo} from "@/utils/fieldFormBuild";
  const arrayToObject = (keys, values) => keys.reduce((obj, key, index) => {
    obj[key] = values[index];
    return obj;
  }, {});
  export default {
    data() {
      return {
        list: [],
        fieldForm: {},
        fieldFormHtml: {},
        tableId : this.$route.params.data,
        dataSourceFieldDialogVisible: false
      }
    },
    created() {
      if(this.tableId){
        this.getList(this.tableId)
      }else{
        this.$router.push({ name: 'DataSource'})
      }
    },
    methods: {
      editField(id) {

      },
      getList(sourceId) {
        getDataSourceFieldList(sourceId).then(response => {
          this.list = response.data
        })
      },
       removeDomain(item,arr)  {
        const index = arr.indexOf(item)
        if (index !== -1) {
          arr.splice(index, 1)
        }
      },

       addDomain (arr)  {
         arr.push({
          key: Date.now(),
          value: '',
        })
      },
      onSubmit(){
        this.fieldForm.tableId = this.tableId
        // console.log(this.fieldFormHtml)
        const obj = arrayToObject(this.fieldFormHtml.html.map(_ => _.prop),this.fieldFormHtml.html.map(_ => _.propValue))
        let form = {
          ...this.fieldForm,
          ...this.fieldFormHtml.formData,
          ...obj
        }
        console.log(form)
        saveDataSourceField(form).then(response => {
          this.dataSourceFieldDialogVisible = false
          this.fieldForm = {}
          this.getList(this.tableId);
        })
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      handleCommand(command) {
        this.dataSourceFieldDialogVisible = true
        this.fieldFormHtml = generateTableFieldInfo(command)
        console.log(this.fieldFormHtml)
      }
    }
  }
</script>

<style scoped lang="scss">

</style>
