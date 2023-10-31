import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ShipDTO } from 'src/app/models/ship.dto';

@Injectable({
  providedIn: 'root'
})
export class ShipService {

  private API_SHIPS = "http://localhost:8080/ships";

  constructor(private http: HttpClient) { }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }


  public getShipByDocIdOrGuideNumber(search: string): Observable<any> {
    const headers = this.getHeaders();
    const params = new HttpParams().set('search', search);  // Create HttpParams object with search parameter

    return this.http.get<any>(`${this.API_SHIPS}/filters`, { headers: headers, params: params });
  }
  public createShip(shipDTO: ShipDTO): Observable<any> {
    const headers = this.getHeaders();
    return this.http.post<any>(`${this.API_SHIPS}`, shipDTO, { headers });
  }


}
