import { Component, OnInit } from '@angular/core';
import { SessionService } from 'src/app/session.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  email = '675kostya@gmail.com';
  avatar: string;
  emailPlaceholder = 'no_logined_user';

  constructor(private sessionService: SessionService) { }

  ngOnInit() {
    this.email = this.sessionService.isLogined() ? this.sessionService.getUser().email : this.emailPlaceholder;
  }

  logout() {
    this.sessionService.logout();
  }

}
