<template>
    <div class="container">
        <form @submit.prevent="onSubmit()">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nazwa</th>
                        <th scope="col">Wzrotst ceny</th>
                        <th scope="col">Typ wzrostu</th>
                        <th scope="col">Dodaj</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="equipment in equipments" scope="row">
                        <th>{{ equipment.id }}</th>
                        <td>{{ equipment.name }}</td>
                        <td>{{ equipment.increase }}</td>
                        <td>{{ equipment.increaseType }}</td>
                        <td><HotelInput type="checkbox" v-model="equipment.checked"></HotelInput></td>
                    </tr>
                </tbody>
            </table>

        <div style="display: flex; width: 100%; justify-content: space-between;">
            <HotelButton type="text">Zapisz</HotelButton>
            <HotelButton @click.prevent="() => {router.go(-1)}" type="text">Cofnij</HotelButton>
            <HotelLink type="text" to="/manageEquipments/add">Dodaj ekwipunek</HotelLink>
        </div>
        </form>
    </div>
</template>

<script lang="ts" setup>
    import HotelButton from '../../components/HotelButton.vue'
    import HotelInput from '../../components/HotelInput.vue'
    import HotelLink from '../../components/HotelLink.vue'
    import EquipmentManager from '../../interfaces/EquipmentManager'
    import { inject, ref } from 'vue'
    import { useRoute, useRouter } from 'vue-router'

    const route = useRoute();
    const router = useRouter();
    const equipmentManager = inject('equipmentManager') as EquipmentManager;
    let equipments = ref([] as any);
    
    const a = async () => {
        //@ts-ignore
        let equipmentInRoom = await equipmentManager.getEquipmentForRoom(route.params.id);
        equipments.value = await equipmentManager.getEquipment();
        for(let i = 0 ; i < equipments.value.length; i++) {
            equipments.value[i] = {
                ...equipments.value[i], 
                //@ts-ignore
                ...(await equipmentManager.getAdditionalPricingForEquipment(equipments.value[i].id))
            }
            equipments.value[i].increaseType = equipments.value[i].type;
            equipments.value[i].checked = equipmentInRoom.some(item => item.id == equipments.value[i].id)
        }
    };
    a();

    const onSubmit = async () => {
        console.log(equipments.value)
        let toSubmit = equipments.value.filter((item: any) => {
            return item.checked
        }) 
        //@ts-ignore
        await equipmentManager.deleteEquipmentFromRoom(route.params.id)
        toSubmit.forEach(async (item: any) => {
            //@ts-ignore
            await equipmentManager.addEquipmentToRoom(item, route.params.id)
        })
        router.go(-1)
    }
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