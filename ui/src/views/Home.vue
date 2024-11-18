<template>
    <div id="homepage">
        <HotelH1>Witamy w naszym hotelu!</HotelH1>
        
    </div>
</template>

<script setup lang="ts">
    import HotelH1 from '../components/HotelH1.vue';
</script>

<style>
    #homepage {
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-top: 10%;
    }
</style>