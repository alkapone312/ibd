import AdditionalEquipmentManager from "../interfaces/AdditionalEquipmentManager";
import EquipmentManager from "../interfaces/EquipmentManager";
import LoginInterface from "../interfaces/LoginInterface";
import RoomManager from "../interfaces/RoomManager";
import { Room } from "../interfaces/RoomManager"
import ApiParamsFactory from "./ApiParamsFactory";
import ApiService from "./ApiService";
import { PricingIncrease } from "../interfaces/PricingIncreaseManager";
import { Equipment } from "../interfaces/EquipmentManager";
import ReservationManager from "../interfaces/ReservationManager";
import { Rent } from "../interfaces/ReservationManager";
import NotificationService from "./NotificationService";

export default class ApiFacade implements 
LoginInterface, 
RoomManager,
EquipmentManager,
AdditionalEquipmentManager,
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
            NotificationService.pushNotification({"type": "error", "message": "Coś poszło nie tak!"});
            return;
        }
        this.apiParamsFactory.setAuthToken(object.token);
        localStorage.setItem('authToken', object.token);
        this.invokeCallback(true);
        NotificationService.pushNotification({"type": "success", "message": "Pomyślnie zalogowano!"});
    }

    public logout(): void {
        localStorage.clear();
        this.apiParamsFactory.setAuthToken('');
        NotificationService.pushNotification({"type": "success", "message": "Wylogowano"});
        this.invokeCallback(false);
    }

    private invokeCallback(isLogged: boolean) {
        this.loginCallback.forEach(cb => cb(isLogged));
    }

    public async register(login: string, password: string) {
        let object = await this.apiService.request(this.apiParamsFactory.register(login, password))
        
        if(!object.status || object.status != "ok") {
            NotificationService.pushNotification({"type": "error", "message": "Coś poszło nie tak!"});
        } else {
            NotificationService.pushNotification({"type": "success", "message": "Pomyślnie zarejestrowano!"});
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
        if((await this.apiService.request(
            this.apiParamsFactory.saveRoom(room)
        )).id == room.id) {
            NotificationService.pushNotification({"type": "success", "message": "Pomyślnie utworzono pokój!"})
            return
        }

        NotificationService.pushNotification({"type": "error", "message": "Coś poszło nie tak!"})
    }

    public async updateRoom(room: Room): Promise<void> {
        if((await this.apiService.request(
            this.apiParamsFactory.updateRoom(room)
        )).id == room.id) {
            NotificationService.pushNotification({"type": "success", "message": "Pomyślnie edytowano pokój!"})
            return
        }

        NotificationService.pushNotification({"type": "error", "message": "Coś poszło nie tak!"})
        
    }

    public async getAdditionalEquipment(): Promise<Equipment[]> {
        return await this.apiService.request(
            this.apiParamsFactory.getAdditionalEquipment()
        );
    }

    public async getOneAdditionalEquipment(id: number): Promise<Equipment> {
        return await this.apiService.request(
            this.apiParamsFactory.getOneAdditionalEquipment(id)
        );
    }

    public async getAdditionalEquipmentForReservation(reservation_id: number): Promise<Equipment[]> {
        return await this.apiService.request(
            this.apiParamsFactory.getAdditionalEquipmentForReservation(reservation_id)
        );
    }

    public async getPricingForAdditionalEquipment(id: number): Promise<PricingIncrease> {
        return await this.apiService.request(
            this.apiParamsFactory.getPricingForAdditionalEquipment(id)
        )
    }

    public async saveAdditionalEquipment(equipment: Equipment, pricingIncrease: PricingIncrease): Promise<void> {
        if((await this.apiService.request(
            this.apiParamsFactory.saveAdditionalEquipment(equipment, pricingIncrease)
        )).id == equipment.id) {
                NotificationService.pushNotification({"type": "success", "message": "Pomyślnie dodano ekwipunek!"})
        } else {
            NotificationService.pushNotification({"type": "error", "message": "Coś poszło nie tak!"})   
        }
    }

    public async updateAdditionalEquipment(equipment: Equipment, pricingIncrease: PricingIncrease): Promise<void> {
        if((await this.apiService.request(
            this.apiParamsFactory.updateAdditionalEquipment(equipment, pricingIncrease)
        )).id == equipment.id) {
                NotificationService.pushNotification({"type": "success", "message": "Pomyślnie edytowano ekwipunek!"})
        } else {
            NotificationService.pushNotification({"type": "error", "message": "Coś poszło nie tak!"})      
        }
    }

    public async getEquipmentForRoom(id: number): Promise<Equipment[]> {
        return await this.apiService.request(
            this.apiParamsFactory.getEquipmentForRoom(id)
        )
    }

    public async addEquipmentToRoom(equipment: Equipment, room_id: number): Promise<void> {
        if((await this.apiService.request(
            this.apiParamsFactory.addEquipmentToRoom(equipment.id, room_id)
        )).id == equipment.id) {
            NotificationService.pushNotification({"type": "success", "message": "Pomyślnie edytowano ekwipunek!"})
        } else {
            NotificationService.pushNotification({"type": "error", "message": "Coś poszło nie tak!"})
        }
    }

    public async deleteEquipmentFromRoom(room_id: number): Promise<Equipment[]> {
        return await this.apiService.request(
            this.apiParamsFactory.deleteEquipmentFromRoom(room_id)
        );
    }
    
    public async getAdditionalPricingForEquipment(id: number): Promise<PricingIncrease> {
        return await this.apiService.request(
            this.apiParamsFactory.getAdditionalPricingForEquipment(id)
        )    
    }
    
    public async saveEquipment(equipment: Equipment, pricingIncrease: PricingIncrease): Promise<void> {
        if((await this.apiService.request(
            this.apiParamsFactory.saveEquipment(equipment, pricingIncrease)
        )).id == equipment.id) {
            NotificationService.pushNotification({"type": "success", "message": "Pomyślnie dodano ekwipunek!"})
        } else {
            NotificationService.pushNotification({"type": "error", "message": "Coś poszło nie tak!"})
        }
    }
    
    public async updateEquipment(equipment: Equipment, pricingIncrease: PricingIncrease): Promise<void> {
        if((await this.apiService.request(
            this.apiParamsFactory.updateEquipment(equipment, pricingIncrease)
        )).id == equipment.id) {
            NotificationService.pushNotification({"type": "success", "message": "Pomyślnie edytowano ekwipunek!"})
        } else {
            NotificationService.pushNotification({"type": "error", "message": "Coś poszło nie tak!"})
        }
    }

    public async getEquipment(): Promise<Equipment[]> {
        return await this.apiService.request(
            this.apiParamsFactory.getEquipment()
        )
    }

    public async getOneEquipment(id: number): Promise<Equipment> {
        return await this.apiService.request(
            this.apiParamsFactory.getOneEquipment(id)
        )
    }

    public async rentRoom(rent: Rent, price: number): Promise<void> {
        let object = await this.apiService.request(
            this.apiParamsFactory.rentRoom(rent, price)
        );
        if(!object.status || object.status != "ok") {
            NotificationService.pushNotification({"type": "error", "message": "Coś poszło nie tak!"});
            return
        }
        NotificationService.pushNotification({"type": "success", "message": "Pomyślnie wynajęto pokój!"});

    }

    public async cancelRent(rent: Rent): Promise<void> {
        let object = await this.apiService.request(
            this.apiParamsFactory.cancelRent(rent)
        );
        if(!object.status || object.status != "ok") {
            NotificationService.pushNotification({"type": "error", "message": "Coś poszło nie tak!"});
            return
        }
        NotificationService.pushNotification({"type": "success", "message": "Pomyślnie anulowano rezerwacje!"});
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