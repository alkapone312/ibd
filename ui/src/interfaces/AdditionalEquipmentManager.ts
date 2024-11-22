
import { PricingIncrease } from "./PricingIncreaseManager";
import { Equipment } from "./EquipmentManager";

export default interface AdditionalEquipmentManager {
    getAdditionalEquipment(): Promise<Equipment[]>;
    getOneAdditionalEquipment(id: number): Promise<Equipment>;
    getAdditionalEquipmentForReservation(reservation_id: number): Promise<Equipment[]>
    getPricingForAdditionalEquipment(id: number): Promise<PricingIncrease>
    saveAdditionalEquipment(equipment: Equipment, pricingIncrease: PricingIncrease): Promise<void>
    updateAdditionalEquipment(equipment: Equipment, pricingIncrease: PricingIncrease): Promise<void>
}