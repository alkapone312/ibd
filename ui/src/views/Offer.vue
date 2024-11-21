
import { onMounted } from 'vue';
<template>
    <div id="hotelContainer">
    <HotelH1>Offer</HotelH1>
    <div id="hotelOffer">
        <HotelLink v-for="room in rooms" :key="room.id" :to="'/room/' + room.id">
            <HotelRoomTile  :room="room"></HotelRoomTile>
        </HotelLink>
    </div>
    </div>
</template>

<script setup lang="ts">
    import { inject, ref } from 'vue';
    import HotelRoomTile from '../components/HotelRoomTile.vue'
    import HotelLink from '../components/HotelLink.vue'
    import HotelH1 from '../components/HotelH1.vue'
    import RoomManager from '../interfaces/RoomManager'

    const roomManager = inject('roomManager') as RoomManager;
    let rooms = ref([]);

    (async () => {
        rooms.value = await roomManager.getRooms();
    })()
</script>

<style scoped>
    #hotelContainer {
        width: 100%;
        margin: 50px;
        display: flex;
        flex-direction: column;
        gap: 20px;
    }

    #hotelOffer {
        width: 100%;
        display: flex;
        gap: 30px;
        justify-content: space-evenly;
        flex-wrap: wrap;
    }
</style>