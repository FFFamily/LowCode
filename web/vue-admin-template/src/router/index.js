import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import Layout from '@/layout'

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },

  {
    path: '/example',
    component: Layout,
    redirect: '/example/application',
    name: 'Example',
    meta: { title: '基础信息', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'example',
        name: 'Example',
        component: () => import('@/views/application/index'),
        meta: { title: '应用管理', icon: 'table' }
      }
    ]
  },

  {
    path: '/data_source',
    component: Layout,
    meta: { title: '表单设计', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'index',
        name: 'DataSource',
        component: () => import('@/views/data_source/index'),
        meta: { title: '表单设计', icon: 'el-icon-s-help' }
      },
      {
        path: 'field',
        name: 'DataSourceField',
        component: () => import('@/views/data_source/field.vue'),
        hidden: true,
        meta: { title: '数据表字段', icon: 'el-icon-s-help' }
      },
      {
        path: 'table',
        name: 'DataSourceTable',
        component: () => import('@/views/data_source/table.vue'),
        hidden: true,
        meta: { title: '数据表', icon: 'el-icon-s-help' }
      },
      {
        path: 'viewForm',
        name: 'ViewForm',
        component: () => import('@/views/data_source/viewForm.vue'),
        hidden: true,
        meta: { title: '视图列表', icon: 'el-icon-s-help' }
      },
      {
        path: 'viewConfig',
        name: 'ViewConfig',
        component: () => import('@/views/data_source/viewFormConfig.vue'),
        hidden: true,
        meta: { title: '视图列表', icon: 'el-icon-s-help' }
      }
    ]
  },
  {
    path: '/show',
    component: Layout,
    redirect: '/show',
    name: 'Example',
    meta: { title: '配置中心', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'show',
        name: 'Show',
        component: () => import('@/views/show/list.vue'),
        meta: { title: '配置中心', icon: 'table' }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
