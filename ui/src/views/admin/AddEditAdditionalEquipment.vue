<template>
    <div class="container">
        <HotelH3>Dodaj/edytuj dodatkowy ekwipunek</HotelH3>
        <form @submit.prevent="onSubmit">
            <HotelInput label="Nazwa" name="name" type="text" v-model="equipment.name"></HotelInput>
            <HotelInput label="Wzrost ceny" name="description" type="number" v-model="equipment.increase"></HotelInput>
            <HotelInput label="Typ wzrostu" name="equipmentSize" type="text" v-model="equipment.increaseType"></HotelInput>
            <HotelButton type="text">Zapisz</HotelButton>
            <HotelButton @click.prevent="() => {router.go(-1)}" type="text">Cofnij</HotelButton>
        </form>
    </div>
</template>

<script setup lang="ts">
import HotelInput from "../../components/HotelInput.vue";
import HotelButton from "../../components/HotelButton.vue";
import HotelH3 from "../../components/HotelH3.vue";
import AdditionalEquipmentManager from '../../interfaces/AdditionalEquipmentManager';
import {useRoute, useRouter} from 'vue-router';
import { inject, ref } from 'vue'

const router = useRouter();
const route = useRoute();
let equipment = ref({
    id: 0,
    name: '',
    increase: 0,
    increaseType: 'percent'
});

const additionalEquipmentManager = inject('additionalEquipmentManager') as AdditionalEquipmentManager;

if(route.params.id && typeof route.params.id == 'string') {
    const a = async () => {
        // @ts-ignore
        let r = await additionalEquipmentManager.getOneAdditionalEquipment(parseInt(route.params.id))
        let i = await additionalEquipmentManager.getPricingForAdditionalEquipment(r.id);
        equipment.value.id = r.id;
        equipment.value.name = r.name;
        equipment.value.increase = i.increase;
        equipment.value.increaseType = i.type;
    }
    a();
}

const onSubmit = () => {
    const increase = {"increase": equipment.value.increase, "type": equipment.value.increaseType}
    if(route.params.id && typeof route.params.id == 'string')
        additionalEquipmentManager.updateAdditionalEquipment(equipment.value, increase);
    else
        additionalEquipmentManager.saveAdditionalEquipment(equipment.value, increase);
    router.go(-1)
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