<template>
  <component :is='input' info="11"></component>
<!--  <div v-for='item in textCompName' :key='item.id'>-->
<!--    <component :is='item.type'></component>-->
<!--  </div>-->
<!--  <HelloWorld msg="Vite + Vue" />-->
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import Input from './components/Input.vue'
let fieldList
export default {
  data(){
    return{
      textCompName : 'HelloWorld',
      input:'Input'
    }
  },
  components:{
    HelloWorld,
    Input
  }
}
</script>
