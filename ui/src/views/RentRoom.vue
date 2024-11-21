<template>
    <div class="container">
        <form @submit.prevent="onSubmit">
            <HotelInput 
                @change="recalculatePrice"
                label="Od"
                name="dateFrom" 
                type="date" 
                v-model="rent.checkin_date"
            ></HotelInput>

            <HotelInput 
                @change="recalculatePrice"
                label="Do"
                name="dateTo" 
                type="date" 
                v-model="rent.checkout_date"
            ></HotelInput>

            <HotelInput
                @change="recalculatePrice"
                v-for="eq in additionalEquipment"
                :key="eq.id"
                :label="eq.name + ' +' + eq.increase + (eq.type == 'percent' ? '%' : 'zł')"
                name="checkbox"
                type="checkbox"
                v-model="additionalEquipmentCheckbox[eq.id]"
            ></HotelInput>

            <HotelH2>Cena: {{ price }}zł</HotelH2>
            <HotelButton type="text">Wynajmij</HotelButton>
        </form>
    </div>
</template>

<script setup lang="ts">
    import { inject, ref } from 'vue'
    import { useRoute, useRouter } from 'vue-router'
    import { Rent } from '../interfaces/ReservationManager';
    import HotelInput from "../components/HotelInput.vue";
    import HotelButton from "../components/HotelButton.vue";
    import HotelH2 from "../components/HotelH2.vue";
    import AdditionalEquipmentManager from '../interfaces/AdditionalEquipmentManager';
    import ReservationManager from '../interfaces/ReservationManager';

    
    const router = useRouter();
    const route = useRoute();
    const roomManager = inject('roomManager') as AdditionalEquipmentManager;
    const additionalEquipmentManager = inject('additionalEquipmentManager') as AdditionalEquipmentManager;
    const rentManager = inject('rentManager') as ReservationManager;
    let room = {};
    let additionalEquipment = ref([]);
    let additionalEquipmentCheckbox = {};
    let price = ref(0);
    let rent: Rent = {
        id: 0,
        client_id: 0,
        room_id: 0,
        checkin_date: new Date(),
        checkout_date: new Date()
    };
    (async () => {
        room = await roomManager.getRoom(route.params.id);
        price.value = room.basePrice;
        rent.room_id = room.id;
        let eq = await additionalEquipmentManager.getAdditionalEquipment();
        let eq2 = {}
        for(let i = 0 ; i < eq.length; i++) {
            eq2[eq[i].id] = {...eq[i], ...(await additionalEquipmentManager.getPricingForAdditionalEquipment(eq[i].id))}
        }
        additionalEquipment.value = eq2;
        console.log(eq2);
        Object.values(eq2).forEach(element => {
            additionalEquipmentCheckbox[element.id] = false;
        });
    })()

    const onSubmit = () => {
        rentManager.rentRoom(rent, price.value);
        router.push("/offer");
    }
    const calculateDaysDifference = (startDate, endDate) => Math.floor((new Date(endDate) - new Date(startDate)) / (1000 * 60 * 60 * 24));

    const recalculatePrice = () => {
        price.value = room.basePrice * Math.max(calculateDaysDifference(rent.checkin_date, rent.checkout_date), 1);
        Object.entries(additionalEquipmentCheckbox).forEach((checkbox) => {
            if(checkbox[1] == true) {
                price.value += Math.ceil(price.value * additionalEquipment.value[checkbox[0]].increase / 100);
            }
        })
    }

</script>

<style scoped>
.container {
        width: 80%;
        margin: 10%;
        display: flex;
        flex-direction: column;
        gap: 30px;
    }
</style>