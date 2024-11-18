<template>
    <div id="hotelManagement">
        <HotelH1>Zarządzanie hotelem</HotelH1><div></div>
        <HotelLink type="plain" to="/manageRooms"><ManagementTile>Zarządzaj pokojami</ManagementTile></HotelLink>
        <HotelLink type="plain" to="/manageEquipments"><ManagementTile>Zarządzaj ewkipunkiem</ManagementTile></HotelLink>
        <HotelLink type="plain" to="/manageAdditionalEquipments"><ManagementTile>Zarządzaj dodatkowym ekwipunkiem</ManagementTile></HotelLink>
        <HotelLink type="plain" to="/manageDiscounts"><ManagementTile>Zarządzaj zniżkami</ManagementTile></HotelLink>
    </div>
</template>

<script setup lang="ts">
    import HotelH1 from '../../components/HotelH1.vue'
    import HotelLink from '../../components/HotelLink.vue'
    import ManagementTile from '../../components/ManagementTile.vue'
</script>

<style scoped>
    #hotelManagement {
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 20px;
    }
</style>