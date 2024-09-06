<template>
  <el-switch v-model="readOnly" class="ml-2" style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"/>
</template>

<script lang="ts">
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
