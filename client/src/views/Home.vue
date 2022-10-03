<template>
  <div :class="$style.container">
    <header :class="$style['container__header']">
      <div :class="$style['container__header__leftColumn']">
        <div :class="$style['container__header__leftColumn__logo']" @click="handleLogo">
          <full-logo/>
        </div>

        <div :class="$style['container__header__leftColumn__search']">
          <v-search-input v-model="search"/>
        </div>

        <nav :class="$style['container__header__leftColumn__nav']">
          <router-link to="/main" :active-class="$style.activeLink">
            Главная
          </router-link>
          <router-link to="/game-selection" :active-class="$style.activeLink">
            Подбор игры
          </router-link>
          <router-link to="/help" :active-class="$style.activeLink">
            Поддержка
          </router-link>
        </nav>
      </div>

      <div :class="$style['container__header__rightColumn']">
        <v-button type="button" @click="home_store.setAuthTemplate('Registration')">Присоединиться</v-button>
        <v-button type="button" variant="outline" @click="home_store.setAuthTemplate('Login')">Войти</v-button>
      </div>
    </header>

    <div :class="$style['container__content']">
      <router-view/>
    </div>

    <v-modal :visible="home_store.isModalVisible" @close="home_store.closeModal">
      <component
          :is="home_store.template"
      />
    </v-modal>
  </div>
</template>

<script>
import FullLogo from '@/assets/images/logos/FullLogo.vue';
import { VButton, VModal, VSearchInput } from '@/components';
import Login from './Login/Login.vue';
import Registration from './Registration/Registration.vue';
import { mapStores } from 'pinia';
import { useHomeStore } from '@/stores/home.js';

export default {
  name: 'Home',
  components: {
    VSearchInput,
    VButton,
    VModal,
    FullLogo,
    Login,
    Registration,
  },
  data() {
    return {
      search: '',
    };
  },
  computed: {
    ...mapStores(useHomeStore)
  },
  methods: {
    handleLogo() {
      this.$router.push('/main');
    },
  },
};
</script>

<style lang="scss" module>
.container {
  display: flex;
  flex-direction: column;
  height: 100vh;

  &__header {
    position: sticky;
    top: 0;
    left: 0;
    right: 0;
    display: flex;
    align-items: stretch;
    justify-content: space-between;
    padding: 8px 16px;
    background: #212330;
    color: #fff;

    &__leftColumn {
      display: flex;
      margin: auto 0;

      &__logo {
        cursor: pointer;
        margin: auto 32px auto 0;
      }

      &__search {
        margin: auto 40px auto 0;
      }

      &__nav {
        margin: auto 0;

        a {
          font-family: 'Nunito', sans-serif;
          font-style: normal;
          font-weight: 600;
          font-size: 16px;
          line-height: 106.4%;
          margin-right: 40px;

          &:last-child {
            margin-right: 0;
          }

          &:hover {
            color: #8600EF;
          }
        }
      }
    }

    &__rightColumn {
      margin: auto 0;

      button {
        margin-right: 25px;

        &:last-child {
          margin-right: 0;
        }
      }
    }
  }

  &__content {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    padding: 8px 64px;
    overflow-y: auto;
    text-align: center;
  }
}

.activeLink {
  color: #8600EF;
}
</style>
