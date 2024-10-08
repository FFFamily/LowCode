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
        meta: { title: '薪资模块', icon: 'table' }
      },
      {
        path: 'salaryConfig',
        name: 'SalaryConfig',
        component: () => import('@/views/application/config.vue'),
        meta: { title: '薪资配置', icon: 'table' }
      }
    ]
  },

  {
    path: '/form',
    component: Layout,
    meta: { title: '表单设计', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'index',
        name: 'DataSource',
        component: () => import('@/views/form_page/index.vue'),
        meta: { title: '表单设计', icon: 'el-icon-s-help' }
      },
      {
        path: 'field',
        name: 'DataSourceField',
        component: () => import('@/views/form_page/field.vue'),
        meta: { title: '数据表字段', icon: 'el-icon-s-help' }
      },
      {
        path: 'table',
        name: 'DataSourceTable',
        component: () => import('@/views/form_page/table.vue'),
        meta: { title: '数据表', icon: 'el-icon-s-help' }
      },
      {
        path: 'viewForm',
        name: 'ViewForm',
        component: () => import('@/views/form_page/viewForm.vue'),
        meta: { title: '视图列表', icon: 'el-icon-s-help' }
      },
      {
        path: 'viewConfig',
        name: 'ViewConfig',
        component: () => import('@/views/form_page/viewFormConfig.vue'),
        meta: { title: '视图列表', icon: 'el-icon-s-help' }
      },
      {
        path: 'formAction',
        name: 'FormAction',
        component: () => import('@/views/form_page/formAction.vue'),
        meta: { title: '动作列表', icon: 'el-icon-s-help' }
      },
      {
        path: 'formRule',
        name: 'FormRule',
        component: () => import('@/views/rule_page/index.vue'),
        meta: { title: '表单规则', icon: 'el-icon-s-help' }
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
      },
      {
        path: 'view',
        name: 'VIEW',
        component: () => import('@/views/show/view.vue'),
        meta: { title: '查看视图', icon: 'table' }
      }
    ]
  },
  {
    path: '/data_source_view',
    component: Layout,
    redirect: '/data_source',
    name: 'DATA_SOURCE',
    meta: { title: '数据源中心', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'dataSource',
        name: 'DataSource',
        component: () => import('@/views/data_source/index.vue'),
        meta: { title: '数据源', icon: 'table' }
      },
      {
        path: 'dataSourceConfig',
        name: 'dataSourceConfig',
        component: () => import('@/views/data_source/config.vue'),
        meta: { title: '数据源配置', icon: 'table' }
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
