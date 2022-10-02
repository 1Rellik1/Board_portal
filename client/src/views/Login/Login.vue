<template>
  <h1>Войти</h1>
  <p>Войти и начать играть</p>
  <v-button
      type="submit"
      variant="outline__white"
      @click="login"
  >
    Войти
  </v-button>
  <p @click="home_store.changeTemplate('Registration')">Ещё нет аккаунта? Создать</p>
</template>

<script>
import VButton from '../../components/VButton/VButton.vue';
import { mapStores } from 'pinia';
import { useUserStore } from '../../stores/user.js';
import { useHomeStore } from '../../stores/home.js';

export default {
  name: 'Login',
  components: {VButton},
  computed: {
    ...mapStores(useUserStore, useHomeStore)
  },
  methods: {
    async login() {
      try {
        await this.user_store.testLog();
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