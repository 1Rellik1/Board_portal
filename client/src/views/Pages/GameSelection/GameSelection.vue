<template>
  <v-button @click="static">/sessions/static</v-button>
  <v-button @click="create">/sessions/create</v-button>
  <v-button>/sessions/check/{sessionId}</v-button>
  <v-button>/sessions/state/{sessionId}</v-button>
  <v-button>/sessions/{id} - заканчивает сессию</v-button>
  <v-button @click="getAllSessions">/sessions</v-button>
</template>

<script>
import {VButton} from "@/components";
import {$authHostMonopoly} from "../../../http";
import {nanoid} from "nanoid";
import {mapStores} from "pinia";
import {useUserStore} from "@/stores/user.js";

export default {
  name: 'GameSelection',
  components: {VButton},
  computed: {
    ...mapStores(useUserStore)
  },
  methods: {
    async static() {
      await $authHostMonopoly.get('/sessions/static')
          .then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.log(error);
          })
    },
    async create() {
      const id = nanoid();
      localStorage.setItem('sessionId', id);
      await $authHostMonopoly.post('/sessions/create', {
        sessionId: id,
        playerName: this.user_store.getUser.username,
      })
          .then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.log(error);
          })
    },
    async getAllSessions() {
      await $authHostMonopoly.get('/sessions')
          .then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.log(error);
          })
    },
  }
};
</script>

<style scoped>

</style>