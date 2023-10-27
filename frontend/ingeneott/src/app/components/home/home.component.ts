import { Component, HostListener, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProductDTO } from 'src/app/models/product.dto';
import { ClientDTO } from 'src/app/models/client.dto';
import { ClientService } from 'src/app/services/client/client.service';
import { ProductService } from 'src/app/services/product/product.service';
import { ShipService } from 'src/app/services/ship/ship.service';
import { ShipDTO } from 'src/app/models/ship.dto';
import { WarehouseDTO } from 'src/app/models/warehouse.dto';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  //Models
  productDTO: ProductDTO = new ProductDTO();
  clientDTO: ClientDTO = new ClientDTO();
  shipDTO: ShipDTO = new ShipDTO();



  //Navbar configuration
  selectedOption: string = 'guideNumber';
  selectedSection: string = 'createShipment';


  //Products
  itemsPerPage = 10;
  currentPage = 1;
  loading = false;
  visibleProducts: any[] = []; // Lista de productos que se mostrarán en la vista
  products: any[] = []; // Esta lista debería ser populada por el servicio que obtiene los productos existentes
  productForm: FormGroup;

  //Clients
  clientForm: FormGroup;
  clients: any[] = []; // Lista de clientes
  visibleClients: any[] = []; //Lista de clientes visibles en la tabla

  //Tracking
  searchForm: FormGroup;
  search: string = '';
  guideNumberOption: boolean = true;
  documentIdOption: boolean = false;
  ships: any[] = [];

  constructor(private productService: ProductService, private clientService: ClientService,
    private shipService: ShipService,
    private router: Router, private fb: FormBuilder) {
    // Inicializar el formulario con los campos requeridos y valores por defecto
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      quantity: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', Validators.required],
      type: ['', Validators.required],
      creationDate: [this.getCurrentDate()] // Establece la fecha actual como valor por defecto
    });
    this.clientForm = this.fb.group({
      name: ['', Validators.required],
      last_name: [''],
      identification: ['', Validators.required],
      email: ['', [Validators.email]],
      address: ['']
    });
    this.searchForm = this.fb.group({
      search: ['', Validators.required],
      guideNumberOption: ['', Validators.required],
      documentIdOption: ['', Validators.required],
      productName: ['', Validators.required],
      productType: ['', Validators.required],
      productQuantity: ['', Validators.required],
      warehouseType: ['', Validators.required],
      clientName: ['', Validators.required],
      docId: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.selectedSection = 'createShipment';
    this.loadProducts();
  }

  loadProducts(): void {
    this.loading = true;
    this.productService.getProducts()
      .subscribe(
        resp => {
          this.products = resp;
          this.visibleProducts = this.products.slice(0, this.itemsPerPage);
          this.currentPage++;
          this.loading = false;
        },
        err => {
          console.log(err);
          this.loading = false;
        }
      );
  }

  @HostListener('window:scroll', ['$event'])
  onScroll(event: Event): void {
    const pos = (document.documentElement.scrollTop || document.body.scrollTop) + document.documentElement.offsetHeight;
    const max = document.documentElement.scrollHeight;

    if (pos > max - 100 && !this.loading) {
      this.loadProducts();
      this.loadClients();
    }
  }

  loadClients(): void {
    this.loading = true;
    this.clientService.getClients()
      .subscribe(
        resp => {
          this.clients = resp;
          this.visibleClients = this.clients.slice(0, this.itemsPerPage);
          this.currentPage++;
          this.loading = false;
        },
        err => {
          console.log(err);
          this.loading = false;
        }
      );
  }

  // Método para obtener la fecha actual en formato 'YYYY-MM-DD'
  getCurrentDate(): string {
    const today = new Date();
    const year = today.getFullYear();
    const month = today.getMonth() + 1;
    const day = today.getDate();
    return `${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}`;
  }


  selectSection(section: string): void {
    this.selectedSection = section;
  }

  changeShipmentOption(): void {

  }

  onSubmitProduct(): void {
    this.productService.createProduct(this.productDTO)
      .subscribe(
        resp => {
        },
        err => {
        }
      )
  }

  onSubmitClient(): void {
    this.clientService.createClient(this.clientDTO)
      .subscribe(
        resp => {

        },
        err => {

        }
      )
  }

  onSubmitTrack(): void {
    this.shipService.getShipByDocIdOrGuideNumber(this.search)
      .subscribe(
        resp => {
          this.ships = resp;
          console.log(this.ships)
        },
        error => {
          this.ships = [];
        }
      );
  }

  onSubmitShipment(): void {
    this.shipDTO.product = new ProductDTO();
    this.shipDTO.product.name = this.searchForm.get('productName')?.value;
    this.shipDTO.product.quantity = this.searchForm.get('productQuantity')?.value;
    this.shipDTO.product.type = this.searchForm.get('productType')?.value;
    this.shipDTO.warehouse = new WarehouseDTO();
    this.shipDTO.warehouse.type = this.searchForm.get('warehouseType')?.value;
    this.shipDTO.client = new ClientDTO();
    this.shipDTO.client.name = this.searchForm.get('clientName')?.value;
    this.shipDTO.client.identification = this.searchForm.get('docId')?.value;
    console.log(this.shipDTO);

    this.shipService.createShip(this.shipDTO)
      .subscribe(
        resp => {
          console.log(this.ships)
          window.alert(`Número de guía generado: ${resp.guideNumber} al cliente con documento: ${resp.client.identification}`);

        },
        error => {
        }
      );
  }
}

