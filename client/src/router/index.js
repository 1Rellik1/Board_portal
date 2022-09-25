import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/Home.vue'),
      meta: {
        title: 'Home',
      },
      children: [
        {
          path: '/test',
          name: 'test',
          component: () => import('../views/Test.vue'),
          meta: {
            title: 'Test',
          },
        }
      ]
    },
  ],
});

router.beforeEach((to, from, next) => {
  document.title = to.meta.title;

  next();
});

export default router;
