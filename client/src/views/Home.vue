<template>
  <div :class="$style.container">
    <header :class="$style['container__header']">
      <div :class="$style['container__header__logo']" @click="handleLogo">
        <full-logo/>
      </div>

      <nav :class="$style['container__header__nav']">
        <v-search-input v-model="search"/>
        <v-button type="button" @click="testReg">Рег</v-button>
        <v-button type="button" @click="testLog">Лог</v-button>
        <router-link to="/test" :active-class="$style.activeLink">
          Test
        </router-link>
      </nav>

      <div :class="$style['container__header__rightColumn']">
        <v-button type="button" @click="login">Присоединиться</v-button>
        <v-button type="button" variant="outline" @click="login">Войти</v-button>
      </div>
    </header>

    <div :class="$style['container__content']">
      <router-view/>
    </div>
    <v-modal v-if="isModalVisible" @close="closeModal">
      fndsjfbodls
    </v-modal>
  </div>
</template>

<script>
import FullLogo from '@/assets/images/logos/FullLogo.vue';
import { VButton, VModal, VSearchInput } from '@/components';
import { $host } from '../http/index.js';

export default {
  name: 'Home',
  components: {
    VSearchInput,
    VButton,
    VModal,
    FullLogo,
  },
  data() {
    return {
      search: '',
      isModalVisible: false,
      isModalCheck: false,
    };
  },
  methods: {
    handleLogo() {
      this.$router.push('/');
    },
    login() {
      this.isModalVisible = true;
    },
    closeModal() {
      if (this.isModalVisible && !this.isModalCheck) {
        this.isModalCheck = true;
        return;
      }
      this.isModalVisible = false;
      this.isModalCheck = false;
    },
    async testReg() {
      await $host.post('/register', {
        userName: 'testFront10',
        userPassword: '12345678'
      }, {}).then((response) => {
        console.log(response.data);
      }).catch(e => {
        console.log(e);
        // console.log(e.message);
      });
    },
    async testLog() {
      await $host.post('/login', {
        userName: 'testFront10',
        userPassword: '12345678'
      }, {}).then((response) => {
        console.log('response', response);
        console.log('loadData', response.data);
        console.log('loadHeader', response.headers);
        localStorage.setItem('token', response.headers.authorization);
      }).catch(e => {
        console.log(e);
        // console.log(e.message);
      });
    }
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

    &__logo {
      cursor: pointer;
      margin: auto 0;
    }

    &__nav {
      margin: auto 0;
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
