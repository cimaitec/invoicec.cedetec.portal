'use strict';

invoicecApp.controller('ConsultaCtrl', 
      ['$scope','Restangular','$modal','$timeout',
      function ($scope,Restangular,$modal,$timeout) {

               var rDocumentTypes = Restangular.all('document/type/list');
              rDocumentTypes.getList().then(function(response){
                          $scope.listDocTypes =response.data;
              });

              var customers = Restangular.all('customer/list');
              customers.getList().then(function(response){
                          $scope.listCustomer=response.data;
              });


              $scope.loadPage=function(){
                    applyFilter({ customerId : null, documentTypeId : null, beginIssueDate : null,
                                endIssueDate : null, beginSequence : null, endSequence :  null });             
              }  

              $scope.filter = function() {
                      $scope.pbResultRefresh = true;
                      
                      var customerId = null;
                      if (!$scope.isUndefinedOrNull($scope.filter.customerSelected)) {
                                 customerId = $scope.filter.customerSelected.id;
                      } 

                      var documentTypeId = null;
                      if (!$scope.isUndefinedOrNull($scope.filter.documentTypeSelected)) {
                                 documentTypeId = $scope.filter.documentTypeSelected.typeId;
                      } 
                      applyFilter({
                                    customerId : customerId,
                                    documentTypeId : documentTypeId,
                                    beginIssueDate : $scope.filter.beginIssueDate,
                                    endIssueDate : $scope.filter.endIssueDate,
                                    beginSequence : $scope.filter.beginSequence,
                                    endSequence :  $scope.filter.endSequence});
              };

              $scope.clear = function() {
                    if (!$scope.isUndefinedOrNull($scope.filter)) {
                          $scope.filter.beginIssueDate = null;
                          $scope.filter.endIssueDate = null;
                          $scope.filter.beginSequence = null;
                          $scope.filter.endSequence = null;
                          $scope.filter.documentTypeSelected = null;
                          $scope.filter.customerSelected = null;  
                    }
              };

              $scope.refresh = function() {
                      $scope.isCollapsed = true;
                      $scope.filter();
              };


                $scope.downloadFile = function(fileName){
                  Restangular.one('document/download').withHttpConfig({responseType: 'blob'}).get({id:fileName}).then(function(response) {
                    var blob=new Blob([response.data],{type:"application/octet"});
                    saveAs(blob,fileName);
                  });
                };






              $scope.openModalEmail = function(fileName) {
                      $scope.items = [fileName];
                      var modalInstance = $modal.open({
                        templateUrl: 'EmailModalContent.html',
                            controller: 'ModalInstanceCtrl',
                            resolve: {
                              items: function () {
                                    return $scope.items;
                              }
                            }
                      });
              };

              $scope.openModalReprocess = function(fileName) {
                      $scope.items = [fileName];
                      var modalInstance = $modal.open({
                        templateUrl: 'ReprocessModalContent.html',
                            controller: 'RepModalInstanceCtrl',
                            resolve: {
                              items: function () {
                                    return $scope.items;
                              }
                            }
                      });
              };

              var applyFilter = function (filterData) {
                      $scope.pbValue=50;
                      Restangular.one('document').post('listFilter',filterData).then(function(response) {
                              $scope.listDocuments = response.data;
                              $scope.pbValue=100;
                              $timeout(function(){$scope.pbResultRefresh=false;$scope.pbValue=0;}, 1000);
                      });
              };

              $scope.enableFilter = function() {
                            if ($scope.isUndefinedOrNull($scope.filter)) {
                                return false;
                            } else {
                                if (!$scope.isUndefinedOrNull($scope.filter.beginIssueDate) && !$scope.isUndefinedOrNull($scope.filter.endIssueDate)) {
                                        return true;
                                } 
                                if (!$scope.isUndefinedOrNull($scope.filter.beginSequence) && !$scope.isUndefinedOrNull($scope.filter.endSequence)) {
                                        return true;
                                }        
                                if (!$scope.isUndefinedOrNull($scope.filter.documentTypeSelected)) {
                                            return true; 
                                }
                                if (!$scope.isUndefinedOrNull($scope.filter.customerSelected)) {
                                            return true; 
                                }
                            };                
                            return false;
              };

              $scope.enableClear = function() {
                            if ($scope.isUndefinedOrNull($scope.filter)) {
                                    return true;
                            } else {
                                    return $scope.isUndefinedOrNull($scope.filter.beginIssueDate) && 
                                        $scope.isUndefinedOrNull($scope.filter.endIssueDate) && 
                                        $scope.isUndefinedOrNull($scope.filter.beginSequence)  && 
                                        $scope.isUndefinedOrNull($scope.filter.endSequence) && 
                                        $scope.isUndefinedOrNull($scope.filter.documentTypeSelected) &&
                                        $scope.isUndefinedOrNull($scope.filter.customerSelected); 
                            }
              };


              $scope.isUndefinedOrNull = function(val) {
                            return angular.isUndefined(val) || val === null ;
              };

               
               /** Funciones para Formatear datos columnas de documento **/
               $scope.getDocumentTypeDesc=function(type) {
                        var desc = '';
                        angular.forEach($scope.listDocTypes, function(item) {
                                                  if (item.typeId === type){ desc = item.descr;}
                        });
                        return desc;
                };

              $scope.getCurrencyPrefix=function(type){
                        var desc= type;
                        if (type === 'PEN') {desc='S/.';}
                        return desc;
              };  
}]);  