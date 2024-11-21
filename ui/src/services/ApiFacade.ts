import AdditionalEquipmentManager from "../interfaces/AdditionalEquipmentManager";
import DiscountManager from "../interfaces/DiscountManager";
import EquipmentManager from "../interfaces/EquipmentManager";
import LoginInterface from "../interfaces/LoginInterface";
import PricingIncreaseManager from "../interfaces/PricingIncreaseManager";
import RoomManager from "../interfaces/RoomManager";
import { Room } from "../interfaces/RoomManager"
import ApiParamsFactory from "./ApiParamsFactory";
import ApiService from "./ApiService";
import { PricingIncrease } from "../interfaces/PricingIncreaseManager";
import { Equipment } from "../interfaces/EquipmentManager";
import { Discount } from "../interfaces/DiscountManager";
import ReservationManager from "../interfaces/ReservationManager";
import { Rent } from "../interfaces/ReservationManager";

export default class ApiFacade implements 
LoginInterface, 
RoomManager,
EquipmentManager,
AdditionalEquipmentManager,
PricingIncreaseManager,
DiscountManager,
ReservationManager {
    private apiService;
    private apiParamsFactory;
    private loginCallback: ((isLogged: boolean) => void)[] = [];
    
    constructor(apiBase: string) {
        this.apiService = new ApiService();
        this.apiParamsFactory = new ApiParamsFactory(apiBase);
        this.apiParamsFactory.setAuthToken(localStorage.getItem("authToken") ?? '');
    }

    public isLogged(): boolean {
        let token = localStorage.getItem('authToken');

        return !!token && token.length != 0;
    }

    public hasPrivilege(privilege: string): boolean {
        let token = localStorage.getItem('authToken')?.split(":");

        return Array.isArray(token) && token.length == 3 && token[2] == privilege;
    }

    public onLoginChange(callback: (isLogged: boolean) => void) {
        this.loginCallback.push(callback);
    }

    public async login(login: string, password: string) {
        let object = await this.apiService.request(this.apiParamsFactory.login(login, password))
        if(!object.token) {
            throw new Error("Something went wrong!");
        }
        localStorage.setItem('authToken', object.token);
        this.invokeCallback(true);
    }

    public logout(): void {
        localStorage.clear();
        this.apiParamsFactory.setAuthToken('');
        this.invokeCallback(false);
    }

    private invokeCallback(isLogged: boolean) {
        this.loginCallback.forEach(cb => cb(isLogged));
    }

    public async register(login: string, password: string) {
        let object = await this.apiService.request(this.apiParamsFactory.register(login, password))
        
        if(!object.status || object.status != "ok") {
            throw new Error("Something went wrong!");
        }
    }


    public async getRooms(): Promise<Room[]> {
        return await this.apiService.request(
            this.apiParamsFactory.getRooms()
        );
    }

    public async getRoom(id: number): Promise<Room> {
        return await this.apiService.request(
            this.apiParamsFactory.getRoom(id)
        );
    }

    public async saveRoom(room: Room): Promise<void> {
        return await this.apiService.request(
            this.apiParamsFactory.saveRoom(room)
        )
    }

    public async updateRoom(room: Room): Promise<void> {
        return await this.apiService.request(
            this.apiParamsFactory.updateRoom(room)
        )
    }

    public async getAdditionalEquipment(): Promise<Equipment> {
        return await this.apiService.request(
            this.apiParamsFactory.getAdditionalEquipment()
        );
    }

    public async getPricingForAdditionalEquipment(id: number): Promise<PricingIncrease> {
        return await this.apiService.request(
            this.apiParamsFactory.getPricingForAdditionalEquipment(id)
        )
    }

    public async saveAdditionalEquipment(equipment: Equipment): Promise<void> {
        throw new Error("NOT IMPLEMENTED")
    }

    public async updateAdditionalEquipment(equipment: Equipment): Promise<void> {
        throw new Error("NOT IMPLEMENTED")
    }

    public async getDiscountForEquipment(id: number): Promise<Discount> {
        throw new Error("NOT IMPLEMENTED")
    }

    public async getDiscountForAdditionalEquipment(id: number): Promise<Discount> {
        throw new Error("NOT IMPLEMENTED")
    }

    public async saveDiscount(increase: Discount): Promise<void> {
        throw new Error("NOT IMPLEMENTED")
    }
    
    public async updateDiscount(increase: Discount): Promise<void> {
        throw new Error("NOT IMPLEMENTED")
    }

    public async getEquipmentForRoom(id: number): Promise<Equipment[]> {
        throw new Error("NOT IMPLEMENTED")
    }
    
    public async getAdditionalPricingForEquipment(id: number): Promise<PricingIncrease> {
        throw new Error("NOT IMPLEMENTED")
    }
    
    public async saveEquipment(equipment: Equipment): Promise<void> {
        throw new Error("NOT IMPLEMENTED")
    }
    
    public async updateEquipment(equipment: Equipment): Promise<void> {
        throw new Error("NOT IMPLEMENTED")
    }

    public async getPricingIncreaseForEquipment(id: number): Promise<PricingIncrease> {
        throw new Error("NOT IMPLEMENTED")
    }
    
    public async getPricingIncreaseForAdditionalEquipment(id: number): Promise<PricingIncrease> {
        throw new Error("NOT IMPLEMENTED")
    }
    
    public async savePricingIncrease(increase: PricingIncrease): Promise<void> {
        throw new Error("NOT IMPLEMENTED")
    }
    
    public async updatePricingIncrease(increase: PricingIncrease): Promise<void> {
        throw new Error("NOT IMPLEMENTED")
    }

    public async rentRoom(rent: Rent, price: number): Promise<void> {
        return await this.apiService.request(
            this.apiParamsFactory.rentRoom(rent, price)
        );
    }

    public async cancelRent(rent: Rent): Promise<void> {
        return await this.apiService.request(
            this.apiParamsFactory.cancelRent(rent)
        );
    }

    public async getRents(): Promise<Rent[]> {
        return await this.apiService.request(
            this.apiParamsFactory.getRents()
        );    
    }

    public async getMyRents(): Promise<Rent[]> {
        return await this.apiService.request(
            this.apiParamsFactory.getMyRents()
        );    
    }
}