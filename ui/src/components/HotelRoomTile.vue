<template>
    <div class="room">
        <div class="roomImg">
            <img src="https://images.pexels.com/photos/1571458/pexels-photo-1571458.jpeg?cs=srgb&dl=pexels-vecislavas-popa-1571458.jpg&fm=jpg"/>
        </div>
        <div class="hr"></div>
        <HotelH2>{{ props.room.name }}</HotelH2>
        <HotelP>{{ props.room.description }}</HotelP>
        <div class="hr"></div>
        <div class="params">
            <div>
                <img class="icon" src="/square.svg"/>
                {{ props.room.roomSize }}
            </div>
            <div>
                <img class="icon" src="/people.svg"/>
                {{ props.room.capacity }}
            </div>
            <div>
                <img class="icon" src="/door.svg"/>
                {{ props.room.roomNumber }}
            </div>
            <div>
                Od: 
                {{ props.room.basePrice }}zł
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
    import { defineProps } from 'vue';
    import { Room } from '../interfaces/RoomManager';
    import HotelH2 from '../components/HotelH2.vue';
    import HotelP from '../components/HotelP.vue';

    const props = defineProps<{
        room: Room
    }>()
</script>

<style>
    .room {
        width: 500px;
        min-height: 500px;
        border-radius: 10px;
        display: flex;
        flex-direction: column;
        border: 2px solid #f2f2f2;
        overflow: hidden;
        align-items: center;
        gap: 10px;
        padding: 0 20px 20px 20px;
        box-shadow: 0px 0px 20px -18px black;
        transition: all 0.3s ease;
        cursor: pointer;
    }

    .room:hover {
        box-shadow: 0px 0px 20px -10px black;
    }

    .roomImg {
        width: 500px;
        height: 250px;
    }

    .hr {
        width: 100%;
        padding: 0 10px;
        border-bottom: 1px solid #d2d2d2;
    }
    
    img {
        object-fit: cover;
        width: 100%;
        height: 100%;
    }

    .icon {
        width: 25px;
        height: 25px;
    }

    .params {
        width: 100%;
        display: flex;
        justify-content: space-around;
    }
</style>