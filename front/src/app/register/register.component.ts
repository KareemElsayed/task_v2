import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../service/user-service.service';
import { User } from '../users/users.component';
import {webSocket, WebSocketSubject} from 'rxjs/webSocket';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  // myWebSocket: WebSocketSubject<any> = webSocket('ws://localhost:8000');
  user:User;
  message : string;
  errorMessage : string;

  constructor(private userService: UserServiceService) { }

  ngOnInit(): void {
    this.user = new User('',null,null,false);
  }

  createUser(){
    this.userService.addNewUser(this.user)
          .subscribe (
            data => {
              data => this.user = data
              console.log(data)
              this.message='User Signed up Successfully'
              this.errorMessage=''
            },
            error => {
              this.errorMessage=error.error.message
              console.log(error.error.message)
              this.message=''
            }
          )
  }

  // sendUser(){
  //   if (this.user) {
  //         this.userService.sendUser(this.user);
  //         this.user = null;
  //       }
  // }

}
