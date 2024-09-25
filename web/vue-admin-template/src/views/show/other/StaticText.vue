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
    // console.log("=========== Text =============");
    // console.log(parser(this.inputValue));
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

