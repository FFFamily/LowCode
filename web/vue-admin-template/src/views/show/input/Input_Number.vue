<template>
  <el-input-number v-model="inputValue" :min="1" :max="10"/>
</template>

<script lang="ts">
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
