import { Component, OnInit, Input } from '@angular/core';
import { Order } from 'src/app/oneOrder';
import { User } from 'src/app/User';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-queue-elem',
  templateUrl: './queue-elem.component.html',
  styleUrls: ['./queue-elem.component.css']
})
export class QueueElemComponent implements OnInit {

  @Input() order: Order;
  user: User;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getUserById(this.order.createdBy).subscribe(data => {
      this.user = data;
    });
  }

}
