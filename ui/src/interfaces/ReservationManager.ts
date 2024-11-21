export type Rent = {
    id: number,
    client_id: number,
    room_id: number,
    checkin_date: string,
    checkout_date: string
};

export default interface ReservationManager {
    rentRoom(rent: Rent, price: number): Promise<void>;
    cancelRent(rent: Rent): Promise<void>;
    getRents(): Promise<Rent[]>
    getMyRents(): Promise<Rent[]>
}