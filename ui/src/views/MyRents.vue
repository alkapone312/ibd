<template>
    <div class="container">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Data zakwaterowania</th>
                    <th scope="col">Data wykwaterowania</th>
                    <th scope="col">Nazwa pokoju</th>
                    <th scope="col">Anuluj</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="rent in reservations" scope="row">
                    <th>{{ rent.id }}</th>
                    <td>{{ rent.checkInDate! }}</td>
                    <td>{{ rent.checkOutDate! }}</td>
                    <td>{{ rent.room!.name }}</td>
                    <td>
                        <HotelButton type="text"
                            @click="cancelReservation(rent)"
                        >Anuluj</HotelButton>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup lang="ts">
    import {ref, inject} from 'vue';
    import {useRouter} from 'vue-router';
    import HotelButton from '../components/HotelButton.vue';
    import ReservationManager from '../interfaces/ReservationManager';
    import { Rent } from '../interfaces/ReservationManager';

    const router = useRouter();
    const rentManager = inject('rentManager') as ReservationManager;
    let reservations = ref([] as Rent[]);

    const a = async () => {
        reservations.value = await rentManager.getMyRents();
    }
    a();

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