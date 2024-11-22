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
    font-size: 32px;
    font-weight: 400;
    padding: 10px 5px;
    color: #1C94AE;
    border-radius: 5px;
    transition: all 0.5s ease;
    box-shadow: -10px 5px 20px -15px black;
}

.btn:hover {
    box-shadow: -10px 5px 20px -5px black;
}

.btn-plain {
    box-shadow: unset;
}

.btn-plain:hover {
    box-shadow: unset;
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