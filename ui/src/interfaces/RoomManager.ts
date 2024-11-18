export type Room = {
    id: number,
    roomSize: number,
    basePrice: number,
    capacity: number,
    roomNumber: number,
    name: string,
    description: string
}

export default interface RoomManager {
    getRooms(): Room[]
    getRoom(id: int): Room
    saveRoom(room: Room): void
}