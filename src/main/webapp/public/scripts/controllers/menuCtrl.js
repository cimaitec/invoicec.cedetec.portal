'use strict';

invoicecApp.controller('MenuCtrl', ['$scope','Restangular','$modal',function ($scope,Restangular,$modal) {
$scope.opcion0 = false;
$scope.opcion01 = false;
$scope.opcion1 = false;
$scope.opcion11 = false;
$scope.opcion12 = false;
$scope.opcion13 = false;
$scope.opcion14 = false;
$scope.opcion2 = false;
$scope.opcion21 = false;
$scope.opcion22 = false;
$scope.opcion23 = false;
$scope.opcion3 = false;
$scope.opcion31 = false;


$scope.toggleOptions = function(index) {
  
  if (index == 0) $scope.opcion01 = $scope.opcion0;
  if (index == 1) $scope.opcion0 = $scope.opcion01;
  if (index == 2) {
    $scope.opcion11 = $scope.opcion1; 
    $scope.opcion12 = $scope.opcion1; 
    $scope.opcion13 = $scope.opcion1; 
    $scope.opcion14 = $scope.opcion1; 
  }
  //TODO
  
};

}]);