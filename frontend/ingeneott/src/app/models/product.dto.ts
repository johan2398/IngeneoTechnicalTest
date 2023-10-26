export class ProductDTO {
    id: number;
    name: string;
    quantity: number;
    description: String;
    price: number;
    type: ProductType;
    date: Date;
  }
  
  export enum ProductType {
    VEHICLES = 'VEHICLES',
    TECHNOLOGY = 'TECHNOLOGY',
    HOME_AND_FURNITURE = 'HOME_AND_FURNITURE',
    FASHION = 'FASHION',
    HEALTH_AND_MEDICAL_EQUIPMENT = 'HEALTH_AND_MEDICAL_EQUIPMENT'
  }