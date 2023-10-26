import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ShipDTO } from 'src/app/models/ship.dto';

@Injectable({
  providedIn: 'root'
})
export class ShipService {

  private API_SHIPS = "http://localhost:8080/ships";

  constructor(private http: HttpClient) { }

  public getShipByDocIdOrGuideNumber(search: string): Observable<any> {
    return this.http.get<any>(`${this.API_SHIPS}/filters`,  {
      params: {
        search: search
      }
    });
  }
  public createShip(shipDTO: ShipDTO): Observable<any> {
    return this.http.post<any>(`${this.API_SHIPS}`, shipDTO);
  }


}
