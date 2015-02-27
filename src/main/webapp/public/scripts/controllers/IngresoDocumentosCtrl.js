                                  'use strict';    


                                  invoicecApp.controller('IngresoDocumentosCtrl', 
                                    ['$scope','Restangular','$modal',
                                    function ($scope,Restangular,$modal) {
                                    $scope.inInquiry = false;
                                    $scope.inEdit = false;
                                    $scope.document={};
                                    $scope.formVisibility = false;
                                    

                                    var rDocumentTypes = Restangular.all('combo/idtype/list');
                                      rDocumentTypes.getList().then(function(response){
                                      $scope.listIdTypes =response.data;
                                     });

                                     //cambiamos customer de acuerdo a emisor
                                    $scope.$watch('company', function (newValue) {
                                    if (newValue) {
                                       var emitterIdentification = $scope.company.identification;

                                        if (!$scope.inInquiry && !$scope.inEdit) {
                                        Restangular.all('customer/list/emitter').getList({emitterId : emitterIdentification}).then(function(response){
                                        $scope.listCustomer =response.data;
                                      });
                                      }
                                      }
                                    });  

                                  
                                     $scope.loadPage=function(){
                                              //var emitters = Restangular.all('emitter/list');
                                              var emitters = Restangular.all('combo/emitter/list');
                                              emitters.getList().then(function(response) {
                                                                      $scope.listEmitter=response.data;
                                              $scope.document = {};
                                              });
                                     }


                                    $scope.save = function() {
                                     $scope.document.company = $scope.company.identification;
                                     $scope.document.customer = $scope.customer.identification;
                                     $scope.document.currency = $scope.currency.descripcion;
                                     $scope.document.documentTypeCode = "01"

                                     Restangular.all('document').post($scope.document).then(function(response) {
                                      console.log(response.data);
                                      $scope.formVisibility = false;
                                      $scope.loadPage();
                                      //$scope.clear();
                                     }, 
                                     function(response) {
                                      console.log(response.data);
                                      });
                                    };  


                                    $scope.ShowForm = function() {
                                      $scope.formVisibility = true;
                                      console.log($scope.formVisibility)
                                     } 
 

                                    $scope.getIdType=function(idType) {
                                        var type = {};
                                            angular.forEach($scope.listIdTypes, function(item) {
                                              if (item.descripcion === descripcion) { type= item; }
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
                                        

                                    $scope.geCurrencyType=function(idType) {
                                        var type = {};
                                            angular.forEach($scope.listIdCurrency, function(item) {
                                              if (item.codigo === idType) { type= item; }
                                             });
                                              return type;
                                         }; 


                                      $scope.getCustomer=function(id) {
                                      var type = {};
                                      angular.forEach($scope.listCustomer, function(item) {

                                          if (item.identification.trim() === id.trim()) {
                                            type=item;
                                          }
                                        });
                                        return type;
                                      };                  

                                  
                          }]);    
