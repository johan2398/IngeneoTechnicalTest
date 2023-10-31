import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ClientService } from './services/client/client.service';
import { ProductService } from './services/product/product.service';
import { ShipService } from './services/ship/ship.service';
import { WarehouseService } from './services/warehouse/warehouse.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  constructor(
  ){

  }
  ngOnInit(): void {
    
  }


}
