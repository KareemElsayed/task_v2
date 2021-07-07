import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../service/user-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email : string;
  password : string;

  message : string;

  errorMessage : string;
  invalidLogin = false

  constructor(private userService: UserServiceService) { }

  ngOnInit(): void {
  }

  handleLogin(email,password){
    console.log(this.email)
    this.userService.loginUser(email,password)
          .subscribe (
            data => {
              console.log(data)
              this.message='User Logged'
              this.errorMessage=''
            },
            error => {
              this.errorMessage=error.error.message
              console.log(error.error.message)
              this.message=''
            }
          )
  }


  handleLogout(email){
    console.log(this.email)
    this.userService.logoutUser(email)
          .subscribe (
            data => {
              console.log(data)
              this.message='User Logged out'
              this.errorMessage=''
            },
            error => {
              this.errorMessage=error.error.message
              console.log(error.error.message)
              this.message=''
            }
          )
  }
}
