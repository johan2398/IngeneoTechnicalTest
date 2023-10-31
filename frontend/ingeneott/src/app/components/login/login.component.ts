import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { ClientDTO } from 'src/app/models/client.dto';
import { ClientService } from 'src/app/services/client/client.service';
import { tap } from 'rxjs/operators';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string = "";
  password: string = "";
  client!: ClientDTO

  constructor(private service:ClientService, private router:Router) { }

  ngOnInit(): void {
  }
  doLogin() {
    if (this.email && this.password) {
      this.service.login(this.email, this.password)
        .pipe(
          tap(resp => {
            console.log(resp);
            localStorage.setItem("token", resp.token);
            this.client = resp;
            this.router.navigate(['/home']);
          })
        )
        .subscribe(
          response => {
            // Aquí puedes manejar la respuesta si es necesario
          },
          error => {
            console.error('Error getting user session:', error);
          }
        );
    } else {
      console.log('Please complete the email and password fields');
    }
  }

}
