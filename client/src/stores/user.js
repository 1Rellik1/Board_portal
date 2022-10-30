import { defineStore } from 'pinia';
import { $host } from '../http/index.js';

export const useUserStore = defineStore('user', {
    state: () => ({
        token: '' || localStorage.getItem('token'),
    }),
    actions: {
        async registration(payload) {
            await $host.post('/register', {
                userName: payload.userName,
                email: payload.email,
                userPassword: payload.password,
            }, {}).then((response) => {
                console.log(response.data);
            }).catch(e => {
                console.log(e);
                throw e;
            });
        },
        async login(payload) {
            await $host.post('/login', {
                userName: payload.userName,
                userPassword: payload.password,
            }, {}).then((response) => {
                console.log('Авторизавция прошла успешно');
                console.log(response);
                localStorage.setItem('token', response.headers.authorization);
            }).catch(e => {
                console.log(e);
                throw e;
            });
        },
        logout() {
            localStorage.clear();
        }
    },
    getters: {},
});
