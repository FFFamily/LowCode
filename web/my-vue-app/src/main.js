import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import ElementPlus from 'element-plus' //全局引入
import FcDesigner from '@form-create/designer';
import 'element-plus/dist/index.css'

const app = createApp(App)
app.use(ElementPlus)
app.use(FcDesigner);
app.use(FcDesigner.formCreate);
app.mount('#app')
