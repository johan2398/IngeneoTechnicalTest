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

  createProduct(ProductDTO: ProductDTO): Observable<any> {
    return this.http.post<any>(`${this.API_PRODUCT}`, ProductDTO);
  }

  getProducts(): Observable<any> {
    return this.http.get<any>(`${this.API_PRODUCT}`);
  }
}
