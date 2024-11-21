<template>
<div>
    <div class="top">
        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M15 4L7 12L15 20" stroke="black" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
        </svg>
        <p>September</p>
        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M9 4L17 12L9 20" stroke="black" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
        </svg>
    </div>
    <div class="down">
        <p>Mo</p>
        <p>Tu</p>
        <p>We</p>
        <p>Th</p>
        <p>Fri</p>
        <p>Sa</p>
        <p>Su</p>

        <p>1</p>
        <p>2</p>
        <p>3</p>
        <p>4</p>
        <p>5</p>
        <p>6</p>
        <p>7</p>
        <p>8</p>
        <p>9</p>
        <p>10</p>
        <p>11</p>
        <p>12</p>
        <p>13</p>
        <p>14</p>
        <p>15</p>
        <p>16</p>
        <p>17</p>
        <p>18</p>
        <p>19</p>
        <p>20</p>
        <p>21</p>
        <p>22</p>
        <p>23</p>
        <p>24</p>
        <p>25</p>
        <p>26</p>
        <p>27</p>
        <p>28</p>
        <p>29</p>
        <p>30</p>
        <p>31</p>
        <p>1</p>
        <p>2</p>
        <p>3</p>
        <p>4</p>
    </div>
</div>
</template>

<script setup lang="ts">
</script>

<style>
    .top {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 1em;
    }

    .top p {
    font-size: 2rem;
    font-weight: 400;
    }

    .top svg  span{
    background-color: #E1E9F0;
    padding: .7em .9em;
    border-radius: .4em;
    }

    .down {
    display: grid;
    grid-template-columns: repeat(7, auto);
    
    justify-content: space-between;
    text-align: center;
    } 

    .down p{
    font-size: 1.7rem;
    font-weight: 400;
    }

    .down p:nth-child(-n + 7) {
    font-weight: bold;
    }

    .down p:nth-last-of-type(-n + 4) {
    color: #999999;
    }
</style>