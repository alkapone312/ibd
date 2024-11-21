<template>
    <div id="singleRoom">
        <div class="container">
            <div class="imgAndCalendar">
                <HotelCalendar class="calendar"></HotelCalendar>
                <img src="/room.jpg"/>
            </div>
            <div class="hr"></div>
            <HotelH1>{{ room.name }}</HotelH1>
            <HotelP>{{ room.description }}</HotelP>
            <div class="hr"></div>
            <HotelH2>Parametry</HotelH2>
            <ul>
                <li>Rozmiar: {{ room.roomSize }} m^2</li>
                <li>Ilośc osób: {{ room.capacity }}</li>
                <li>Nr pokoju: {{ room.roomNumber }}</li>
                <li>Cena bazowa: {{ room.basePrice }} zł</li>
            </ul>
            <HotelLink v-if="isLogged" type="text" :to="'/rent/' + room.id">Wynajmij</HotelLink>
            <HotelH3 v-else>Zaloguj się lub zarejestruj aby wynająć pokój</HotelH3>
        </div>
    </div>
</template>

<script setup lang="ts">
    import { inject, ref } from 'vue';
    import { useRoute } from 'vue-router';
    import HotelCalendar from '../components/HotelCalendar.vue'
    import HotelH1 from '../components/HotelH1.vue'
    import HotelH2 from '../components/HotelH2.vue'
    import HotelH3 from '../components/HotelH3.vue'
    import HotelLink from '../components/HotelLink.vue'
    import HotelP from '../components/HotelP.vue'
    import RoomManager from '../interfaces/RoomManager'

    const roomManager = inject('roomManager') as RoomManager;
    const login = inject('login') as LoginInterface;
    let isLogged = ref(login.isLogged());
    login.onLoginChange(isLoggedNew => {
        isLogged.value = isLoggedNew;
    })
    const route = useRoute();
    let room = ref({});
    (async () => {
        room.value = await roomManager.getRoom(route.params.id)
    })()
</script>

<style scoped>
    #singleRoom {
        width: 100%;
    }

    .hr {
        width: 100%;
        padding: 0 10px;
        border-bottom: 1px solid #d2d2d2;
    }
    
    .container {
        width: 80%;
        margin: 10%;
        display: flex;
        flex-direction: column;
        gap: 30px;
    }

    .calendar {
        max-width: 500px;
    }

    .imgAndCalendar {
        width: 100%;
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 20px;
        align-items: center;
    }

    img {
        width: 100%;
    }

</style>