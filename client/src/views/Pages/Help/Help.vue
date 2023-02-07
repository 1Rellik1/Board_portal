<template>
  <v-button @click="connect">Коннект</v-button>
  <v-button @click="send">Отправить</v-button>
  <v-button @click="sendStartGame">Начать игра</v-button>
  <v-button @click="sendMoveTransition">Передать ход</v-button>
  <v-button @click="sendMoveStatus">Статус ход</v-button>
  <v-button @click="sendRollDice">Кубики брось</v-button>
  <v-button @click="sendSurrender">Сдался слабак</v-button>
  <v-button @click="sendOffer">Предложение держи слабак</v-button>
  <v-button @click="sendRejectOffer">Не буду принимать, хуй ты волосатый</v-button>
  <v-button @click="sendAcceptOffer">буду принимать, хуй ты волосатый</v-button>
  <v-button @click="sendBuyCard">Купить</v-button>
  <v-button @click="sendImproveCard">Прокачать</v-button>
  <v-button @click="sendSellCard">Продать</v-button>
  <v-button @click="sendPayCard">Заплатить</v-button>
  <v-button @click="sendChance">Chance</v-button>
  <v-button @click="sendMessage">Отправить сообщение</v-button>
  <v-button @click="sendRest">Джэкпот</v-button>
  <v-button @click="sendTaxIncome">sendTaxIncome</v-button>
  <v-button @click="sendTaxLuxury">sendTaxLuxury</v-button>
  <v-button @click="sendStart">Остановился на старте</v-button>
  <v-button @click="sendToJail">Отправить в тюрьму</v-button>
  <v-button>Дисконнект</v-button>
</template>

<script>
import SockJS from 'sockjs-client/dist/sockjs';
import Stomp from 'webstomp-client'

import {VButton} from "@/components";

export default {
  name: 'Help',
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
        this.stompClient.send('/app/api/monopoly/sessions/add-player', JSON.stringify(json), {});
      }
    },
    sendStartGame() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'zelen2',
        }
        this.stompClient.send('/app/api/monopoly/sessions/start-game', JSON.stringify(json), {});
      }
    },
    sendMoveTransition() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'Player4',
        }
        this.stompClient.send('/app/api/monopoly/sessions/move-transition', JSON.stringify(json), {});
      }
    },
    sendMoveStatus() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId')
        }
        this.stompClient.send('/app/api/monopoly/sessions/move-status', JSON.stringify(json), {});
      }
    },
    sendRollDice() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'zelen2',
        }
        this.stompClient.send('/app/api/monopoly/sessions/roll-dice', JSON.stringify(json), {});
      }
    },
    sendSurrender() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'zelen2',
        }
        this.stompClient.send('/app/api/monopoly/sessions/surrender', JSON.stringify(json), {});
      }
    },
    sendOffer() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          creator: {
            playerName: 'zelen2',
            money: 100,
            cards: []
          },
          receiver: {
            playerName: 'Player4',
            money: 0,
            cards: []
          },
        }
        this.stompClient.send('/app/api/monopoly/sessions/send-offer', JSON.stringify(json), {});
      }
    },
    sendRejectOffer() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'Player4',
        }
        this.stompClient.send('/app/api/monopoly/sessions/reject-offer', JSON.stringify(json), {});
      }
    },
    sendAcceptOffer() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          creator: {
            playerName: 'zelen2',
            money: 100,
            cards: []
          },
          receiver: {
            playerName: 'Player4',
            money: 0,
            cards: []
          },
        }
        this.stompClient.send('/app/api/monopoly/sessions/accept-offer', JSON.stringify(json), {});
      }
    },
    sendBuyCard() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'Player4',
          cardId:"2"
        }
        this.stompClient.send('/app/api/monopoly/buy-card', JSON.stringify(json), {});
      }
    },
    sendImproveCard() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'Player4',
          cardId:"2"
        }
        this.stompClient.send('/app/api/monopoly/improve-card', JSON.stringify(json), {});
      }
    },
    sendSellCard() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'Player4',
          cardId:"2"
        }
        this.stompClient.send('/app/api/monopoly/sell-card', JSON.stringify(json), {});
      }
    },
    sendPayCard() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'zelen2',
          cardId:"2"
        }
        this.stompClient.send('/app/api/monopoly/pay-for-card', JSON.stringify(json), {});
      }
    },
    sendChance() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'zelen2'
        }
        this.stompClient.send('/app/api/monopoly/cards/chance', JSON.stringify(json), {});
      }
    },
    sendMessage() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          sender: 'zelen2',
          message:"Ебать ты лох, конечно"
        }
        this.stompClient.send('/app/api/monopoly/chat/common', JSON.stringify(json), {});
      }
    },
    sendRest() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'zelen2',
          digits:[6,2]
        }
        this.stompClient.send('/app/api/monopoly/cards/rest', JSON.stringify(json), {});
      }
    },
    sendTaxIncome() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'zelen2'
        }
        this.stompClient.send('/app/api/monopoly/cards/taxIncome', JSON.stringify(json), {});
      }
    },
    sendTaxLuxury() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'zelen2'
        }
        this.stompClient.send('/app/api/monopoly/cards/taxLuxury', JSON.stringify(json), {});
      }
    },
    sendStart() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'zelen2'
        }
        this.stompClient.send('/app/api/monopoly/cards/start', JSON.stringify(json), {});
      }
    },
    sendToJail() {
      if (this.stompClient && this.stompClient.connected) {
        const json = {
          sessionId: localStorage.getItem('sessionId'),
          playerName: 'zelen2'
        }
        this.stompClient.send('/app/api/monopoly/cards/goToJail', JSON.stringify(json), {});
      }
    },
    connect() {
      const id = localStorage.getItem('sessionId');
      this.socket = new SockJS('http://localhost:8083/gameplay');
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
            this.stompClient.subscribe(`/topic/chat/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/move-transition/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/move-status/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/change-balance/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/roll-dice/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/surrender/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/accept-offer/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/send-contract/${id}/Player4`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/card-action/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/pay-for-card/${id}`, tick => {
              console.log('tick', tick);
              // this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe(`/topic/change-position/${id}`, tick => {
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