import { defineStore } from 'pinia';

export const useHomeStore = defineStore('home', {
    state: () => ({
        isModalVisible: false,
        isModalCheck: false,
    }),
    actions: {},
    getters: {},
});
