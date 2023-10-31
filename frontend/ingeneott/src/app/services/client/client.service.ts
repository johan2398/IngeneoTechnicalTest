import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { ClientDTO } from 'src/app/models/client.dto';
import { UserDTO } from 'src/app/models/user.dto';
@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private API_CLIENT = "http://localhost:8080/clients";

  constructor(private http: HttpClient) { }

  public login(email: string, password: string): Observable<any> {
    const headers = new HttpHeaders();
    const userDTO: UserDTO = {
      email: email,
      password: password
    };
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.post<any>(`${this.API_CLIENT.replace("/clients", "")}/login`, userDTO, httpOptions);
  }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  public getClients(): Observable<any> {
    const headers = this.getHeaders();
    return this.http.get<any>(`${this.API_CLIENT}`, { headers });
  }

  public createClient(clientDTO: ClientDTO): Observable<any> {
    const headers = this.getHeaders();
    return this.http.post<any>(`${this.API_CLIENT}`, clientDTO, { headers });
  }

}
