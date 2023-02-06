import { defineStore } from 'pinia';

export const useHomeStore = defineStore('home', {
    state: () => ({
        isModalVisible: false,
        isModalCheck: false,
        template: undefined,
    }),
    actions: {
        setAuthTemplate(type) {
            this.template = type;
            this.isModalVisible = true;
        },
        changeTemplate(type) {
            this.isModalCheck = false;
            this.template = type;
        },
        closeModal() {
            if (this.isModalVisible && !this.isModalCheck) {
                this.isModalCheck = true;
                return;
            }
            this.isModalVisible = false;
            this.isModalCheck = false;
            this.template = undefined;
        },
    },
    getters: {},
});
