import { createRouter, createWebHistory } from 'vue-router'
import DashboardView from '../views/DashboardView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'dashboard',
      component: DashboardView,
    },
    {
      path: '/create',
      name: 'create',
      // route level code-splitting
      // this generates a separate chunk (Create.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/CreateDeviceView.vue'),
    },
    {
      path: '/factory',
      name: 'factory',
      component: () => import('../views/FactoryView.vue'),
    }
  ],
})

export default router
