export class Order{
	name: string;
	amount: number;
	orderType: number;
	annotation: string;
	products: {[key: number]: number};
	id_user: number;
	createdBy: number;
}