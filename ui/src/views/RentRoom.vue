<template>
    <div class="container">
        <HotelInput 
            label="Od"
            name="password" 
            type="date" 
            v-model="password"
        ></HotelInput>

        <HotelInput 
            label="Do"
            name="password" 
            type="date" 
            v-model="password"
        ></HotelInput>

        
        <HotelInput
            label="checkbox"
            type="checkbox"
        ></HotelInput>
    </div>
</template>

<script setup lang="ts">
    import {inject} from 'vue'
    import HotelInput from "../components/HotelInput.vue";
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