import Home from '../views/Home.vue';
import Login from '../views/Login.vue';
import Register from '../views/Register.vue';
import Offer from '../views/Offer.vue';
import OfferRoom from '../views/OfferRoom.vue';
import RentRoom from '../views/RentRoom.vue';
import MyRents from '../views/MyRents.vue';
import ManageRooms from '../views/admin/ManageRooms.vue';
import ManageRents from '../views/admin/ManageRents.vue';
import ManageEquipments from '../views/admin/ManageEquipments.vue';
import ManageAdditionalEquipments from '../views/admin/ManageAdditionalEquipments.vue';
import ManageEquipmentInRooms from '../views/admin/ManageEquipmentInRooms.vue';
import ManageEquipmentInRoom from '../views/admin/ManageEquipmentInRoom.vue';
import ManageHotel from '../views/admin/ManageHotel.vue';
import AddEditRoom from '../views/admin/AddEditRoom.vue';
import AddEditEquipment from '../views/admin/AddEditEquipment.vue';
import AddEditAdditionalEquipment from '../views/admin/AddEditAdditionalEquipment.vue';
import { RouteRecordRaw } from 'vue-router';

export default [
    {path: '/', component: Home},
    {path: '/login', component: Login},
    {path: '/register', component: Register},
    {path: '/manageRents', component: ManageRents},
    {path: '/manageRooms', component: ManageRooms},
    {path: '/manageRooms/add', component: AddEditRoom},
    {path: '/manageRooms/edit/:id', component: AddEditRoom},
    {path: '/manageEquipments', component: ManageEquipments},
    {path: '/manageEquipments/add', component: AddEditEquipment},
    {path: '/manageEquipments/edit/:id', component: AddEditEquipment},
    {path: '/manageEquipmentInRoom', component: ManageEquipmentInRooms},
    {path: '/manageEquipmentInRoom/room/:id', component: ManageEquipmentInRoom},
    {path: '/manageAdditionalEquipments', component: ManageAdditionalEquipments},
    {path: '/manageAdditionalEquipments/add', component: AddEditAdditionalEquipment},
    {path: '/manageAdditionalEquipments/edit/:id', component: AddEditAdditionalEquipment},
    {path: '/manageHotel', component: ManageHotel},
    {path: '/offer', component: Offer},
    {path: '/room/:id', component: OfferRoom},
    {path: '/rent/:id', component: RentRoom},
    {path: '/myRents', component: MyRents}
] as RouteRecordRaw[]