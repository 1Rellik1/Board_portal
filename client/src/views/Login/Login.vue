<template>
  <div :class="$style.loginTemplate">
    <h1>Войти</h1>
    <p :class="$style['loginTemplate__step-title']">Войти и начать играть</p>
    <form @submit.prevent="login">
      <v-input
          v-model="userName"
          type="text"
          placeholder="E-mail/Логин"
      />
      <v-input
          v-model="password"
          type="password"
          placeholder="Пароль"
      />
      <v-button
          type="submit"
      >
        Войти
      </v-button>
    </form>
    <p>Ещё нет аккаунта? <span @click="home_store.changeTemplate('Registration')">Создать</span></p>
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
      userName: '',
      password: '',
    };
  },
  computed: {
    ...mapStores(useUserStore, useHomeStore)
  },
  methods: {
    async login() {
      try {
        await this.user_store.login({
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

<style lang="scss" module>
.loginTemplate {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  min-width: 384px;

  &__step-title {
    font-size: 14px;
    color: rgba(255, 255, 255, 0.7);
    max-width: 220px;
    text-align: center;
  }

  form {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 16px;

    button {
      width: 100%;
    }
  }

  p {
    span {
      color: #8600EF;
      cursor: pointer;
      text-decoration: underline;

      &:hover {
        text-decoration: none;
      }
    }
  }
}
</style>
