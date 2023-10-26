import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ClientDTO } from 'src/app/models/client.dto';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private API_CLIENT = "http://localhost:8080/clients";

  constructor(private http: HttpClient) { }

  public login(username: string, password: string): Observable<ClientDTO> {
    const headers = new HttpHeaders();

    return this.http.get<ClientDTO>(`${this.API_CLIENT}/usernames`, {
      headers: headers,
      responseType: 'json',
      params: {
        userName: username,
        password: password
      }
    });
  }

  public getClients(): Observable<any> {
    return this.http.get<any>(`${this.API_CLIENT}`);
  }

  public createClient(clientDTO:ClientDTO): Observable<any> {
    return this.http.post<any>(`${this.API_CLIENT}`, clientDTO);
  }

}
