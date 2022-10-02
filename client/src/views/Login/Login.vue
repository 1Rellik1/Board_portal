<template>
  <h1>Войти</h1>
  <p>Войти и начать играть</p>
  <v-button
      type="submit"
      @click="user_store.testLog()"
  >
    Войти
  </v-button>
</template>

<script>
import VButton from '../../components/VButton/VButton.vue';
import { mapStores } from 'pinia';
import { useUserStore } from '../../stores/user.js';

export default {
  name: 'Login',
  components: {VButton},
  computed: {
    ...mapStores(useUserStore)
  }
};
</script>

<style scoped>

</style>