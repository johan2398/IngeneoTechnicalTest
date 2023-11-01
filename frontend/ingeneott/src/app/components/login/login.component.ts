import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { ClientDTO } from 'src/app/models/client.dto';
import { ClientService } from 'src/app/services/client/client.service';
import { tap } from 'rxjs/operators';
import { UserDTO } from 'src/app/models/user.dto';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string = "";
  password: string = "";
  username: string = "";
  client!: ClientDTO;
  userDTO: UserDTO = new UserDTO();


  constructor(private service:ClientService, private router:Router) { }

  ngOnInit(): void {
  }
  doLogin() {
    if (this.email && this.password) {
      this.service.login(this.email, this.password)
        .pipe(
          tap(resp => {
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

  doSignUp() {
    if (this.email && this.password && this.username) {
      console.log("Entró aquí");
      this.userDTO.email = this.email;
      this.userDTO.password = this.password
      this.service.createUser(this.userDTO)
        .pipe(
          tap(resp => {
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
      console.log('Please complete the username, email and password fields');
    }
  }

}
