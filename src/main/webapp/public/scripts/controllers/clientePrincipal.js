'use strict';

invoicecApp.controller('ClientePrincipalCtrl', ['$scope','LoginFactory','Restangular','$window','$timeout','$modal',function ($scope, LoginFactory, Restangular,$window,$timeout,$modal) {
	Restangular.setDefaultHeaders({'Authorization': $window.sessionStorage.token});
	//
	$scope.alerts = [];

	$scope.listDocuments = [];
	$scope.pbResultRefresh=false;
	$scope.pbValue=0;
	$scope.seccion = [];
	$scope.seccion[0]=true;
    $scope.seccion[1]=false;


				$scope.selSeccion = function(number) { 
                        for(var i=0;i<=1; i++) {
                            if (i!=number) { $scope.seccion[i] = false; } 
                              else { 
                                  $scope.seccion[i] = true;
                                  //$scope.clearData() ;
                              }
                        }
                };

	

}]);