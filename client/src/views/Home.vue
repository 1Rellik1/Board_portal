<template>
  <div :class="$style.container">
    <header :class="$style['container__header']">
      <div :class="$style['container__header__logo']" @click="handleLogo">
        <full-logo/>
      </div>

      <nav :class="$style['container__header__nav']">
        <v-search-input v-model="search"/>
        <router-link to="/test" :active-class="$style.activeLink">
          Test
        </router-link>
      </nav>

      <div :class="$style['container__header__rightColumn']">
        <v-button type="button" @click="setAuthTemplate('Registration')">Присоединиться</v-button>
        <v-button type="button" variant="outline" @click="setAuthTemplate('Login')">Войти</v-button>
      </div>
    </header>

    <div :class="$style['container__content']">
      <router-view/>
    </div>

    <v-modal v-if="isModalVisible" @close="closeModal">
      <component
          :is="template"
      />
    </v-modal>
  </div>
</template>

<script>
import FullLogo from '../assets/images/logos/FullLogo.vue';
import { VButton, VModal, VSearchInput } from '../components';
import Login from './Login/Login.vue';
import Registration from './Registration/Registration.vue';

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
      isModalVisible: false,
      isModalCheck: false,
      template: undefined,
    };
  },
  methods: {
    handleLogo() {
      this.$router.push('/');
    },
    setAuthTemplate(type) {
      this.template = type;
      this.isModalVisible = true;
    },
    closeModal() {
      if (this.isModalVisible && !this.isModalCheck) {
        this.isModalCheck = true;
        return;
      }
      this.isModalVisible = false;
      this.isModalCheck = false;
      this.template = undefined;
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
