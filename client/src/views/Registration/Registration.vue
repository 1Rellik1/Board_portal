<template>
  <h1>Регистрация</h1>
  <p>Зарегистрируйся и получи премиум на 3 дня!</p>
  <form @submit.prevent="registration">
    <v-input
        v-model="email"
        type="email"
        placeholder="E-mail"
    />
    <v-input
        v-model="userName"
        type="text"
        placeholder="Логин"
    />
    <v-input
        v-model="password"
        type="password"
        placeholder="Пароль"
    />
    <v-button
        type="submit"
        @click="registration"
    >
      Создать аккаунт
    </v-button>
  </form>
  <p @click="home_store.changeTemplate('Login')">Войти</p>
</template>

<script>
import { mapStores } from 'pinia';
import { useUserStore } from '@/stores/user.js';
import { useHomeStore } from '@/stores/home.js';
import { VButton, VInput } from '@/components';

export default {
  name: 'Registration',
  components: {
    VButton,
    VInput,
  },
  data() {
    return {
      email: '',
      userName: '',
      password: '',
    };
  },
  computed: {
    ...mapStores(useUserStore, useHomeStore)
  },
  methods: {
    async registration() {
      try {
        await this.user_store.registration({
          email: this.email,
          userName: this.userName,
          password: this.password,
        });
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