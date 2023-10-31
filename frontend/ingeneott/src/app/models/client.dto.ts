export class ClientDTO {
    id: number;
    name: string;
    lastName: string;
    password: string;
    email: string;
    address: string;
    state: ClientState;
    identification: string;
    username: string;
  }
  
  export enum ClientState {
    ACTIVE = 'ACTIVE',
    INACTIVE = 'INACTIVE',
    PENDING = 'PENDING'
  }