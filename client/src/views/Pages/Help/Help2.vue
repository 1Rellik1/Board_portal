<template>
  <v-button @click="connect">Коннект</v-button>
  <v-button @click="send">Отправить</v-button>
  <v-button @click="sendStartGame">Начать игра</v-button>
  <v-button @click="sendMoveTransition">Передать ход</v-button>
  <v-button @click="sendKicoutPlayer">Выгнать игрока из бункера</v-button>
  <v-button @click="sendGetPlayerCards">Получить карты игрока</v-button>
  <v-button @click="sendShowPlayerCards">Показать карты игрока</v-button>
  <v-button @click="sendUseSpecialCard">Использовать спец карту</v-button>
  <v-button @click="sendVote">Проголосовать</v-button>
  <v-button @click="sendEndVoting">Закончить голосование</v-button>
  <v-button>Дисконнект</v-button>
</template>

<script>
import SockJS from 'sockjs-client/dist/sockjs';
import Stomp from 'webstomp-client'

import {VButton} from "@/components";

export default {
  name: 'Help2',
  components: {VButton},
  data() {
    return {
      received_messages: [],
      connected: false,
      socket: null,
      stompClient: null,
    };
  },
  methods: {
    send() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'zelen2',
        }
        this.stompClient.send('/app/api/bunker/sessions/add-player', JSON.stringify(json), {});
      }
    },
    sendStartGame() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'zelen2',
        }
        this.stompClient.send('/app/api/bunker/sessions/start-game', JSON.stringify(json), {});
      }
    },
    sendMoveTransition() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'Player4',
        }
        this.stompClient.send('/app/api/bunker/sessions/move-transition', JSON.stringify(json), {});
      }
    },
    sendKicoutPlayer() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'zelen2',
        }
        this.stompClient.send('/app/api/bunker/sessions/kick-out-player', JSON.stringify(json), {});
      }
    },
    sendGetPlayerCards() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'Player4',
        }
        this.stompClient.send('/app/api/get-player-cards', JSON.stringify(json), {});
      }
    },
    sendShowPlayerCards() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'Player4',
          cardId:'34'
        }
        this.stompClient.send('/app/api/show-player-cards', JSON.stringify(json), {});
      }
    },
    sendUseSpecialCard() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'Player4',
          secondPlayerName : 'zelen2',
          specialCardId:'51',
          cardId:''
        }
        this.stompClient.send('/app/api/useSpecialCard', JSON.stringify(json), {});
      }
    },
    sendVote() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          votingPlayerName: 'Player4',
          votedForPlayerName : 'zelen2',
        }
        this.stompClient.send('/app/api/bunker/vote', JSON.stringify(json), {});
      }
    },
    sendEndVoting() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
        }
        this.stompClient.send('/app/api/bunker/endVoting', JSON.stringify(json), {});
      }
    },
    connect() {
      const id = localStorage.getItem('sessionId');
      this.socket = new SockJS('http://localhost:8085/gameplay');
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
          {},
          frame => {
            this.connected = true;
            console.log('frame', frame);
            this.stompClient.subscribe(`/topic/add-player/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/start-game/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/move-transition/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/kick-out-player/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/player-cards/Player4/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/open-card/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/used-special-card/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/votes/info/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/kicked/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
          },
          error => {
            console.log(error);
            this.connected = false
          }
      );
    },
  }
};
</script>

<style scoped>

</style>