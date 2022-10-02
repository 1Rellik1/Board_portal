import { defineStore } from 'pinia';
import { $host } from '../http/index.js';

export const useUserStore = defineStore('user', {
    state: () => ({
        token: localStorage.getItem('token'),
    }),
    actions: {
        async testReg() {
            await $host.post('/register', {
                userName: 'testFront12',
                userPassword: '12345678'
            }, {}).then((response) => {
                console.log(response.data);
            }).catch(e => {
                console.log(e);
                throw e;
            });
        },
        async testLog() {
            await $host.post('/login', {
                userName: 'testFront12',
                userPassword: '12345678'
            }, {}).then((response) => {
                console.log('Авторизавция прошла успешно');
                console.log(response);
                localStorage.setItem('token', response.headers.authorization);
            }).catch(e => {
                console.log(e);
                throw e;
            });
        }
    },
    getters: {},
});
