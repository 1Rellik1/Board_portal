import { createApp } from 'vue';
import { createPinia, setMapStoreSuffix } from 'pinia';

import App from './App.vue';
import router from './router';
import VClickOutside from '@/directives/VClickOutside';

const app = createApp(App);

setMapStoreSuffix('_store');

app.directive('click-outside', VClickOutside);

app.use(createPinia());
app.use(router);

app.mount('#app');
