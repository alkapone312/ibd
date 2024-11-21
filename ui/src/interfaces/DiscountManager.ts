export type Discount = {
    id: number,
    decrease: number,
    type: string
};

export default interface DiscountManager {
    getDiscountForEquipment(id: number): Promise<Discount>
    getDiscountForAdditionalEquipment(id: number): Promise<Discount>
    saveDiscount(increase: Discount): Promise<void>
    updateDiscount(increase: Discount): Promise<void>
}