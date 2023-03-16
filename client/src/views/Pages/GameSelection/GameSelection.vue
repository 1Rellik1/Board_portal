<template>
  <v-button @click="static">/sessions/static</v-button>
  <v-button @click="create">/sessions/create</v-button>
  <v-button @click="check">/sessions/check/{sessionId}</v-button>
  <v-button @click="state">/sessions/state/{sessionId}</v-button>
  <v-button @click="checkCard">/cards</v-button>
  <v-button @click="deleteSession">/sessions/{id} - заканчивает сессию</v-button>
  <v-button @click="getAllSessions">/sessions</v-button>
  <v-button @click="bunkerCreate">bunker/sessions/create</v-button>
  <v-button @click="bunkerCheck">bunker/sessions/check/{sessionId}</v-button>
  <v-button @click="bunkerState">bunker/sessions/state/{sessionId}</v-button>
  <v-button @click="bunkerDeleteSession">bunker/sessions/{id} - заканчивает сессию</v-button>
  <v-button @click="bunkerGetAllSessions">bunker/sessions</v-button>
</template>

<script>
import {VButton} from "@/components";
import {$authHostBunker, $authHostMonopoly} from "../../../http";
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
    async check() {
      const id = localStorage.getItem('sessionId')
      await $authHostMonopoly.get(`/sessions/check/${id}`)
          .then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.log(error);
          })
    },
    async state() {
      const id = localStorage.getItem('sessionId')
      await $authHostMonopoly.get(`/sessions/state/${id}`)
          .then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.log(error);
          })
    },
    async checkCard() {
      const id = localStorage.getItem('sessionId')
      await $authHostMonopoly.get('/cards', {
        params: {
          sessionId: id,
          cardId: 2,
        },
      })
          .then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.log(error);
          })
    },
    async deleteSession() {
      const id = localStorage.getItem('sessionId')
      await $authHostMonopoly.delete(`/sessions/${id}`)
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

    async bunkerCreate() {
      const id = nanoid();
      localStorage.setItem('sessionId', id);
      await $authHostBunker.post('/sessions/create', {
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
    async bunkerCheck() {
      const id = localStorage.getItem('sessionId')
      await $authHostBunker.get(`/sessions/check/${id}`)
          .then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.log(error);
          })
    },
    async bunkerState() {
      const id = localStorage.getItem('sessionId')
      await $authHostBunker.get(`/sessions/state/${id}`)
          .then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.log(error);
          })
    },
    async bunkerDeleteSession() {
      const id = localStorage.getItem('sessionId')
      await $authHostBunker.delete(`/sessions/${id}`)
          .then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.log(error);
          })
    },
    async bunkerGetAllSessions() {
      await $authHostBunker.get('/sessions')
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