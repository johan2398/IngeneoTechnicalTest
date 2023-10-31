import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductDTO }from 'src/app/models/product.dto';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private API_PRODUCT = "http://localhost:8080/products";

  constructor(private http: HttpClient) { }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  createProduct(ProductDTO: ProductDTO): Observable<any> {
    const headers = this.getHeaders();
    return this.http.post<any>(`${this.API_PRODUCT}`, ProductDTO, { headers });
  }

  getProducts(): Observable<any> {
    const headers = this.getHeaders();
    return this.http.get<any>(`${this.API_PRODUCT}`, { headers });
  }
}
