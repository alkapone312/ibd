<template>
    <div id="homepage">
        <div class="container">
            <HotelH1>Witamy w naszym hotelu!</HotelH1>
            <HotelP>Witamy w Hotel.com – Twoim miejscu pełnym luksusu, komfortu i 
                niezapomnianych wrażeń! Jesteśmy dumni, że możemy gościć Cię w naszym
                wyjątkowym hotelu, gdzie każdy detal został starannie zaplanowany, abyś 
                czuł się jak w domu, ale jednocześnie otoczony luksusem i wygodą.</HotelP>

            <HotelP>Nasz hotel położony jest w malowniczej okolicy, otoczonej pięknem 
                przyrody oraz blisko głównych atrakcji turystycznych. Bez względu na to, czy 
                podróżujesz służbowo, czy też wypoczywasz z rodziną, zawsze mamy coś 
                wyjątkowego do zaoferowania.</HotelP>

            <HotelP>Nasi pracownicy, pełni pasji i zaangażowania, są gotowi spełnić 
                wszelkie Twoje oczekiwania, by Twój pobyt był niezapomniany. Nasze 
                nowoczesne pokoje zostały starannie urządzone, aby zapewnić Ci 
                komfort i spokój, a nasza obsługa dba o najmniejsze szczegóły, abyś 
                mógł cieszyć się chwilą.</HotelP>

            <HotelP>Nasz hotel oferuje również bogatą gamę udogodnień, w tym eleganckie 
                restauracje serwujące wykwintne dania, przytulne spa, basen oraz sale 
                konferencyjne idealne do organizacji spotkań biznesowych czy 
                wydarzeń specjalnych.</HotelP>

            <HotelP>Zapraszamy Cię do odkrywania naszych atrakcji i korzystania z 
                bogatej oferty, jaką przygotowaliśmy specjalnie dla Ciebie. Niezależnie
                od tego, czy jesteś tutaj na jedną noc czy na dłużej, nasz hotel zawsze
                jest gotów, aby uczynić Twój pobyt wyjątkowym.</HotelP>

            <HotelP>Dziękujemy, że wybrałeś Hotel.com. Jesteśmy przekonani, 
                że z nami zaznasz prawdziwej przyjemności podróżowania i odpoczynku.</HotelP>
        </div>
    </div>
</template>

<script setup lang="ts">
    import HotelH1 from '../components/HotelH1.vue';
    import HotelP from '../components/HotelP.vue';
</script>

<style>
    #homepage {
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-top: 10%;
    }

    .container {
        width: 1000px;
        display: flex;
        flex-direction: column;
        align-items: center;
    }
</style>