import { Port } from "./port.dto";
import { Store } from "./store.dto";

export class WarehouseDTO {
    id: number;
    name: string;
    location: string;
    storageCapacity: string;
    type: WarehouseType;
    port: Port;
    store: Store;
}

export enum WarehouseType {
    STORE = 'STORE',
    PORT = 'PORT'
}