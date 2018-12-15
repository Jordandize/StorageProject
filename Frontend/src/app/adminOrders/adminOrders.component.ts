import { Component, OnInit, Inject, Input, ViewChild } from '@angular/core';
import {MatTableDataSource, MatPaginator, MatSort} from '@angular/material';
import {HttpClient} from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { baseUrl } from '../../varUrl';
import { OrderService } from '../order.service';
import { SessionService } from '../session.service';
import { HttpService } from '../http.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import swal from 'sweetalert2';


export class Order {
  annotation: string;
  archived: boolean;
  assignedTo: number;
  createdBy: number;
  creationDateTime: string;
  id: number;
  modifiedDateTime: string;
  orderStatus: number;
  orderType: number;
  parentId: number;
}
export class Keeper {
  id : number;
  email : string;
  name : string;
  surname : string;
  phone: string;
  active : boolean;
}

@Component({
  selector: 'admin-orders',
  templateUrl: './adminOrders.component.html',
  styleUrls: ['./adminOrders.component.css']
})
export class AdminOrdersComponent implements OnInit {

  displayedColumns = ['id', 'creationDateTime', 'annotation', ' assignedTo'];
  private orders: Order[] = null;
  public id = this.sessionService.getUser().id;
  baseUrl = baseUrl;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

     public  dataSource2: Order[] ;

      public  dataSource ;

    applyFilter(filterValue: string) {
      this.dataSource.filter = filterValue.trim().toLowerCase();
    }
   
  constructor(
    private orderService: OrderService,
    private http: HttpClient,
    private httpService: HttpService,
    private sessionService: SessionService,
    private route: ActivatedRoute,
    private router: Router,
    public dialog: MatDialog) {}
    openDialog(orderId:number): void {
      const dialogRef = this.dialog.open(AssignKeeper, {
        width: '80%',
        data: orderId
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
  
      });
    }



    async ngOnInit() {
     await  this.httpService.get(this.baseUrl + '/api/orders/id_keeper=null').subscribe(data => {

        if (data != null) {
        this.dataSource2 = <Order[]>data;
        }


        this.dataSource = new MatTableDataSource(this.dataSource2);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;


          },   error => {
            console.log(error);
        }
        );
    }

}
@Component({
  selector: 'assignKeeper',
  templateUrl: 'assignKeeper.component.html',
  styleUrls: ['./assignKeeper.component.css']
})
export class AssignKeeper implements OnInit {
  public  dataSource2: Keeper[] ;

  public  dataSource ;
  constructor( private http: HttpClient, private router: Router, private httpService: HttpService,
    public dialogRef: MatDialogRef<AssignKeeper>,
    @Inject(MAT_DIALOG_DATA) public idOrder: number) { 
       this.dataSource = new MatTableDataSource(this.dataSource2);
      }

  displayedColumns = ['id', 'email', 'surname', 'active'];
  loading = false;
  submitted = false;
  baseUrl = baseUrl;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

     

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
 
    ngOnInit() {
    this.getKeepers();
    console.log( this.idOrder);
  }
  assignKeeper(idKeeper:number){
    this.loading=true;
    return this.httpService.postUrl(this.baseUrl + '/'+ this.idOrder +'/assignKeeper/' + idKeeper)
        .subscribe(data => {
          swal({
            type: 'success',
            title: 'You successfully assignKeeper!',
            showConfirmButton: false,
            timer: 1500
          });
          this.onNoClick();
          this.router.navigate(['/cabinet/adminOrders']);
        },
          error => {
            swal({
              type: 'error',
              title: 'Error!',
              text: '' + this.checkErrors(error)
            })
            console.log(error);
            this.loading = false;
          });
  }
  getKeepers(){
  return  this.httpService.get(this.baseUrl + '/role=keeper/active=true').subscribe(data => {
      if (data != null) {
      this.dataSource2 = <Keeper[]>data;
      }
      this.dataSource = new MatTableDataSource(this.dataSource2);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      console.log( this.dataSource2);
        },   error => {
          console.log(error);
      }
      
      );

  }
  onNoClick(): void {
    this.dialogRef.close();
  }
  checkErrors(error): string {
    return ("Something went wrong!");
  }
}
