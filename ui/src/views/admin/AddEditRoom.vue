<template>
    <div class="container">
        <HotelH3>Dodaj/edytuj pokój</HotelH3>
        <form @submit.prevent="onSubmit">
            <HotelInput label="Nazwa" name="name" type="text" v-model="room.name"></HotelInput>
            <HotelInput label="Opis" name="description" type="textarea" v-model="room.description"></HotelInput>
            <HotelInput label="Rozmiar" name="roomSize" type="number" v-model="room.roomSize"></HotelInput>
            <HotelInput label="Ilość osób" name="capacity" type="number" v-model="room.capacity"></HotelInput>
            <HotelInput label="Numer pokoju" name="roomNumber" type="number" v-model="room.roomNumber"></HotelInput>
            <HotelInput step=".01" label="Cena bazowa" name="basePrice" type="number" v-model="room.basePrice"></HotelInput>
            <HotelButton type="text">Zapisz</HotelButton>
            <HotelButton @click.prevent="() => {router.go(-1)}" type="text">Cofnij</HotelButton>
        </form>
    </div>
</template>

<script setup lang="ts">
import HotelInput from "../../components/HotelInput.vue";
import HotelButton from "../../components/HotelButton.vue";
import HotelH3 from "../../components/HotelH3.vue";
import { Room } from '../../interfaces/RoomManager';
import RoomManager from '../../interfaces/RoomManager';
import {useRoute, useRouter} from 'vue-router';
import { inject, ref } from 'vue'

const route = useRoute();
const router = useRouter();
let room = ref({
    id: 0,
    name: '',
    description: '',
    roomSize: 0,
    capacity: 0,
    basePrice: 0
} as Room);

const roomManager = inject('roomManager') as RoomManager;

if(route.params.id && typeof route.params.id == 'string') {
    const a = async () => {
        // @ts-ignore
        let r = await roomManager.getRoom(parseInt(route.params.id))
        room.value.id = r.id;
        room.value.name = r.name;
        room.value.description = r.description;
        room.value.basePrice = r.basePrice;
        room.value.roomNumber = r.roomNumber;
        room.value.capacity = r.capacity;
        room.value.roomSize = r.roomSize;
    }
    a();
}

const onSubmit = () => {
    if(route.params.id && typeof route.params.id == 'string')
        roomManager.updateRoom(room.value);
    else
        roomManager.saveRoom(room.value);

    router.go(-1);
}

</script>

<style scoped>
    .container {
        margin-top: 5%;
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
    }

    form {
        width: 600px;
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 20px;
    }
</style>