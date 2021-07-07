import { Injectable } from '@angular/core';
import { User } from '../users/users.component';
import { HttpClient } from '@angular/common/http';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';



@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  // stompClient: any;
  user : User[] ;
  constructor(private http: HttpClient) { }

  retrieveAllUsers() {
    return this.http.get<User[]>('http://localhost:8080/users')
  }

  addNewUser(user) {
    return this.http.post(
      'http://localhost:8080/users', user);
  }

  loginUser(email, password) {
    return this.http.get<User[]>(`http://localhost:8080/user/login?email=${email}&password=${password}`)
  }

  logoutUser(email) {
    return this.http.get<User[]>(`http://localhost:8080/user/logout?email=${email}`)
  }

  retrieveLoggedUsers() {
    return this.http.get<User[]>('http://localhost:8080/users/logged')
  }



  // initializeWebSocketConnection() {
  //   const serverUrl = 'http://localhost:8080/users';
  //   const ws = new SockJS(serverUrl);
  //   this.stompClient = Stomp.over(ws);
  //   const that = this;
  //   // tslint:disable-next-line:only-arrow-functions
  //   this.stompClient.connect({}, function(frame) {
  //     that.stompClient.subscribe('/users', (user) => {
  //       if (user) {
  //         that.user.push(user);
  //       }
  //     });
  //   });
  // }


  // sendUser(user) {
  //   this.stompClient.send('/app/users' , {}, user);
  // }
}
