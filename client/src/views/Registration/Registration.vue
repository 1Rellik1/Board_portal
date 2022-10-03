<template>
  <h1>Регистрация</h1>
  <p>Зарегистрируйся и получи премиум на 3 дня!</p>
  <v-button
      type="submit"
      @click="registration"
  >
    Создать аккаунт
  </v-button>
  <p @click="home_store.changeTemplate('Login')">Войти</p>
</template>

<script>
import VButton from '../../components/VButton/VButton.vue';
import { mapStores } from 'pinia';
import { useUserStore } from '../../stores/user.js';
import { useHomeStore } from '../../stores/home.js';

export default {
  name: 'Registration',
  components: {VButton},
  computed: {
    ...mapStores(useUserStore, useHomeStore)
  },
  methods: {
    async registration() {
      try {
        await this.user_store.testReg();
        this.home_store.isModalVisible = false;
        this.home_store.isModalCheck = false;
      } catch (e) {
        console.log(e.response.status);
      }
    }
  }
};
</script>

<style scoped>

</style>