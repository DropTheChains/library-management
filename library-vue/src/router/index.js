import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/user',
    name: 'user',
    component :() => import('../views/user/User.vue')
  },
  {
    path: '/addUser',
    name: 'addUser',
    component :() => import('../views/user/addUser.vue')
  },
  {
    path: '/editUser',
    name: 'editUser',
    component :() => import('../views/user/editUser.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
