import { User } from './../../models/user';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  user: User;
  loggedIn: boolean = false;

  constructor(private authSvc: AuthService) { }

  ngOnInit(): void {
    this.reload();
  }

  reload(){
    if (this.authSvc.checkLogin){
      this.loggedIn = true;
    }
    this.authSvc.getUserInfo().subscribe(
      data => {
        this.user = data;
      },
      err => {
        this.user = new User();
        console.error('AccountComponent.reload(): error getting user data with principal.');
      }
    );
  }

  submit(){
    this.authSvc.createUser(this.user).subscribe(
      data => {
        this.authSvc.login(this.user.username, this.user.password).subscribe(
          data => {
          },
          err => {
            console.error('AccountComponent.submit(): error logging new user in')
          }
        )
        this.user = data;
      },
      err => {
        this.user = new User();
        console.error('AccountComponent.submit(): error creating new user');
      }
    );
  }

}
