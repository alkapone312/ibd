import Home from '../views/Home.vue';
import Login from '../views/Login.vue';
import Register from '../views/Register.vue';
import { RouteRecordRaw } from 'vue-router';

export default [
    {path: '/', component: Home},
    {path: '/login', component: Login},
    {path: '/register', component: Register},
] as RouteRecordRaw[]