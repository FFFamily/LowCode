<script>
import {find} from '@/api/show'
export default {
  data() {
    return {
      queryParam: this.info.options["business"] || "",
      inputValue:""
    }
  },
  created() {
    // console.log(this.info);
    find(this.queryParam).then(response => {
      this.inputValue = response.data
    })

  },
  props:{
    info:Object
  }
}
</script>

<template>
  <div>
    <el-input v-model="inputValue" :disabled="true"></el-input>
  </div>

</template>

<style scoped lang="scss">

</style>
