import { createApp } from 'vue/dist/vue.esm-bundler'
import { createRouter, createWebHistory } from 'vue-router'
import './style.css'
import App from './App.vue'

const Home = {template: '<div>Home</div>'};
const About = {template: '<div>About</div>'};
const Profile = {template: '<div>Profile</div>'};

const routes = [
    { path: '/', component: Home },
    { path: '/about', component: About },
    { path: '/profile', component: Profile },
]
  
// 3. Create the router instance and pass the `routes` option
// You can pass in additional options here, but let's
// keep it simple for now.
const router = createRouter({
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    history: createWebHistory(),
    routes, // short for `routes: routes`
})

const app = createApp(App);
app.use(router);
app.mount('#app')
