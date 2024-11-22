import { PricingIncrease } from "./PricingIncreaseManager";

export type Equipment = {
    id: number,
    name: string,
};

export default interface EquipmentManager {
    getOneEquipment(id: number): Promise<Equipment>
    getEquipment(): Promise<Equipment[]>
    getEquipmentForRoom(id: number): Promise<Equipment[]>
    addEquipmentToRoom(equipment: Equipment, room_id: number): Promise<void>
    deleteEquipmentFromRoom(room_id: number): Promise<Equipment[]>
    getAdditionalPricingForEquipment(id: number): Promise<PricingIncrease>
    saveEquipment(equipment: Equipment, pricingIncrease: PricingIncrease): Promise<void>
    updateEquipment(equipment: Equipment, pricingIncrease: PricingIncrease): Promise<void>
}