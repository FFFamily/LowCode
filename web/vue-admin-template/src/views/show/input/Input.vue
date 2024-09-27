<script>
export default {
  data() {
    return {
      inputValue: this.info.options["default"] || "",
      name: this.info.name || "",
    }
  },
  created() {
    // console.log(this.info);
  },
  props:{
    info:Object
  },
  methods:{
    changeMsg(){
      this.$emit("modelValue",this.inputValue);
    }
  }
}
</script>

<template>
  <div>
    <el-input v-model="inputValue" @change="changeMsg" placeholder="请输入内容"></el-input>
  </div>

</template>

<style scoped lang="scss">

</style>
