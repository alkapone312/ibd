<template>
    <div class="container">
        <form @submit.prevent="onSubmit">
            <HotelInput label="Email" name="login" type="text" v-model="login"></HotelInput>
            <HotelInput label="Hasło" name="password" type="password" v-model="password"></HotelInput>
            <HotelInput label="Powtórz hasło" name="repeatPassword" type="password" v-model="repeatPassword"></HotelInput>
            
            <HotelButton type="text">
                Zarejestruj się
            </HotelButton>
            <HotelH3>
                Powrót do strony logowania?
            </HotelH3>
            <HotelLink to="/login" type="text">
                Zaloguj się!
            </HotelLink>
        </form>
    </div>
</template>

<script setup lang="ts">
import HotelInput from "../components/HotelInput.vue";
import HotelButton from "../components/HotelButton.vue";
import HotelH3 from "../components/HotelH3.vue";
import HotelLink from "../components/HotelLink.vue";
import LoginInterface from "../interfaces/LoginInterface";

import { inject } from 'vue'

let login = '';
let password = '';
let repeatPassword = '';

const apiFacade = inject('login') as LoginInterface;

const onSubmit = () => {
    if(password == repeatPassword) {
        apiFacade.register(login, password)
    }
}

</script>

<style scoped>
    .container {
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