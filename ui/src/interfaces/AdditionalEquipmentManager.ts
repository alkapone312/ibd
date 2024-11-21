
import { PricingIncrease } from "./PricingIncreaseManager";
import { Equipment } from "./EquipmentManager";

export default interface AdditionalEquipmentManager {
    getAdditionalEquipment(): Promise<Equipment>;
    getAdditionalEquipmentForReservation(reservation_id: number): Promise<Equipment[]>
    getPricingForAdditionalEquipment(id: number): Promise<PricingIncrease>
    saveAdditionalEquipment(equipment: Equipment): Promise<void>
    updateAdditionalEquipment(equipment: Equipment): Promise<void>
}