<template>
  <h1>Регистрация</h1>
  <p>Зарегистрируйся и получи премиум на 3 дня!</p>
  <v-button
      type="submit"
      @click="user_store.testReg()"
  >
    Создать аккаунт
  </v-button>
</template>

<script>
import VButton from '../../components/VButton/VButton.vue';
import { mapStores } from 'pinia';
import { useUserStore } from '../../stores/user.js';

export default {
  name: 'Registration',
  components: {VButton},
  computed: {
    ...mapStores(useUserStore)
  }
};
</script>

<style scoped>

</style>