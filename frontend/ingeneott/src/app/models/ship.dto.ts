import { ClientDTO } from "./client.dto";
import { ProductDTO } from "./product.dto";
import { WarehouseDTO } from "./warehouse.dto";
import { DiscountDTO } from "./discount.dto";


export class ShipDTO{
    id: number;
    product: ProductDTO;
    registryDate: Date;
    deliveryDate: Date;
    shippingPrice: number;
    guideNumber: string;
    client: ClientDTO;
    warehouse: WarehouseDTO;
    discount: DiscountDTO;
    state: ShipState;
    shippingPriceWithDiscount: number;
}

export enum ShipState{
    REGISTRIED = 'REGISTRIED',
	ON_THE_WAY = 'ON_THE_WAY',
	DELIVERIED = 'DELIVERIED',
	RETURNED = 'RETURNED'
}