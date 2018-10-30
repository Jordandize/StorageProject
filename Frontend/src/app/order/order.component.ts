import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({templateUrl: 'order.component.html'})
export class OrderComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router
    // private authenticationService: AuthenticationService,
    // private alertService: AlertService
  ) {}
  ngOnInit() {
    // this.orderForm = this.formBuilder.group({
    //   username: ['', Validators.required],
    //   password: ['', Validators.required]
    // });

    // reset login status
    // this.authenticationService.logout();

    // get return url from route parameters or default to '/'
    //this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  // angular.module('Commande', []).controller('commandeController', ['$scope', function($scope) {
  //
  //   $scope.articles = [
  //     { id: 1, reference: 123, titre: "MSI GTX 980ti", prixUnitaire: 666.63, quantite: 0, montantHT: 666.63, montantTTC: 799.95 },
  //     { id: 2, reference: 456, titre: "Intel Core i7-4770K", prixUnitaire: 324.96, quantite: 0, montantHT: 324.96, montantTTC: 389.95 },
  //     { id: 3, reference: 789, titre: "ASUS MAXIMUS VII RANGER", prixUnitaire: 134.96, quantite: 0, montantHT: 134.96, montantTTC: 161.95 }
  //   ];
  //
  //
  //
  //   $scope.PrixTotalTTC = function() {
  //     var resultTTC = 0;
  //
  //     angular.forEach($scope.articles, function (article) {
  //       resultTTC += article.montantTTC * article.quantite;
  //     });
  //     return resultTTC;
  //   };
  //
  //   $scope.PrixTotalHT = function() {
  //     var resultHT = 0;
  //
  //     angular.forEach($scope.articles, function (article) {
  //       resultHT += article.montantHT * article.quantite;
  //     });
  //     return resultHT;
  //   };
  //
  //   $scope.NombreArticle = function() {
  //     var resultArticle = 0;
  //
  //     angular.forEach($scope.articles, function(article){
  //       resultArticle += article.quantite;
  //     });
  //     return resultArticle;
  //   };
  //
  //   $scope.AjouterArticle = function() {
  //     $scope.articles.push({
  //       id: '',
  //       reference: '',
  //       titre: '',
  //       prixUnitaire: 0,
  //       quantite: 0,
  //       montantHT: 0,
  //       montantTTC: 0
  //     });
  //   };
  //
  //   $scope.SupprimerArticle = function(index) {
  //     $scope.articles.splice(index, 1);
  //   };
  //
  // }])
}


