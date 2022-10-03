<template>
  <div :class="$style.loginTemplate">
    <h1>Войти</h1>
    <p>Войти и начать играть</p>
    <v-input
        v-model="email"
        type="email"
        placeholder="E-mail/Логин"
    />
    <v-input
        v-model="password"
        type="password"
        placeholder="Пароль"
    />
    <v-button
        type="submit"
        @click="login"
    >
      Войти
    </v-button>
    <p @click="home_store.changeTemplate('Registration')">Ещё нет аккаунта? Создать</p>
  </div>
</template>

<script>
import { mapStores } from 'pinia';
import { useUserStore } from '@/stores/user.js';
import { useHomeStore } from '@/stores/home.js';
import { VButton, VInput } from '@/components';

export default {
  name: 'Login',
  components: {VInput, VButton},
  data() {
    return {
      email: '',
      password: '',
    };
  },
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

<style lang="scss" module>
.loginTemplate {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;

  button {
    width: 100%;
  }
}
</style>