import ApiParams from "./ApiParams";
import { Room } from "../interfaces/RoomManager";

export default class ApiParamsFactory {
    private authToken: string = '';

    constructor(
        private readonly baseUrl: string
    ) {}

    public login(login: string, password: string): ApiParams {
        let params = new ApiParams(this.baseUrl, '/api/login', 'POST');
        params.addBodyField("login", login);
        params.addBodyField("password", password);
        
        return params;
    }


    public register(login: string, password: string): ApiParams {
        let params = new ApiParams(this.baseUrl, '/api/register', 'POST');
        params.addBodyField("login", login);
        params.addBodyField("password", password);
        
        return params;
    }

    public getRooms(): ApiParams {
        return new ApiParams(this.baseUrl, '/api/rooms', 'GET');
    }

    public getRoom(id: number) {
        return new ApiParams(this.baseUrl, '/api/room/' + id, 'GET');
    }

    public saveRoom(room: Room) {
        let params = new ApiParams(this.baseUrl, '/api/room', 'POST');
        params.addBodyField('roomSize', String(room.roomSize))
        params.addBodyField('basePrice', String(room.basePrice))
        params.addBodyField('capacity', String(room.capacity))
        params.addBodyField('roomNumber', String(room.roomNumber))
        params.addBodyField('name', room.name)
        params.addBodyField('description', room.description)
        
        return this.authorize(params);
    }

    public updateRoom(room: Room) {
        let params = new ApiParams(this.baseUrl, '/api/room/' + room.id, 'PUT');
        params.addBodyField('roomSize', String(room.roomSize))
        params.addBodyField('basePrice', String(room.basePrice))
        params.addBodyField('capacity', String(room.capacity))
        params.addBodyField('roomNumber', String(room.roomNumber))
        params.addBodyField('name', room.name)
        params.addBodyField('description', room.description)
        
        return this.authorize(params);
    }

    public getAdditionalEquipmentForRoom() {

    }



    public setAuthToken(token: string) {
        this.authToken = token;
    }

    private authorize(params: ApiParams): ApiParams {
        params.setHeader("Authorization", this.authToken);

        return params
    }
}