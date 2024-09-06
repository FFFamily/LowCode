<template>
  <el-input-number v-model="num" :min="1" :max="10"/>
</template>

<script lang="ts" setup>
export default {
  data() {
    return {
      readOnly: this.info.options["readOnly"]
    }
  },
  props:{
    info:Object
  }
}
</script>
