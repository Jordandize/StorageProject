import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from './http.service';
import { baseUrl } from '../varUrl';
import { User } from './data/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl = baseUrl + '/api/users';

  constructor(private httpService: HttpService) { }

  getUserById(id: number): Observable<User> {
    return this.httpService.get<User>(`${this.usersUrl}/${id}`);
  }

}
