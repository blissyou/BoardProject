import {createApp} from 'vue'
import App from './App.vue'
import router from './router'

//fixme: css 적용이 안됨 해결해야함
import './assets/styles.css'
import axios from 'axios'
const app =createApp(App)
app.config.globalProperties.$axios = axios;
app.config.globalProperties.$serverUrl = '//localhost:8080'
app.use(router).mount('#app')