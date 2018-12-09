import { OnInit, Component, ViewChild } from '@angular/core';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { baseUrl } from '../../varUrl';
import swal from 'sweetalert2';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpService } from '../http.service';
import { MatTableDataSource, MatPaginator } from '@angular/material';
/**
 * @title Table with expandable rows
 */
@Component({
  selector: 'app-user-management',
  styleUrls: ['./user-management.component.css'],
  templateUrl: './user-management.component.html',
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0', display: 'none' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class UserManagementComponent implements OnInit {
  dataSource = ELEMENT_DATA;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  dataSource2: MatTableDataSource<User>;

  columnsToDisplay = ['id', 'email', 'active'];
  expandedElement: User;
  user: User;
  loading = false;
  submitted = false;
  baseUrl = baseUrl;
  roles: string[];

  constructor(private http: HttpClient, private router: Router, private httpService: HttpService, ) {
    this.dataSource2 = new MatTableDataSource(this.dataSource);
  }

  ngOnInit() {
    this.dataSource2.paginator = this.paginator;
  }
  applyFilter(filterValue: string) {
    this.dataSource2.filter = filterValue.trim().toLowerCase();
  }

  changeUser(userRole: any, role: any): void {
    this.user = this.dataSource.find(x => x.id == userRole);
    if (role == "user") {
      this.user.roles.user == true ? this.user.roles.user = false : this.user.roles.user = true;
    }
    else if (role == "keeper") {
      this.user.roles.keeper == true ? this.user.roles.keeper = false : this.user.roles.keeper = true;
    }
    else if (role == "admin") {
      this.user.roles.admin == true ? this.user.roles.admin = false : this.user.roles.admin = true;
    }
    console.log(this.user);
  }

  submitRoles(userR: any) {
    this.submitted = true;
    this.loading = true;
    this.user = this.dataSource.find(x => x.id == userR);
    if (this.user.roles.user == true) {
      this.roles.push("USER");
    }
    else if (this.user.roles.keeper == true) {
      this.roles.push("KEEPER");
    }
    else if (this.user.roles.admin == true) {
      this.roles.push("ADMIN");
    }

    return this.httpService.post(this.baseUrl + '/api/user/' + userR + '/roles', this.roles)
      .subscribe(data => {
        swal({
          type: 'success',
          title: 'You successfully  update roles!',
          showConfirmButton: false,
          timer: 1500
        });
        this.router.navigate(['/cabinet']);
      },
        error => {
          console.log(error);
          swal({
            type: 'error',
            title: 'Error!',

            text: 'Can not update roles :'
          })
          console.log(error);

          this.loading = false;
        });

  }


}

export interface User {
  email: string;
  id: number;
  name: string;
  phone: string;
  surname: string;
  active: boolean;
  roles: Roles;
}
export interface Roles {
  user: boolean;
  keeper: boolean;
  admin: boolean;
}


const ELEMENT_DATA: User[] = [
  {
    id: 7,
    email: "whilerun2@moto.sport",
    name: "Caly",
    surname: "Referer",
    phone: "+1010101010101",
    active: true,
    roles: { user: true, keeper: false, admin: true }
  }, {
    id: 2,
    email: "whilerun2@moto.sport",
    name: "Caly",
    surname: "Referer",
    phone: "+1010101010101",
    active: true,
    roles: { user: false, keeper: false, admin: true }
  }, {
    id: 3,
    email: "whilerun2@moto.sport",
    name: "Caly",
    surname: "Referer",
    phone: "+1010101010101",
    active: true,
    roles: { user: true, keeper: false, admin: true }
  }, {
    id: 4,
    email: "whilerun2@moto.sport",
    name: "Caly",
    surname: "Referer",
    phone: "+1010101010101",
    active: true,
    roles: { user: true, keeper: false, admin: true }
  }
];