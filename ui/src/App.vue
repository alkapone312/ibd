<template>
	<nav>
		<HotelH2>Hotel.com</HotelH2>
		<HotelLink 
			type="filled" 
			to="/manageHotel" 
			v-if="isLogged && apiFacade.hasPrivilege('ADMIN')"
			>Zarządzaj hotelem</HotelLink>
		<HotelLink
			type="filled" 
			to="/"
			>Strona główna</HotelLink>
		<HotelLink 
			type="filled" 
			to="/offer" 
			>Oferta</HotelLink>
		<HotelLink 
			type="filled" 
			to="/myRents" 
			v-if="isLogged"
			>Moje rezerwacje</HotelLink>
		<HotelButton 
			type="filled" 
			v-if="isLogged" 
			@click="() => {apiFacade.logout()}"
			>Wyloguj</HotelButton>
		<HotelLink 
			type="filled" 
			to="/login" 
			v-if="!isLogged"
			>Zaloguj</HotelLink>
		<HotelLink 
			type="filled" 
			to="/register" 
			v-if="!isLogged"
			>Zarejestruj</HotelLink>
	</nav>
	<main>
		<RouterView></RouterView>
	</main>
</template>

<script setup lang="ts">
import { inject, ref } from 'vue';
import HotelLink from './components/HotelLink.vue';
import HotelButton from './components/HotelButton.vue';
import HotelH2 from './components/HotelH2.vue';
import LoginInterface from './interfaces/LoginInterface';

const apiFacade = inject('login') as LoginInterface

let isLogged = ref(apiFacade.isLogged());
apiFacade.onLoginChange((isLoggedNew) => {
	isLogged.value = isLoggedNew
})

</script>

<style scoped>
	nav {
		height: 100px;
		width: 100%;
		padding: 20px;
		background-color: #006CE4;
		display: flex;
		justify-content: end;
		align-items: center;
		gap: 20px;
	}
</style>
