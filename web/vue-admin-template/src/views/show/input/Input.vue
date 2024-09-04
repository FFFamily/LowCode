<script>
export default {
  data() {
    return {
      input: this.info.a
    }
  },
  props:{
    info:Object
  }
}
</script>

<template>
  <el-input v-model="input" placeholder="请输入内容"></el-input>
</template>

<style scoped lang="scss">

</style>
