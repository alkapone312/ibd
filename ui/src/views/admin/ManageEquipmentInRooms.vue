<template>
    <div class="container">
        <HotelLink type="text" to="/manageRooms/add">Dodaj pokój</HotelLink>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nazwa</th>
                    <th scope="col">Numer pokoju</th>
                    <th scope="col">Wyposażenie</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="room in rooms" scope="row">
                    <th>{{ room.id }}</th>
                    <td>{{ room.name }}</td>
                    <td>{{ room.roomNumber }}</td>
                    <td><HotelLink type="text" :to="'/manageEquipmentInRoom/room/' + room.id">Edytuj</HotelLink></td>
                </tr>
            </tbody>
        </table>
        <HotelButton @click.prevent="() => {router.go(-1)}" type="text">Cofnij</HotelButton>
    </div>
</template>

<script lang="ts" setup>
    import HotelButton from '../../components/HotelButton.vue'
    import { useRouter } from 'vue-router';
import HotelLink from '../../components/HotelLink.vue'
    import RoomManager from '../../interfaces/RoomManager'
    import { Room } from '../../interfaces/RoomManager'
    import { inject, ref } from 'vue'

    const router = useRouter();
    const roomManager = inject('roomManager') as RoomManager;
    let rooms = ref([] as Room[]);
    
    const a = async () => {
        rooms.value = await roomManager.getRooms();
    };
    a();
</script>

<style scoped>
    @import '/bootstrap.css';

    * {
        font-size: 24px;
    }

    .container {
        margin-top: 30px;
        gap: 20px;
        display: flex;
        flex-direction: column;
    }
</style>