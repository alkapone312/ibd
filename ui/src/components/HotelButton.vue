<template>
    <div>
        <button 
            :class="{
                'btn': true, 
                'btn-filled': props.type == 'filled',
                'btn-text': props.type == 'text'
            }
        ">
            <slot></slot>
        </button>
    </div>
</template>

<script setup lang="ts">
import { defineProps } from 'vue';

const props = defineProps<{
    type: string | null
}>()

</script>

<style scoped>
    .btn {
        font-size: 32px;
        font-weight: 400;
        padding: 10px 5px;
        color: #1C94AE;
        border-radius: 5px;
        transition: all 0.5s ease;
        box-shadow: -10px 5px 20px -15px black;
        cursor: pointer;
    }

    .btn:hover {
        box-shadow: -10px 5px 20px -5px black;
    }

    .btn-filled {
        background-color: white;
    }

    .btn-text {
        box-shadow: unset;
        text-shadow: -2px 2px 10px rgba(0,0,0,0.15);
        background-color: unset;
    }

    .btn-filled:hover {
        background-color: #CCC;
    }

    .btn-text:hover {
        background-color: #CCC;
    }
</style>