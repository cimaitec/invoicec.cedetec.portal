'use strict';

invoicecApp.controller('DocumentClientCtrl', 
      ['$scope','Restangular','$modal','$timeout',
      function ($scope,Restangular,$modal,$timeout) {

                           var rDocumentTypes = Restangular.all('combo/documenttype/list');
                          rDocumentTypes.getList().then(function(response){
                                      $scope.listDocTypes =response.data;
                          });

                          var customers = Restangular.all('combo/customer/list');
                          customers.getList().then(function(response){
                                      $scope.listCustomer=response.data;
                          });

              
                          var rEmitters = Restangular.all('combo/emitter/list');
                          rEmitters.getList().then(function(response){
                            $scope.listEmitter =response.data;
                          });


              $scope.loadPage=function(){
                    //applyFilter({ emitterId : null, documentTypeId : null, legalNumber : null, customerId : null });             
              }  

              $scope.filter = function() {
                      $scope.pbResultRefresh = true;

                      var emitterId = null;
                      if (!$scope.isUndefinedOrNull($scope.filter.emitterSelected)) {
                                 emitterId = $scope.filter.emitterSelected.identification;
                      }

                      var documentTypeId = null;
                      if (!$scope.isUndefinedOrNull($scope.filter.documentTypeSelected)) {
                                 documentTypeId = $scope.filter.documentTypeSelected.typeId;
                      }

                      var legalNumber = null;
                      if (!$scope.isUndefinedOrNull($scope.filter.inSerie) && !$scope.isUndefinedOrNull($scope.filter.inNumero)) {
                                 legalNumber = $scope.filter.inSerie+'-'+$scope.filter.inNumero;
                      }

                      var customerId = null;
                      if (!$scope.isUndefinedOrNull($scope.filter.customerId)) {
                                 customerId = $scope.filter.customerId;
                      }


                      applyFilter({
                                    emitterId : emitterId,
                                    documentTypeId : documentTypeId,
                                    legalNumber : legalNumber,
                                    customerId : customerId});
              };

              $scope.clear = function() {
                    if (!$scope.isUndefinedOrNull($scope.filter)) {
                          $scope.filter.emitterSelected = null;
                          $scope.filter.documentTypeSelected = null;
                          $scope.filter.customerId = null;
                          $scope.filter.inNumero = null;
                          $scope.filter.inSerie = null;
                    }
              };


              var applyFilter = function (filterData) {
                      $scope.pbValue=50;
                      Restangular.one('client/document').post('filter',filterData).then(function(response) {
                              $scope.listDocuments = response.data;
                              $scope.pbValue=100;
                              $timeout(function(){$scope.pbResultRefresh=false;$scope.pbValue=0;}, 1000);
                      });
              };

              $scope.downloadFile = function(documentTypeCode, legalNumber){
                      Restangular.one('document/download').
                        withHttpConfig({responseType: 'blob'}).get({documentTypeCode:documentTypeCode, legalNumber:legalNumber}).then(function(response) {
                                        var blob=new Blob([response.data],{type:"application/octet"});
                                        saveAs(blob,legalNumber);
                                });
              };

              $scope.refresh = function() {
                      $scope.isCollapsed = true;
                      $scope.filter();
              };

              $scope.enableFilter = function() {
                    if ($scope.isUndefinedOrNull($scope.filter)) {
                        return false;
                    } else {
                        if (!$scope.isUndefinedOrNull($scope.filter.emitterSelected)      && 
                            !$scope.isUndefinedOrNull($scope.filter.inNumero)             && 
                            !$scope.isUndefinedOrNull($scope.filter.inSerie)              &&
                            !$scope.isUndefinedOrNull($scope.filter.documentTypeSelected) &&
                            !$scope.isUndefinedOrNull($scope.filter.customerId)           ) {
                                return true;
                        } 
                    };                
                    return false;
              };

              $scope.enableClear = function() {
                    if ($scope.isUndefinedOrNull($scope.filter)) {
                            return true;
                    } else {
                            return $scope.isUndefinedOrNull($scope.filter.emitterSelected) && 
                                $scope.isUndefinedOrNull($scope.filter.documentTypeSelected) && 
                                $scope.isUndefinedOrNull($scope.filter.customerId)  && 
                                $scope.isUndefinedOrNull($scope.filter.inNumero) && 
                                $scope.isUndefinedOrNull($scope.filter.inSerie); 
                    }
              };


              $scope.isUndefinedOrNull = function(val) {
                            return angular.isUndefined(val) || val === null || val === '';
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