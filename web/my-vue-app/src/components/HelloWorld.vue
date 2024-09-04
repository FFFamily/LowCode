<template>
  <button @click="handleClick" :class="buttonClass">
    <slot></slot>
  </button>
  <el-radio v-model="radio" label="1">备选项</el-radio>
  <el-radio v-model="radio" label="2">备选项</el-radio>
</template>

<script>
export default {
  name: 'MyButton',
  data(){
    return{
      radio: '1'
    }
  },
  props: {
    buttonClass: {
      type: String,
      default: ''
    }
  },
  methods: {
    handleClick() {
      // 处理按钮点击事件的逻辑
      console.log('按钮被点击了！');
    }
  }
}
</script>

<style scoped>
/* 组件的样式 */
button {
  /* 样式规则 */
}
</style>