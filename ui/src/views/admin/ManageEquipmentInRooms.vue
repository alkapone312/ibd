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
                    <td><HotelLink type="text" :to="'/manageRooms/edit/' + room.id">Edytuj</HotelLink></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script lang="ts" setup>
    import HotelH1 from '../../components/HotelH1.vue'
    import HotelLink from '../../components/HotelLink.vue'
    import RoomManager from '../../interfaces/RoomManager'
    import { inject, ref } from 'vue'

    const roomManager = inject('roomManager') as RoomManager;
    let rooms = ref([]);
    
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