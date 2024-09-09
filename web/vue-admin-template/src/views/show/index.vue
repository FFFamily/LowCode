<template>
  <div class="login-container">
    <component  v-for="item in schemaList" :is='item.options["x-component"]' :info="item"></component>
  </div>
</template>

<script>
import {getDataSourceFieldList} from '@/api/data_source/dataSourceField.js'
import Input from '../show/input/Input.vue'
import Input_Number from '../show/input/Input_Number.vue'
const sourceId = 0;
const tableId = 2;
export default {
  data() {
    return {
      schemaList:[]
    }
  },
  components:{
    Input,
    Input_Number
  },
  watch: {

  },
  created() {
    this.getTableField()
  },
  methods: {
    getTableField(){
      getDataSourceFieldList(tableId).then(response => {
        this.schemaList = response.data
        this.schemaList.forEach(item => {
          item.options = JSON.parse(item.options)
        })
        console.log(this.schemaList)
      })
    },
    test(){
      return h('div', [h('span', 'no')])
    }
  }
}
</script>
