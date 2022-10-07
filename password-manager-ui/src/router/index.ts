import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes: Array<RouteRecordRaw> = [
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
    path: '/senhas',
    name: 'password',
    component: () => import("../views/password/PasswordList.vue")
  },
  {
    path: '/senhas/:id',
    name: 'password-detail',
    props: (router) => ({ id: router.params.id }),
    component: () => import("../views/password/PasswordDetail.vue")
  },
  {
    path: '/senhas/editar/:id',
    name: 'password-edit',
    props: (router) => ({ id: router.params.id }),
    component: () => import("../views/password/PasswordEdit.vue")
  },
  {
    path: '/senhas/cadastrar',
    name: 'password-register',
    component: () => import("../views/password/PasswordRegister.vue")
  },
  {
    path: '/usuarios/login',
    name: 'user-login',
    component: () => import("../views/user/Login.vue")
  },
  {
    path: '/usuarios/registrar',
    name: 'user-register',
    component: () => import("../views/user/UserRegister.vue")
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
