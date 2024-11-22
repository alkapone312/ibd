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

            <div style="padding: 20px 0;" v-if="equipments.length !== 0">
                Wyposażenie pokoju:
                <ul>
                    <li v-for="eq in equipments">{{ eq.name + ' +' + eq.increase + '%'}}</li>
                </ul>
            </div>

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
    import EquipmentManager from '../interfaces/EquipmentManager';  
    import { Room } from '../interfaces/RoomManager';
    import RoomManager from '../interfaces/RoomManager';
    import ReservationManager from '../interfaces/ReservationManager';

    
    const router = useRouter();
    const route = useRoute();
    const roomManager = inject('roomManager') as RoomManager;
    const additionalEquipmentManager = inject('additionalEquipmentManager') as AdditionalEquipmentManager;
    const equipmentManager = inject('equipmentManager') as EquipmentManager;
    const rentManager = inject('rentManager') as ReservationManager;
    let room = {} as Room;
    let additionalEquipment = ref([] as any);
    let equipments = ref([] as any);
    let additionalEquipmentCheckbox = {} as any;
    let price = ref(0);
    let rent: Rent = {
        id: 0,
        client_id: 0,
        room_id: 0,
        checkin_date: '',
        checkout_date: ''
    };

    const recalculatePrice = () => {
        price.value = room.basePrice * Math.max(calculateDaysDifference(rent.checkin_date, rent.checkout_date), 1);
        equipments.value.forEach((eq: any) =>{
            price.value += Math.ceil(price.value * eq.increase / 100);
        });
        Object.entries(additionalEquipmentCheckbox).forEach((checkbox) => {
            if(checkbox[1] == true) {
                price.value += Math.ceil(price.value * additionalEquipment.value[parseInt(checkbox[0])].increase / 100);
            }
        })
    }

    (async () => {
        //@ts-ignore
        room = await roomManager.getRoom(route.params.id);
        equipments.value = await equipmentManager.getEquipmentForRoom(room.id);
        for(let i = 0 ; i < equipments.value.length; i++) {
            equipments.value[i] = {
                ...equipments.value[i], 
                ...(await equipmentManager.getAdditionalPricingForEquipment(equipments.value[i].id))
            }
            equipments.value[i].increaseType = equipments.value[i].type;
        }

        rent.room_id = room.id;
        let aeq = await additionalEquipmentManager.getAdditionalEquipment();
        let aeq2 = {} as any;
        for(let i = 0 ; i < aeq.length; i++) {
            aeq2[aeq[i].id] = {...aeq[i], ...(await additionalEquipmentManager.getPricingForAdditionalEquipment(aeq[i].id))}
        }
        additionalEquipment.value = aeq2;
        Object.values(aeq2).forEach((element: any) => {
            additionalEquipmentCheckbox[element.id] = false;
        });

        recalculatePrice()
    })()

    const onSubmit = () => {
        rentManager.rentRoom(rent, price.value);
        router.push("/offer");
    }
    //@ts-ignore
    const calculateDaysDifference = (startDate, endDate) => Math.floor((new Date(endDate) - new Date(startDate)) / (1000 * 60 * 60 * 24));

    

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