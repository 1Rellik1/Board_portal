<template>
  <h1>Войти</h1>
  <p>Войти и начать играть</p>
  <v-button
      type="submit"
      @click="login"
  >
    Войти
  </v-button>
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
    login() {
      this.user_store.testLog();
      this.home_store.isModalVisible = false;
      this.home_store.isModalCheck = false;
    }
  }
};
</script>

<style scoped>

</style>