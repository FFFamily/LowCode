<script>
export default {
  data() {
    return {
      inputValue: this.info.options["default"] || "",
      items: this.info.options["items"] || [],
    }
  },
  created() {
    // console.log("==========下拉选择========")
    // console.log(this.info);
    // console.log("==========下拉选择========")
  },
  props:{
    info:Object
  }
}
</script>

<template>
  <div>
    <template>
      <el-select v-model="inputValue" placeholder="请选择">
        <el-option v-for="item in items" :label="item.label" :value="item.value"></el-option>
      </el-select>
    </template>
  </div>

</template>

<style scoped lang="scss">

</style>
