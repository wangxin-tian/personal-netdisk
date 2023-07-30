import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

import login_ from '../components/login_.vue';
import index_ from '../components/index_hello.vue';
import register_ from '../components/register_.vue';
import personal_ from '../components/user/personal_.vue';
import main_folder from "../components/allmain/main_folder.vue";
import main_all from "../components/allmain/main_all.vue";
import main_home from "../components/allmain/main_home.vue";
import main_ from "../components/user/main_.vue";
import main_search from "../components/allmain/main_search.vue";
import main_text from "../components/allmain/main_text.vue";
import main_video from "../components/allmain/main_video.vue";
import main_picture from "../components/allmain/main_picture.vue";
import main_audio from "../components/allmain/main_audio.vue";
import info_visualization from "../components/allmain/info_visualization.vue";
import other_rubbish from "../components/other/other_rubbish.vue";
import other_share from "../components/other/other_share.vue";
import user_action from "../components/user/user_action.vue";

const router = new VueRouter({
  mode: 'history',
  routes: [{
      path: '/',
      name: 'login',
      component: login_
  },{
    path: '/regist',
    name: 'register',
    component: register_
  },{
      path: '/index',
      name: 'index',
      component: index_,
      children: [{
        path: '/all',
        name: 'main_all',
        component: main_all,
      },{
        path: '/folder',
        name: 'main_folder',
        component: main_folder,
      },{
        path: '/personal',
        name: 'personal',
        component: personal_
      },{
        path: '/home',
        name: 'main_home',
        component: main_home
      },{
        path: '/users',
        name: 'main_',
        component: main_
      },{
        path: '/search',
        name: 'main_search',
        component: main_search
      },{
        path: '/video',
        name: 'main_video',
        component: main_video
      },{
        path: '/mytext',
        name: 'main_text',
        component: main_text
      },{
        path: '/audio',
        name: 'main_audio',
        component: main_audio
      },{
        path: '/picture',
        name: 'main_picture',
        component: main_picture
      },{
        path: '/rubbish',
        name: 'other_rubbish',
        component: other_rubbish
      },{
        path: '/action',
        name: 'user_action',
        component: user_action
      },{
        path: '/share',
        name: 'other_share',
        component: other_share
      },{
        path: '/visual',
        name: 'info_visualization',
        component: info_visualization
      }]
  }],
})

export default router;