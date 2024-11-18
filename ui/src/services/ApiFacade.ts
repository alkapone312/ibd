import LoginInterface from "../interfaces/LoginInterface";
import RoomManager from "../interfaces/RoomManager";
import { Room } from "../interfaces/RoomManager"
import ApiParamsFactory from "./ApiParamsFactory";
import ApiService from "./ApiService";

export default class ApiFacade implements LoginInterface, RoomManager {
    private apiService;
    private apiParamsFactory;
    private loginCallback: (isLogged: boolean) => void = () => {};
    
    constructor(apiBase: string) {
        this.apiService = new ApiService();
        this.apiParamsFactory = new ApiParamsFactory(apiBase);
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
        this.loginCallback = callback;
    }

    public async login(login: string, password: string) {
        let object = await this.apiService.request(this.apiParamsFactory.login(login, password))
        if(!object.token) {
            throw new Error("Something went wrong!");
        }
        localStorage.setItem('authToken', object.token);
        this.loginCallback(true);
    }

    public logout(): void {
        localStorage.clear();
        this.loginCallback(false);
    }

    public async register(login: string, password: string) {
        let object = await this.apiService.request(this.apiParamsFactory.register(login, password))
        
        if(!object.status || object.status != "ok") {
            throw new Error("Something went wrong!");
        }
    }


    getRooms(): Room[] {
        return [];
    }

    getRoom(id: int): Room {
        return {} as Room;
    }

    saveRoom(room: Room): void {
        
    }
}