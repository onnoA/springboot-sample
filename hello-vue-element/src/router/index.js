import Vue from 'vue'
import VueRouter from "vue-router"

import Main from '../views/Main'
import Login from '../views/Login'

Vue.use(VueRouter)

export default new VueRouter({
  routes:[
    {
      // 登录页
      // 路由路径
      path: '/login',
      // 路由名称
      name: 'Login',
      // 路由组件
      component: Login
    },
    {
      // 首页
      path: '/main',
      name: 'Main',
      component: Main
    }
  ]
})
