<script>
export default {
  data() {
    return {
      inputValue: this.info.options["default"]
    }
  },
  props:{
    info:Object
  }
}
</script>

<template>
  <el-input v-model="inputValue" placeholder="请输入内容"></el-input>
</template>

<style scoped lang="scss">

</style>
