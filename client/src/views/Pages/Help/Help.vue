<template>
  <v-button @click="connect">Коннект</v-button>
  <v-button @click="send">Отправить</v-button>
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
          sessionId: 'wzMV1QlBhUTyAb6DAV7Cb',
          playerName: 'zelen2',
        }
        this.stompClient.send('/app/api/monopoly/sessions/add-player', JSON.stringify(json), {});
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