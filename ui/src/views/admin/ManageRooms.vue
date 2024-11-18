<template>
    <div id="roomManagement">
        <HotelH1>Zarządzanie pokojami</HotelH1>
        <HotelLink type="text" to="/addRoom">Dodaj pokój</HotelLink>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nazwa</th>
                    <th>Opis</th>
                    <th>Rozmiar pokoju [m^2]</th>
                    <th>Cena bazowa</th>
                    <th>Liczba osób</th>
                </tr>
            </thead>
            <tbody>
                
            </tbody>
        </table>
    </div>
</template>

<script lang="ts" setup>
    import HotelH1 from '../../components/HotelH1.vue'
    import HotelLink from '../../components/HotelLink.vue'
    import RoomManager from '../../interfaces/RoomManager'
    import { inject } from 'vue'

    const roomProvider = inject('roomManager') as RoomManager;

</script>

<style>
    #roomManagement {
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items:center;
        gap: 20px;
    }
</style>