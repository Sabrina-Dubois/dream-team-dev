import { createRouter, createWebHistory } from 'vue-router'
import ProfileView from '../views/ProfileView.vue'
import FiltersView from '../views/FiltersView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Profil',
      component: ProfileView
    },
    {
      path: '/filters',
      name: 'Filters',
      component: FiltersView
    }
  ]
})

export default router
