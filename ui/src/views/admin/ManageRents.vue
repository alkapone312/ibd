<template>
    <HotelH1 class="header-of-page">Zarządzaj rezerwacjami</HotelH1>
    <div class="container">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Data zakwaterowania</th>
                    <th scope="col">Data wykwaterowania</th>
                    <th scope="col">Numer pokoju</th>
                    <th scope="col">Nazwa pokoju</th>
                    <th scope="col">Email</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="rent in reservations" scope="row">
                    <th>{{ rent.id }}</th>
                    <td>{{ rent.checkInDate! }}</td>
                    <td>{{ rent.checkOutDate! }}</td>
                    <td>{{ rent.room!.id }}</td>
                    <td>{{ rent.room!.name }}</td>
                    <td>{{ rent.client!.email }}</td>
                    <td>
                        <HotelButton type="text"
                            @click="cancelReservation(rent)"
                        >Anuluj</HotelButton>
                    </td>
                </tr>
            </tbody>
        </table>

        <div style="display: flex; width: 100%; justify-content: space-between;">
            <HotelButton @click.prevent="() => {router.go(-1)}" type="text">Cofnij</HotelButton>
        </div>
    </div>
</template>

<script setup lang="ts">
    import {ref, inject, toRaw} from 'vue';
    import {useRouter} from 'vue-router';
    import HotelButton from '../../components/HotelButton.vue';
    import HotelH1 from '../../components/HotelH1.vue';
    import ReservationManager from '../../interfaces/ReservationManager';
    import { Rent } from '../../interfaces/ReservationManager';

    const router = useRouter();
    const rentManager = inject('rentManager') as ReservationManager;
    let reservations = ref([] as Rent[]);
    console.log(toRaw(reservations));

    (async () => {
        reservations.value = await rentManager.getRents();
    })();

    const cancelReservation = async (rent: Rent) => {
        await rentManager.cancelRent(rent)
        router.go(0);
    }

</script>

<style scoped>
    @import '/bootstrap.css';

    * {
        font-size: 24px;
    }
</style>