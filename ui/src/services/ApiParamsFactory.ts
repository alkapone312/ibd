import ApiParams from "./ApiParams";
import { Room } from "../interfaces/RoomManager";
import { Rent } from "../interfaces/ReservationManager";

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

    public getAdditionalEquipment(): ApiParams {
        return this.authorize(new ApiParams(
            this.baseUrl, '/api/additionalEquipments', 'GET'
        ));
    }

    public getAdditionalEquipmentForReservation(reservation_id: number): ApiParams {
        return this.authorize(new ApiParams(
            this.baseUrl, '/api/additionalEquipments/' + reservation_id, 'GET'
        ));
    }

    public rentRoom(rent: Rent, price: number): ApiParams {
        let params = new ApiParams(this.baseUrl, '/api/rent/' + rent.room_id, 'POST');
        params.addBodyField("dateFrom", rent.checkin_date);
        params.addBodyField("dateTo", rent.checkout_date);
        params.addBodyField("price", String(price));

        return this.authorize(params);
    }

    public cancelRent(rent: Rent): ApiParams {
        let params = new ApiParams(this.baseUrl, '/api/reservation', 'DELETE');
        params.addBodyField('reservationId', String(rent.id))

        return this.authorize(params);
    }

    public getRents(): ApiParams {
        return this.authorize(new ApiParams(this.baseUrl, '/api/reservations', 'GET'));
    }

    public getMyRents(): ApiParams {
        return this.authorize(new ApiParams(this.baseUrl, '/api/myReservations', 'GET'));
    }

    public getPricingForAdditionalEquipment(id: number): ApiParams {
        return this.authorize(new ApiParams(
            this.baseUrl, '/api/additionalEquipment/pricingIncrease/' + id, 'GET'
        ))
    }

    public setAuthToken(token: string) {
        this.authToken = token;
    }

    private authorize(params: ApiParams): ApiParams {
        params.setHeader("Authorization", this.authToken);

        return params
    }
}