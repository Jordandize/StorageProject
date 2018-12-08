import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SessionService } from './session.service';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  authHeader = 'x-auth-token';

  constructor(
    private http: HttpClient,
    private sessionService: SessionService) { }

  private appendAuthHeader(headers: HttpHeaders): HttpHeaders {
    return headers.append(this.authHeader, this.sessionService.getAuth());
  }

  get<T>(url, headers = new HttpHeaders()): Observable<T> {
    headers = this.appendAuthHeader(headers);
    return this.http.get<T>(url, {
      headers: headers
    });
  }
  delete<T>(url, headers = new HttpHeaders()): Observable<T> {
    headers = this.appendAuthHeader(headers);
    return this.http.delete<T>(url, {
      headers: headers
    });
  }

  post<T>(url, data, headers = new HttpHeaders()): Observable<T> {
    headers = this.appendAuthHeader(headers);
    return this.http.post<T>(url, data, {
      headers: headers
    });
  }
}
