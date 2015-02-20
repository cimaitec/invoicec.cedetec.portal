'use strict';    


invoicecApp.controller('EmitterCtrl', ['$scope','Restangular','$modal',function ($scope,Restangular,$modal) {
  $scope.inInquiry = false;
  $scope.inEdit = false;

   $scope.loadPage=function(){
            var emitters = Restangular.all('emitter/list');
            emitters.getList().then(function(response) {
                                    $scope.listEmitter=response.data;
            });
   }  
 

 $scope.inquiry = function(index) {
    $scope.inInquiry = true;
    $scope.inEdit = false;
    $scope.refreshInput(index);
  };

  $scope.edit = function(index) {
    $scope.inInquiry = false;
    $scope.inEdit = true;
    $scope.refreshInput(index);
  };

 $scope.refreshInput = function(index) {
    $scope.emitter = $scope.listEmitter[index];
  };

  $scope.clear = function() {
    $scope.inInquiry = false;
    $scope.inEdit = false;
    $scope.emitter = null;
  };


}]);    
