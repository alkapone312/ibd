import { Room } from "./RoomManager";

export type Rent = {
    id: number,
    client?: any,
    room?: Room,
    room_id?: number,
    client_id?: number,
    checkin_date: string,
    checkout_date: string
    checkInDate?: string,
    checkOutDate?: string
};

export default interface ReservationManager {
    rentRoom(rent: Rent, price: number): Promise<void>;
    cancelRent(rent: Rent): Promise<void>;
    getRents(): Promise<Rent[]>
    getMyRents(): Promise<Rent[]>
}