<template>
    <div id="loginForm">
        <form @submit.prevent="onSubmit">
            <HotelInput 
                label="Email"
                name="login" 
                type="text" 
                v-model="login"
            ></HotelInput>
            <HotelInput 
                label="Hasło"
                name="password" 
                type="password" 
                v-model="password"
            ></HotelInput>
            <HotelButton type="text">
                Zaloguj
            </HotelButton>
            <HotelH3>
                Nie masz jeszcze konta?
            </HotelH3>
            <HotelLink to="/register" type="text">
                Zarejetruj się!
            </HotelLink>
        </form>
    </div>
</template>

<script setup lang="ts">
import HotelInput from "../components/HotelInput.vue";
import HotelH3 from "../components/HotelH3.vue";
import HotelButton from "../components/HotelButton.vue";
import HotelLink from "../components/HotelLink.vue";
import { inject } from 'vue';
import { useRouter } from 'vue-router';


let login = '';
let password = '';

const router = useRouter();
const apiFacade = inject('login') as LoginInterface 

const onSubmit = () => {
    apiFacade.login(login, password)
    router.push({path: '/'})
}

</script>

<style scoped>
    #loginForm {
        margin-top: 10%;
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    form {
        width: 600px;
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 20px;
    }
</style>