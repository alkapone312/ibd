<template>
    <div class="inputGroup">
        <label class="label">{{ props.label }}</label>
        <input :step="props.step ?? undefined" :name="props.name" :type="props.type" v-model="model" class="input">
    </div>
</template>

<script setup lang="ts">
import { defineProps, defineModel } from 'vue';

const props = defineProps<{
    type?: string,
    name?: string,
    label?: string
    step?: string | null
}>();

const model = defineModel();

</script>

<style scoped>
    .inputGroup {
        position: relative;
        width: 100%;
    }

    .input {
        min-height: 20px;
        padding: 30px 20px 10px 20px;
        font-size: 24px;
        width: 100%;
        outline: none;
        border: 1px solid #d5d8de;
        border-radius: 5px;
    }

    .label {
        position: relative;
        top: 25px;
        left: 10px;
    }
</style>