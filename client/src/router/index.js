import { createRouter, createWebHistory } from 'vue-router';
import main from '../views/Pages/Main/Main.vue';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: () => import('../views/Home.vue'),
            redirect: {
                name: 'main',
            },
            children: [
                {
                    path: '/main',
                    name: 'main',
                    component: () => import('../views/Pages/Main/Main.vue'),
                    meta: {
                        title: 'Главная',
                    },
                },
                {
                    path: '/game-selection',
                    name: 'game-selection',
                    component: () => import('../views/Pages/GameSelection/GameSelection.vue'),
                    meta: {
                        title: 'Подбор игры',
                    },
                },
                {
                    path: '/help',
                    name: 'help',
                    component: () => import('../views/Pages/Help/Help.vue'),
                    meta: {
                        title: 'Поддержка',
                    },
                },
                {
                    path: '/help2',
                    name: 'help2',
                    component: () => import('../views/Pages/Help/Help2.vue'),
                    meta: {
                        title: 'Поддержка2',
                    },
                },
            ]
        },
    ],
});

router.beforeEach((to, from, next) => {
    document.title = to.meta.title;

    next();
});

export default router;
