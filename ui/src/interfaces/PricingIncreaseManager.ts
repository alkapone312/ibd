export type PricingIncrease = {
    id: number,
    increase: number,
    type: string
};

export default interface PricingIncreaseManager {
    getPricingIncreaseForEquipment(id: number): Promise<PricingIncrease>
    getPricingIncreaseForAdditionalEquipment(id: number): Promise<PricingIncrease>
    savePricingIncrease(increase: PricingIncrease): Promise<void>
    updatePricingIncrease(increase: PricingIncrease): Promise<void>
}