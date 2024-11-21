import { PricingIncrease } from "./PricingIncreaseManager";

export type Equipment = {
    id: number,
    name: string,
};

export default interface EquipmentManager {
    getEquipmentForRoom(id: number): Promise<Equipment[]>
    getAdditionalPricingForEquipment(id: number): Promise<PricingIncrease>
    saveEquipment(equipment: Equipment): Promise<void>
    updateEquipment(equipment: Equipment): Promise<void>
}