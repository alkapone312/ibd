
<template>
    <div class="container">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nazwa</th>
                    <th scope="col">Wzrotst ceny</th>
                    <th scope="col">Typ wzrostu</th>
                    <th scope="col">Edytuj</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="equipment in equipments" scope="row">
                    <th>{{ equipment.id }}</th>
                    <td>{{ equipment.name }}</td>
                    <td>{{ equipment.increase }}</td>
                    <td>{{ equipment.increaseType }}</td>
                    <td><HotelLink type="text" :to="'/manageAdditionalEquipments/edit/' + equipment.id">Edytuj</HotelLink></td>
                </tr>
            </tbody>
        </table>
        <div style="display: flex; width: 100%; justify-content: space-between;">
            <HotelButton @click.prevent="() => {router.go(-1)}" type="text">Cofnij</HotelButton>
            <HotelLink type="text" to="/manageAdditionalEquipments/add">Dodaj dodatkowy ekwipunek</HotelLink>
        </div>
    </div>
</template>

<script lang="ts" setup>
    import HotelLink from '../../components/HotelLink.vue'
    import HotelButton from '../../components/HotelButton.vue'
    import AdditionalEquipmentManager from '../../interfaces/AdditionalEquipmentManager'
    import { inject, ref } from 'vue'
    import { useRouter } from 'vue-router'

    const router = useRouter();
    const equipmentManager = inject('additionalEquipmentManager') as AdditionalEquipmentManager;
    let equipments = ref([] as any);
    
    const a = async () => {
        equipments.value = await equipmentManager.getAdditionalEquipment();
        for(let i = 0 ; i < equipments.value.length; i++) {
            equipments.value[i] = {
                ...equipments.value[i], 
                ...(await equipmentManager.getPricingForAdditionalEquipment(equipments.value[i].id))
            }
            equipments.value[i].increaseType = equipments.value[i].type;
        }
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