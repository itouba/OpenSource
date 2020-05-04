import Vue from 'vue'
import App from './App.vue'

import router from './router'
import store from './store'

// 引入fastclick并绑定到body 解决移动端点击响应延迟0.3s的问题
import FastClick from 'fastclick'
FastClick.attach(document.body)

new Vue(
  {
    el: '#app',
    render: function (createElement) {
      return createElement(App);
    },
    router, // 使用vue-router
    store  //使用vuex
  }
)
