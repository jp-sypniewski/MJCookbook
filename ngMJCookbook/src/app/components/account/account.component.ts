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
    if (this.authSvc.checkLogin){
      this.loggedIn = true;
    }
  }

  submit(){

  }

}
