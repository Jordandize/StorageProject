import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { baseUrl } from '../varUrl';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Order as OldOrder } from './order';
import { Order } from './data/Order';
import { HttpService } from './http.service';
import { OrderStatus } from './data/OrderStatus';
import { OrderShortage } from './data/OrderShortage';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  baseUrl = baseUrl;
  ORDERS: OldOrder[] = [
{
	name: 'order1',
	amount: 1, 
	orderType: 1,
	annotation: 'qwr',
	products: {1: 2, 2: 3},
	id_user: 2,
  createdBy: 2
}
];



  constructor(
    private http: HttpClient,
    private httpService: HttpService) { }

  createOrder(order: OldOrder): Observable<number> {
    const head = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post<number>(this.baseUrl + '/api/orders', this.ORDERS[0], {headers: head}).pipe(
      catchError(this.handleError<number>('create order')));
  }

  createOrderAny(order: any): Observable<number> {
    return this.httpService.post<number>(this.baseUrl + '/api/orders', order);
  }

  getUserOrders(id: number): Observable<OldOrder>{
    const url = `${this.baseUrl}/orders/${id}`;
    return this.http.get<OldOrder>(url)
    .pipe(
      catchError(this.handleError<OldOrder>(`getUserOrder id=${id}`))
      );
  }


   assignKeeperToOrder(id_user: number, id_order: number): Observable<OldOrder>{
     const url = `${this.baseUrl}/${id_order}/assignKeeper/${id_user}`;
     return this.http.post<OldOrder>(url, {id_order, id_user})
     .pipe(
       catchError(this.handleError<OldOrder>(`assign order id={id_order} to keeper id={id_keeper} failed`))
       );
   }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    }
  }

  private log(message: string) {
    console.log(`OrderServise: ${message}`);
  }

  getShortageForOrder(id: number): Observable<OrderShortage[]> {
    return this.httpService.get(`${baseUrl}/api/orders/${id}/shortage`);
  }

  getOrdersForKeeperByStatus(status: OrderStatus): Observable<Order[]> {
    return this.httpService.get(`${baseUrl}/api/orders?status=${status}`);
  }

  setOrderReady(id: number): Observable<Order> {
    return this.httpService.post(`${baseUrl}/api/orders/${id}/ready`, { });
  }

  setOrderClosed(id: number): Observable<Order> {
    return this.httpService.post(`${baseUrl}/api/orders/${id}/closed`, { });
  }

}
