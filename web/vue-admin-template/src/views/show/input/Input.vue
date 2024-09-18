<script>
export default {
  data() {
    return {
      inputValue: this.info.options["default"] || "",
      name: this.info.name || "",
    }
  },
  created() {
    console.log(this.info);
  },
  props:{
    info:Object
  }
}
</script>

<template>
  <div>
    <span>{{name}}</span>
    <el-input v-model="inputValue" placeholder="请输入内容"></el-input>
  </div>

</template>

<style scoped lang="scss">

</style>
