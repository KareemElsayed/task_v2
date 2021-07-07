import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../service/user-service.service';


export class User {

  constructor(
    public userName: string,
    public email: string,
    public password: string,
    public isLogged: Boolean
    ){}
}

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  allUsers: User[];
  loggedUsers : User[];
  error: String;
  constructor(private userService: UserServiceService) { }

  ngOnInit(): void {
    this.retrieveAllUsers();
    this.retrieveAllLoggedUsers();
  }
  retrieveAllUsers() {
    this.userService.retrieveAllUsers().subscribe(
      response => {
        this.allUsers = response;
      },
      error => {
        this.error = error;
      })
  }
  retrieveAllLoggedUsers(){
    this.userService.retrieveLoggedUsers().subscribe(
      response => {
        this.loggedUsers = response;
      },
      error => {
        this.error = error;
      })
  }

  
}
