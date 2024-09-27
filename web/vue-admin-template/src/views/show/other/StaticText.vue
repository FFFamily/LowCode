<script>
import {parser} from "@/utils/FormulaParser";
export default {
  data() {
    return {

      inputValue: parser(this.info.options["default"] || ""),
      name: this.info.name || "",
    }
  },
  created() {
  },
  props:{
    info:Object
  }
}
</script>

<template>
  <div>
    <span>{{inputValue}}</span>
  </div>
</template>

