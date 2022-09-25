import { createApp } from 'vue';
import { createPinia, setMapStoreSuffix } from 'pinia';

import App from './App.vue';
import router from './router';

const app = createApp(App);

setMapStoreSuffix('_store');

app.use(createPinia());
app.use(router);

app.mount('#app');
