'use strict';

invoicecApp.controller('EmpresaPrincipalCtrl', 
          ['$scope', 'LoginFactory', 'Restangular', 
          '$window', '$timeout','$modal', 
          function ($scope, LoginFactory, Restangular, $window, $timeout, $modal) {
      
               //Configuramos Headers
              Restangular.setDefaultHeaders({'Authorization': $window.sessionStorage.token});

               //inicializamos variables para menus
              $scope.accordionStatus = {open1 : true, open2 : false , open3 : false , open4 : false, open5 : false};
              $scope.accordionOneAtATime = true;
              $scope.seccion = [];
              $scope.seccion[0]=true;
              $scope.seccion[1]=false;
              $scope.seccion[2]=false;
              $scope.seccion[3]=false;
              $scope.seccion[4]=false;
              $scope.seccion[5]=false;
              $scope.seccion[6]=false;
              $scope.seccion[7]=false;
              $scope.seccion[8]=false;
              $scope.seccion[9]=false;
              
              //Declaramos e inicializamos variables para funcionalidad
              $scope.filter = {};
              $scope.listDocuments = [];
              $scope.pbResultRefresh=false;
              $scope.pbValue=0;
              $scope.pbResultRefresh=true;
              $scope.isCollapsed = true;
              $scope.loggedUser = LoginFactory.getUserName();
      

            
      
              /** funcion para logout**/
              $scope.logout = function() { LoginFactory.logout(); };
              
              /** funciones para seleccion de menu **/
              $scope.selSeccion = function(number) { 
                        for(var i=0;i<=9; i++) {
                            if (i!=number) { $scope.seccion[i] = false; } 
                              else { 
                                  $scope.seccion[i] = true;
                                  $scope.clearData() ;
                              }
                        }
                };

              $scope.clearData = function() {
                $scope.listDocument = null;
                $scope.listCustomer = null;
                $scope.listUser = null;
                $scope.listRole = null;
                $scope.listEmitter = null;
              };

      

             /** funciones para permisos **/
             $scope.allowed = function() {
                if (LoginFactory.getUserRole() == 'Admin') return true; 
                return false;
              };

              $scope.disableButtons = function(status) {
                if (status.trim() == 'AT') return true;
              };


              var rCurrency = Restangular.all('combo/currencytype/list');
              rCurrency.getList().then(function(response){
                            $scope.listIdCurrency =response.data;
              });
}]);