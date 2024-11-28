import { createApp } from 'vue/dist/vue.esm-bundler'
import { createRouter, createWebHashHistory } from 'vue-router'
import './style.css'
import App from './App.vue'
import ApiFacade from './services/ApiFacade';

import routes from './routes/routes';

console.log(routes)
  
// 3. Create the router instance and pass the `routes` option
// You can pass in additional options here, but let's
// keep it simple for now.
const router = createRouter({
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    history: createWebHashHistory(),
    routes, // short for `routes: routes`
})

const app = createApp(App);
const apiFacade = new ApiFacade('http://localhost:8081');
apiFacade.onLoginChange((isLogged) => {
    if(isLogged == false) {
        router.push('/login');
    }
    if(isLogged == true) {
        router.go(-1);
    }
})
app.use(router);
app.provide('login', apiFacade)
app.provide('roomManager', apiFacade)
app.provide('additionalEquipmentManager', apiFacade)
app.provide('equipmentManager', apiFacade)
app.provide('rentManager', apiFacade)
app.mount('#app');