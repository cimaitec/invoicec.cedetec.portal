'use strict';

invoicecApp.controller('ConsultaCtrl', 
      ['$scope','Restangular','$modal','$timeout',
      function ($scope,Restangular,$modal,$timeout) {

              var sendData = { customerId : null, documentType : null, beginIssueDate : null,
                          endIssueDate : null, beginSequence : null, endSequence :  null };
      
              var rDocumentTypes = Restangular.all('document/type/list');
              rDocumentTypes.getList().then(function(response){
                          $scope.listDocTypes =response.data;
              });


              $scope.loadPage=function(){
                    /**var roles = Restangular.all('role/list');
                    roles.getList().then(function(response) {
                                            $scope.listRole=response.data;
                    });**/             
              }  

               /** funciones para aplicacion de Filtro **/
              var applyFilter = function (filterData) {
                $scope.pbValue=50;
                Restangular.one('document').post('listFilter',filterData).then(function(response) {
                  $scope.listDocuments = response.data;
                  $scope.pbValue=100;
                  $timeout(function(){$scope.pbResultRefresh=false;$scope.pbValue=0;}, 1000);
                });
              };
              applyFilter(sendData);
              
              $scope.refreshResults = function() {
                $scope.isCollapsed = true;
                $scope.pbResultRefresh = true;
                var sendData = { customerId : null, documentType : null, beginIssueDate : null,
                    endIssueDate : null, beginSequence : null, endSequence :  null };
                applyFilter(sendData);
              };


            $scope.filterResults = function() {
              $scope.pbResultRefresh = true;
              var idDoc = null;
              if (!$scope.isUndefinedOrNull($scope.filter.documentTypeSelected)) {
                         idDoc = $scope.filter.documentTypeSelected.id;
              } 
              var sendData = {
                customerId : null,
                documentType : idDoc,
                beginIssueDate : $scope.filter.beginIssueDate,
                endIssueDate : $scope.filter.endIssueDate,
                beginSequence : $scope.filter.beginSequence,
                endSequence :  $scope.filter.endSequence
              };
              applyFilter(sendData);
            };

            $scope.filterParamsOK = function() {
                if ($scope.isUndefinedOrNull($scope.filter)) {
                  return false;
                } else {
                  if (!$scope.isUndefinedOrNull($scope.filter.beginIssueDate) && !$scope.isUndefinedOrNull($scope.filter.endIssueDate)) {
                    return true;
                  } else {
                    if (!$scope.isUndefinedOrNull($scope.filter.beginSequence) && !$scope.isUndefinedOrNull($scope.filter.endSequence)) {
                      return true;
                    } else if (!$scope.isUndefinedOrNull($scope.filter.documentTypeSelected)) {
                      return true;
                    }
                  } 
                }
                return false;
              };

              $scope.filterParamsEmpty = function() {
                if ($scope.isUndefinedOrNull($scope.filter)) {
                  return true;
                } else {
                  return $scope.isUndefinedOrNull($scope.filter.beginIssueDate) && $scope.isUndefinedOrNull($scope.filter.endIssueDate) && $scope.isUndefinedOrNull($scope.filter.beginSequence)  && $scope.isUndefinedOrNull($scope.filter.endSequence) && $scope.isUndefinedOrNull($scope.filter.documentType); 
                }
              };

              $scope.filterClear = function() {
                if (!$scope.isUndefinedOrNull($scope.filter)) {
                  $scope.filter.beginIssueDate = null;
                  $scope.filter.endIssueDate = null;
                  $scope.filter.beginSequence = null;
                  $scope.filter.endSequence = null;
                  $scope.filter.documentTypeSelected = null;
                }
              };

              $scope.isUndefinedOrNull = function(val) {
                return angular.isUndefined(val) || val === null ;
              };

               /** Funciones para Formatear datos columnas de documento **/
               $scope.getDocumentTypeDesc=function(type) {
                var desc = '';
                angular.forEach($scope.listDocTypes, function(item) {
                    if (item.typeId === type) {
                      desc= item.descr;
                    }
                  });
                  return desc;
                };

              $scope.getCurrencyPrefix=function(type){
                var desc= type;
                //TODO : tomar de tabla de monedas
                if (type === 'PEN') {desc='S/.';}
                return desc;
              };  




/**
        $scope.inInquiry = false;
        $scope.inEdit = false;

        $scope.loadPage=function(){
            var roles = Restangular.all('role/list');
            roles.getList().then(function(response) {
                                    $scope.listRole=response.data;
            });             
        }  

        $scope.save = function(){
          Restangular.all('role').post($scope.role).then(function(response) {
                console.log(response.data);
                $scope.loadPage();
                $scope.clear();
              }, 
              function(response) {                
                console.log(response.data);
              });          
        };

        $scope.openModalDelete = function(role) {
                $scope.items = [role];
                var modalInstance = $modal.open({
                              templateUrl: 'DelModalContent.html',
                              controller: 'DelModalInstanceCtrl',
                              resolve: { items: function () { return $scope.items; } }
                });

                modalInstance.result.then(
                        function () {
                              Restangular.one('role').remove({id:$scope.items[0].codRol}).then(function()
                                              { $scope.loadPage(); });
                        }, 
                        function () {});
        };

        $scope.selectRole = function(index) {
          $scope.role = $scope.listRole[index];
        };

        $scope.clear = function() {
          $scope.inInquiry=false;
          $scope.inEdit=false;
          $scope.role = null;
        };

        $scope.edit = function(index) {
          $scope.inInquiry = false;
          $scope.inEdit = true;
          $scope.selectRole(index);
        };

        $scope.inquiry = function(index) {
          $scope.inInquiry = true;
          $scope.inEdit = false;
          $scope.selectRole(index);
        };

        $scope.validInput = function() {
            if (!$scope.isUndefinedOrNull($scope.role)) {
                  if ($scope.isUndefinedOrNull($scope.role.codRol)) { return false; }
                  if ($scope.isUndefinedOrNull($scope.role.descripcion)) { return false; }
                  if ($scope.isUndefinedOrNull($scope.role.isActive)) { return false; }          
                  if ($scope.inInquiry) return false;
                  return true;
            }
        };
**/
}]);  