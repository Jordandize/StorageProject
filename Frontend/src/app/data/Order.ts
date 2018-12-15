import { OrderStatus } from './OrderStatus';

export class Order {
    id: number;
    annotation: string;
    archived: boolean;
    assignedTo: number;
    createdBy: number;
    creationDateTime: string;
    modifiedDateTime: string;
    orderStatus: OrderStatus;
    orderType: number;
    parentId: number;
}
