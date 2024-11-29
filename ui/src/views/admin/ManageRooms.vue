<template>
    <div class="container">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nazwa</th>
                    <th scope="col">Opis</th>
                    <th scope="col">Powierzchnia</th>
                    <th scope="col">Liczba osób</th>
                    <th scope="col">Numer pokoju</th>
                    <th scope="col">Cena bazowa</th>
                    <th scope="col">Edytuj</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="room in rooms" scope="row">
                    <th>{{ room.id }}</th>
                    <td>{{ room.name }}</td>
                    <td>{{ room.description }}</td>
                    <td>{{ room.roomSize }}</td>
                    <td>{{ room.capacity }}</td>
                    <td>{{ room.roomNumber }}</td>
                    <td>{{ room.basePrice }}</td>
                    <td><HotelLink type="text" :to="'/manageRooms/edit/' + room.id">Edytuj</HotelLink></td>
                </tr>
            </tbody>
        </table>

        <div style="display: flex; width: 100%; justify-content: space-between;">
        <HotelButton @click.prevent="() => {router.go(-1)}" type="text">Cofnij</HotelButton>
        <HotelLink type="text" to="/manageRooms/add">Dodaj pokój</HotelLink>
        </div>
    </div>
</template>

<script lang="ts" setup>
    import HotelLink from '../../components/HotelLink.vue'
    import HotelButton from '../../components/HotelButton.vue'
    import RoomManager from '../../interfaces/RoomManager'
    import { Room } from '../../interfaces/RoomManager'
    import { inject, ref } from 'vue'
    import { useRouter } from 'vue-router'

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