import Home from '../views/Home.vue';
import Login from '../views/Login.vue';
import Register from '../views/Register.vue';
import Offer from '../views/Offer.vue';
import OfferRoom from '../views/OfferRoom.vue';
import RentRoom from '../views/RentRoom.vue';
import MyRents from '../views/MyRents.vue';
import ManageRooms from '../views/admin/ManageRooms.vue';
import ManageEquipments from '../views/admin/ManageEquipments.vue';
import ManageAdditionalEquipments from '../views/admin/ManageAdditionalEquipments.vue';
import ManageDiscounts from '../views/admin/ManageDiscounts.vue';
import ManageHotel from '../views/admin/ManageHotel.vue';
import { RouteRecordRaw } from 'vue-router';

export default [
    {path: '/', component: Home},
    {path: '/login', component: Login},
    {path: '/register', component: Register},
    {path: '/manageRooms', component: ManageRooms},
    {path: '/manageEquipments', component: ManageEquipments},
    {path: '/manageAdditionalEquipments', component: ManageAdditionalEquipments},
    {path: '/manageDiscounts', component: ManageDiscounts},
    {path: '/manageHotel', component: ManageHotel},
    {path: '/offer', component: Offer},
    {path: '/room/:id', component: OfferRoom},
    {path: '/rent/:id', component: RentRoom},
    {path: '/myRents/:id', component: MyRents}
] as RouteRecordRaw[]