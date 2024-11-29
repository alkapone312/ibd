<template>
    <HotelH1 class="header-of-page">Zarządzanie hotelem</HotelH1>
    <div id="hotelManagement">
        <RouterLink class="manage-link" type="plain" to="/manageRents"><ManagementTile>Zarządzaj rezerwacjami</ManagementTile></RouterLink>
        <RouterLink class="manage-link" type="plain" to="/manageRooms"><ManagementTile>Zarządzaj pokojami</ManagementTile></RouterLink>
        <RouterLink class="manage-link" type="plain" to="/manageEquipments"><ManagementTile>Zarządzaj ewkipunkiem</ManagementTile></RouterLink>
        <RouterLink class="manage-link" type="plain" to="/manageAdditionalEquipments"><ManagementTile>Zarządzaj dodatkowym ekwipunkiem</ManagementTile></RouterLink>
        <RouterLink class="manage-link" type="plain" to="/manageEquipmentInRoom"><ManagementTile>Zarządzaj ekwipunkiem w pokojach</ManagementTile></RouterLink>
    </div>
</template>

<script setup lang="ts">
    import HotelH1 from '../../components/HotelH1.vue'
    import ManagementTile from '../../components/ManagementTile.vue'
</script>

<style scoped>
    #hotelManagement {
        display: flex;
        flex-wrap: wrap;
        align-items: center;
        gap: 20px;
        justify-content: center;
    }

    .manage-link {
        font-size: 36px;
    }
</style>