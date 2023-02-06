<template>
  <div v-for="(game, id) in games" :key="id">
    <img :src="`data:image/${game.type};base64,`+game.image" alt="test">
  </div>
</template>

<script>
import {$hostGames} from "../../../http";

export default {
  name: 'Main',
  created() {
    this.getAllGames();
  },
  data() {
    return {
      games: [],
    };
  },
  methods: {
    async getAllGames() {
      await $hostGames.get('/getGamesInfo')
          .then(response => {
            console.log(response.data);
            this.games = response.data;
          })
          .catch(error => {
            console.log(error);
          })
    }
  },
};
</script>

<style scoped>

</style>