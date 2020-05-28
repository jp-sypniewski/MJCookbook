import { User } from './../../models/user';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Profile } from 'src/app/models/profile';
import { Router } from '@angular/router';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  user: User = new User();
  loggedIn: boolean = false;
  showEdit: boolean = false;

  constructor(private router: Router,
    private authSvc: AuthService) { }

  ngOnInit(): void {
    this.user = new User();
    this.user.profile = new Profile();
    this.reload();
  }

  reload(){
    if (this.authSvc.checkLogin()){
      this.authSvc.getUserInfo().subscribe(
        data => {
          this.user = data;
          this.loggedIn = true;
        },
        err => {
          this.user = new User();
          this.user.profile = new Profile();
          console.error('AccountComponent.reload(): error getting user data with principal.');
        }
      );
    } else {
      this.user = new User();
      this.user.profile = new Profile();
    }
  }

  logIn(form: NgForm){
    this.authSvc.login(form.value.username, form.value.password).subscribe(
      next => {
        console.log('AccountComponent.logIn(): user logged in');
        this.reload();
      },
      error => {
        console.error('AccountComponent.logIn(): error logging in.');
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
        this.reload();
      },
      err => {
        this.user = new User();
        console.error('AccountComponent.submit(): error creating new user');
      }
    );
  }

  update(){
    this.authSvc.updateUser(this.user).subscribe(
      data => {
        this.user = data;
        this.showEdit = false;
      },
      err => {
        console.error('AccountComponent.update(): error updating user')
      }
    );
  }

}
