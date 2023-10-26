import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { ClientDTO } from 'src/app/models/client.dto';
import { ClientService } from 'src/app/services/client/client.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string = "";
  password: string = "";
  client!: ClientDTO

  constructor(private service:ClientService, private router:Router) { }

  ngOnInit(): void {
  }
  doLogin(){
    if (this.username && this.password) {
      this.service.login(this.username, this.password)
      .subscribe((client) => {
        this.client = client;
        this.router.navigate(["/home"])
      }, (error) => {console.error('Error getting user session:', error);})
    }
    else {
      console.log('Please complete the username and password fields')
    }
  }

}
