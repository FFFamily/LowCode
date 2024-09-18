<template>
  <fc-designer ref="designer" height="100vh" />
</template>

<script>
import FcDesigner from "@form-create/designer";
import { ref } from 'vue';
// 可以在此处获取设计器实例或进行其他操作
const designer = ref(null);
export default {
  name: 'MyButton',
  components: {FcDesigner},
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
