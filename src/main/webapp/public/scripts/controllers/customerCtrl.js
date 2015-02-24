'use strict';


invoicecApp.controller('CustomerCtrl', 
              ['$scope','Restangular','$modal', 
              function ($scope,Restangular,$modal) {

                          $scope.inInquiry=false;
                          $scope.inEdit = false;
                          $scope.tipoIdentificacion={};
                          $scope.moneda={};
                          
                          var rDocumentTypes = Restangular.all('combo/idtype/list');
                          rDocumentTypes.getList().then(function(response){
                            $scope.listIdTypes =response.data;
                          });

                          var rEmitters = Restangular.all('combo/emitter/list');
                          rEmitters.getList().then(function(response){
                            $scope.listEmitter =response.data;
                          });


                          $scope.loadPage = function() {
                                    var customers = Restangular.all('customer/list');
                                    customers.getList().then(function(response){
                                            $scope.listCustomer=response.data;
                                    });
                          };

                          $scope.save = function() {
                                    $scope.client.identificationEmitter = $scope.emitterSelected.identification;
                                    $scope.client.identificationTypeCode = $scope.identificationTypeSelected.codigo;                            
                                    Restangular.all('customer').post($scope.client).then(function(response) {
                                      console.log(response.data);
                                      $scope.loadPage();
                                      $scope.clear();
                                    }, 
                                    function(response) {
                                      console.log(response.data);
                                    });
                          };


                          $scope.openModalDelete = function(customer) {
                                    $scope.items = [customer];
                                    var modalInstance = $modal.open({
                                                  templateUrl: 'DelModalContent.html',
                                                  controller: 'DelModalInstanceCtrl',
                                                  resolve: { items: function () { return $scope.items; } }
                                    });

                                    modalInstance.result.then(
                                                  function () {
                                                        Restangular.one('customer').remove({identification:$scope.items[0].identification, 
                                                                                 identificationEmitter:$scope.items[0].identificationEmitter}).then(
                                                                                                  function(){ $scope.loadPage(); });
                                                  }, 
                                                  function () {}
                                    );
                          };
                          
                          $scope.inquiry = function(index) {
                                    $scope.inInquiry = true;
                                    $scope.inEdit = false;
                                    $scope.client = $scope.listCustomer[index];
                                    $scope.identificationTypeSelected = $scope.getIdType($scope.listCustomer[index].identificationTypeCode);
                                    $scope.emitterSelected=$scope.getEmitter($scope.listCustomer[index].identificationEmitter);
                          };


                          $scope.edit = function(index) {
                                      $scope.inInquiry = false;
                                      $scope.inEdit = true;
                                      $scope.client = $scope.listCustomer[index];
                                      $scope.identificationTypeSelected = $scope.getIdType($scope.listCustomer[index].identificationTypeCode);
                                      $scope.emitterSelected=$scope.getEmitter($scope.listCustomer[index].identificationEmitter);                                      
                          };

                          $scope.clear = function() {
                                    $scope.client = null;
                                    $scope.emitterSelected=null;
                                    $scope.identificationTypeSelected=null;
                                    $scope.inInquiry=false;
                                    $scope.inEdit=false;
                          };

                          $scope.validInput = function () {
                                if (!$scope.isUndefinedOrNull($scope.client)) {
                                    if ($scope.isUndefinedOrNull($scope.client.email)) { return false; }
                                    if ($scope.isUndefinedOrNull($scope.client.rucCliente)) { return false;}
                                    if ($scope.isUndefinedOrNull($scope.client.razonSocial)) { return false; }
                                    if ($scope.isUndefinedOrNull($scope.client.direccion)) { return false; }
                                    if ($scope.isUndefinedOrNull($scope.client.telefono)) { return false; }
                                    if ($scope.inInquiry) return false;
                                    return true;
                                }
                          };
                          $scope.getIdType=function(idType) {
                                var type = {};
                                angular.forEach($scope.listIdTypes, function(item) {
                                            if (item.codigo === idType) { type= item; }
                                });
                                return type;
                          };

                          $scope.getEmitter=function(id) {
                                var emit = {};
                                angular.forEach($scope.listEmitter, function(item) {
                                          if (item.identification === id) { emit=item; }
                                });
                                return emit;
                          }; 

                          $scope.isUndefinedOrNull = function(val) {
                                return angular.isUndefined(val) || val === null || val === '';
                          };
}]);


invoicecApp.controller('CustomerPaginationCtrl', ['$scope',function ($scope) {
  $scope.$watch('listCustomer', function (newValue) {
    if (newValue) {
      $scope.totalItems = $scope.listCustomer.length;
      $scope.currentPage = 1;   
    }
  });
  $scope.currentPage = 1;
  $scope.itemsPerPage=10;
  $scope.setPage = function (pageNo) {
    $scope.currentPage = pageNo;
  };
}]);

