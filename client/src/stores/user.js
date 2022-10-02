import { defineStore } from 'pinia';
import { $host } from '../http/index.js';

export const useUserStore = defineStore('user', {
    state: () => ({
        token: localStorage.getItem('token'),
    }),
    actions: {
        async testReg() {
            await $host.post('/register', {
                userName: 'testFront10',
                userPassword: '12345678'
            }, {}).then((response) => {
                console.log(response.data);
            }).catch(e => {
                console.log(e);
            });
        },
        async testLog() {
            await $host.post('/login', {
                userName: 'testFront10',
                userPassword: '12345678'
            }, {}).then((response) => {
                localStorage.setItem('token', response.headers.authorization);
            }).catch(e => {
                console.log(e);
            });
        }
    },
    getters: {},
});
