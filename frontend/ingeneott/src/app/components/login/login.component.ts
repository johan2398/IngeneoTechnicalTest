import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string =  "";
  password: string = "";

  constructor() { }

  ngOnInit(): void {
  }
  doLogin(){
    // if (this.username && this.password) {
    //   this.service.login(this.username, this.password)
    //   .subscribe((user) => {
    //     this.user = user;
    //     this.router.navigate(["/home"])
    //   }, (error) => {console.error('Error getting user session:', error);})
    // }
    // else {
    //   console.log('Please complete the username and password fields')
    // }
  }

}
