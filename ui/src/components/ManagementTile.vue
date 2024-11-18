<template>
    <div class="tile"><slot></slot></div>
</template>

<script lang="ts" setup>
</script>

<style scoped>
.tile {
    width: 400px;
    height: 400px;
    border: 1px solid #d5d8de;
    border-radius: 5px;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    transition: all 0.3s ease;
}

.tile:hover {
    box-shadow: -5px 5px 10px -8px;
}
</style>