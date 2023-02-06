<template>
  <div :class="$style.container">
    <div :class="$style.loginTemplate">
      <h1>Регистрация</h1>
      <p :class="$style['loginTemplate__step-title']">Зарегистрируйся и получи премиум на 3 дня!</p>
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
        <v-input
            v-model="confirmPassword"
            type="password"
            placeholder="Подтвердите пароль"
        />
        <v-button
            type="submit"
        >
          Создать аккаунт
        </v-button>
      </form>
    </div>
    <div :class="$style.divider"/>
    <div :class="$style.loginTemplate">
      <h1>Уже есть аккаунт?</h1>
      <p :class="$style['loginTemplate__step-title']">Войти и начать играть</p>
      <v-button type="button" variant="outline__white" @click="home_store.changeTemplate('Login')">
        Войти
      </v-button>
      <big-logo/>
    </div>
  </div>
</template>

<script>
import { mapStores } from 'pinia';
import { useUserStore } from '@/stores/user.js';
import { useHomeStore } from '@/stores/home.js';
import { VButton, VInput } from '@/components';
import BigLogo from '@/assets/images/logos/BigLogo.vue';

export default {
  name: 'Registration',
  components: {
    BigLogo,
    VButton,
    VInput,
  },
  data() {
    return {
      email: '',
      userName: '',
      password: '',
      confirmPassword: '',
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

<style lang="scss" module>
.container {
  display: flex;
  gap: 20px;
}

.divider {
  min-height: 100%;
  min-width: 1px;
  background-color: rgba(255, 255, 255, 0.4);
}

.loginTemplate {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;

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
  }

  button {
    width: 100%;
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