import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { OrderComponent } from './order/order.component';
import { baseUrl } from '../varUrl';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Order } from './order';
import { User } from './userPage/user.component';
//import { ORDERS } from './order/ORDERS';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  baseUrl = baseUrl;
  //ORDERS = ORDERS;

  ORDERS:Order[] = [
{
	name: 'order1',
	amount: 1, 
	orderType: 1,
	annotation: 'qwr',
	products: {1:2, 2:3},
	id_user: 2,
  createdBy: 2
}
];



  constructor(private http: HttpClient) { }


  createOrder(order: Order): Observable<number>{
  	return this.http.post<number>(this.baseUrl+'/orders', this.ORDERS[0]).pipe(
  		catchError(this.handleError<number>('create order')));
  }

  getUserOrders(id: number): Observable<Order>{
    const url = `${this.baseUrl}/orders/${id}`;
    return this.http.get<Order>(url)
    .pipe(
      catchError(this.handleError<Order>(`getUserOrder id=${id}`))
      );
  }


  // assignKeeperToOrder(id_user: number, id_order: number): Observable<Order>{
  //   const url = `${this.baseUrl}/${id_order}/assignKeeper/${id_user}`;
  //   return this.http.post<Order>(url, )
  // }
  // getUserOrders(id: number): Observable<Order[]>{
  //   return this.http.get<Order[]>(this.baseUrl+'/orders/{id}').pipe(
  //     catchError(this.handleError<Order[]>('get orders for user {id}')));
  // }

  // assignOrderToKeeper(order: Order, id: number){
  //   return this.http.post(this.baseUrl+'')
  // }

  private handleError<T> (operation = 'operation', result?:T){
    return (error:any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    }
  }

  private log(message: string) {
    console.log(`OrderServise: ${message}`);
  }

}
