import Vue from 'vue'
// 导入自定义的组件 @表示src这个文件夹的路径 在build目录下的webpack.base.conf.js 下配置 '@': resolve('src'),
import Content from '../components/Content'
// 导入商品自定义组件
import Product from "../components/Product";
// 导入路由插件
import Router from "vue-router";


// 安装路由
Vue.use(Router);
// 配置路由
export default new Router({
  routes:[
    {
      // 路由路径
      path: '/content',
      // 路由名称
      name: 'Content',
      // 跳转组件
      component: Content
    },
    {
      // 路由路径
      path: '/product',
      // 路由名称
      name: 'Product',
      // 跳转组件
      component: Product
    }
  ]
});
