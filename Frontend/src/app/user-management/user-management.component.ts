import { OnInit, Component, ViewChild } from '@angular/core';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { baseUrl } from '../../varUrl';
import swal from 'sweetalert2';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpService } from '../http.service';
import { MatTableDataSource, MatPaginator,MatSort } from '@angular/material';
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
  ELEMENT_DATA: User[];
  dataSource = this.ELEMENT_DATA;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  dataSource2: MatTableDataSource<User>;

  columnsToDisplay = ['id', 'email', 'name'];
  expandedElement: User;
  user: User;
  loading = true;
  submitted = false;
  baseUrl = baseUrl;
  roles: string[]=[];

  constructor(private http: HttpClient, private router: Router, private httpService: HttpService, ) {
    this.dataSource2 = new MatTableDataSource(this.dataSource);
  }

   ngOnInit() {
  this.loading = true;
  this.getUsers();
  }
  applyFilter(filterValue: string) {
  filterValue = filterValue.trim(); 
  filterValue = filterValue.toLowerCase(); 
  this.dataSource2.filter = filterValue;
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
    var counter=0;
    this.user = this.dataSource.find(x => x.id == userR);
    if (this.user.roles.user == true) {
      this.roles.push("USER");
    }
    if (this.user.roles.keeper == true) {
      this.roles.push("KEEPER");
    }
    if (this.user.roles.admin == true) {
      this.roles.push("ADMIN");
    }
    if(this.user.roles.user ==false && this.user.roles.keeper ==false && this.user.roles.admin ==false){
      this.roles.push("USER");
    }
    const ch = {
      'name': this.roles
  };

    return this.httpService.post(this.baseUrl + '/api/users/' + userR + '/roles', ch)
      .subscribe(data => {
        this.swalOk('You successfully  update roles!');
        this.router.navigate(['/cabinet']);
      },
        error => {
          console.log(error);
          this.swalError('Can not update roles :');
          this.loading = false;
        });

  }
swalOk(str:string){
  swal({
    type: 'success',
    title: str,
    showConfirmButton: false,
    timer: 1500
  });
}
swalError(str:string){
  swal({
    type: 'error',
    title: 'Error!',

    text: str
  })
}
getUsers(){
  return this.httpService.get(this.baseUrl + '/api/users')
  .subscribe(data => {
    this.dataSource=<User[]>data;
    for (let entry of this.dataSource) {
     this.httpService.get(this.baseUrl + '/api/users/' + entry.id+ '/roles')
     .subscribe(data => {
       var r= { user: false, keeper: false, admin: false };
       var roles=<RolesFromBack[]>data; 
       for(var i=0;i<roles.length;i++){
         if(roles[i].name==userRole){
           r.user=true;
         }
         else if(roles[i].name==keeperRole){
          r.keeper=true;
         }
         else if(roles[i].name==adminRole){
          r.admin=true;
         }
       }
       entry.roles=r;
       this.loading = false;
     },
       error => {
         console.log(error);
         this.loading = false;
       });
    }
    this.dataSource2 = new MatTableDataSource<User>(this.dataSource);
    this.dataSource2.filterPredicate = function(data, filter: string): boolean {
      return data.email.toLowerCase().includes(filter);
  };
    this.dataSource2.paginator = this.paginator;
    this.dataSource2.sort = this.sort;
    this.loading = false;
  },
    error => {
      console.log(error);
      this.loading = false;
    });
}

}
const userRole:string="USER";
const keeperRole:string="KEEPER";
const adminRole:string="ADMIN";

export interface User {
  email: string;
  id: number;
  name: string;
  phone: string;
  surname: string;
  active: boolean;
  roles: Roles;
}
export interface RolesFromBack {
  id: number;
  name: string;
}
export interface Roles {
  user: boolean;
  keeper: boolean;
  admin: boolean;
}


/*const ELEMENT_DATA: User[] = [
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
];*/