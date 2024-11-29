<template>
    <div>
        <RouterLink :to="props.to" 
            :class="{
                'btn': true, 
                'btn-filled': props.type == 'filled',
                'btn-text': props.type == 'text',
                'btn-plain': props.type == 'plain'
            }">
            <slot></slot>
        </RouterLink>
    </div>
</template>

<script setup lang="ts">
    let props = defineProps<{
        to: string
        type?: string
    }>();
</script>

<style scoped>
    .btn {
        font-size: 24px;
        font-weight: 400;
        padding: 5px 5px;
        margin: 5px;
        color: white;
        background-color: #5fa1eb;
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
    }

    .btn-filled:hover {
        background-color: #CCC;
    }

    .btn-text:hover {
        background-color: #CCC;
    }
</style>