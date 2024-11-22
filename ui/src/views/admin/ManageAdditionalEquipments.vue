<template>
    <div class="container">
        <HotelLink type="text" to="/manageAdditionalEquipments/add">Dodaj dodatkowy ekwipunek</HotelLink>
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
    </div>
</template>

<script lang="ts" setup>
    import HotelH1 from '../../components/HotelH1.vue'
    import HotelLink from '../../components/HotelLink.vue'
    import EquipmentManager from '../../interfaces/EquipmentManager'
    import { inject, ref } from 'vue'

    const equipmentManager = inject('additionalEquipmentManager') as EquipmentManager;
    let equipments = ref([]);
    
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