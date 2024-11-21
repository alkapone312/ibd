
import { PricingIncrease } from "./PricingIncreaseManager";
import { Equipment } from "./EquipmentManager";

export default interface AdditionalEquipmentManager {
    getAdditionalEquipmentForRoom(id: number): Promise<Equipment[]>
    getAdditionalPricingForAdditionalEquipment(id: number): Promise<PricingIncrease>
    saveAdditionalEquipment(equipment: Equipment): Promise<void>
    updateAdditionalEquipment(equipment: Equipment): Promise<void>
}