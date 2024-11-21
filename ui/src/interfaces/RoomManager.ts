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
    getRooms(): Promise<Room[]>
    getRoom(id: number): Promise<Room>
    saveRoom(room: Room): Promise<void>
    updateRoom(room: Room): Promise<void>
}