import Vue from 'vue';
import axios from 'axios';
import VueAxios from 'vue-axios';
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './plugins/element.js';
import '@/assets/css/global.css';
import router from './plugins/router.js';
import store from './plugins/store';
// import marked from 'marked';

axios.defaults.withCredentials=true;

Vue.prototype.$axios = axios; 
Vue.use(ElementUI);
Vue.use(VueAxios, axios);
Vue.config.productionTip = false;


new Vue({
  el: "#app",
  store,
  router,
  // marked,
  render: h => h(App),
}).$mount('#app')
