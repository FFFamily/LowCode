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
  }
}
</script>

<template>
  <div>
<!--    <span>{{name}}</span>-->
    <template>
      <div class="block">
        <el-date-picker
          v-model="inputValue"
          type="date"
          placeholder="选择日期">
        </el-date-picker>
      </div>
    </template>
  </div>
</template>

<style scoped lang="scss">

</style>
