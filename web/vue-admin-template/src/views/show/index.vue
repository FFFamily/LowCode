<template>
  <div class="login-container">
    <component  v-for="item in schemaList" :is='inpp' :info="item"></component>
  </div>
</template>

<script>
import {getDataSourceFieldList} from '@/api/data_source/dataSourceField.js'
import Input from '../show/input/Input.vue'
const sourceId = 0;
const tableId = 2;
export default {
  data() {
    return {
      schemaList:[],
      inpp:'Input'
    }
  },
  components:{
    Input
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
        console.log(this.schemaList)
      })
    },
    test(){
      return h('div', [h('span', 'no')])
    }
  }
}
</script>
