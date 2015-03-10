'use strict';

invoicecApp.controller('ClienteLoginCtrl', 
     ['$scope', '$window', '$location', 
      'LoginFactory', 'Restangular', 
      function ($scope, $window, $location, LoginFactory, Restangular){
                
                $scope.incorrectCredentials=false;
                $scope.login = function(isValid) {
                      $scope.loginMessage='';
                      if (isValid) {
                              $scope.user.userType = 'C';
                              Restangular.all('user/login').post($scope.user).then(
                                      function(response) { 
                                              Restangular.setDefaultHeaders({'Authorization': response.data.token});
                                              $window.sessionStorage.token = response.data.token;
                                              //actualizo datos usuario para resto controllers.
                                              LoginFactory.setUserId($scope.user.userName);
                                              LoginFactory.setUserName(response.data.userDescription);
                                              LoginFactory.setUserType('C');
                                              //redireccionar a pagina principal
                                              $location.path('/cliente/principal');
                                        },
                                        function(response){ //respuesta fallo
                                              delete $window.sessionStorage.token;
                                              $scope.incorrectCredentials=true; 
                                              if (response.status == 401) {
                                                      $scope.loginMessage = "Usuario o Password Incorrecto!.";                                                  
                                                    } else {                                                  
                                                      $scope.loginMessage = "Error de conexión a servicio.";
                                              }
                                        });
                      }


                };

      }]);


invoicecApp.controller('EmpresaLoginCtrl', 
     ['$scope', '$window', '$location', 
      'LoginFactory', 'Restangular', 
      function ($scope, $window, $location, LoginFactory, Restangular){
                
                $scope.incorrectCredentials=false;
                $scope.login = function(isValid) {
                      $scope.loginMessage='';
                      if (isValid) {
                              $scope.user.userType = 'E';
                              Restangular.all('user/login').post($scope.user).then(
                                      function(response) { 
                                              Restangular.setDefaultHeaders({'Authorization': response.data.token});
                                              $window.sessionStorage.token = response.data.token;
                                              //actualizo datos usuario para resto controllers.
                                              LoginFactory.setUserId($scope.user.userName);
                                              LoginFactory.setUserName(response.data.userDescription);
                                              LoginFactory.setUserType('E');
                                              LoginFactory.setUserRole(response.data.userRole);
                                              //redireccionar a pagina principal
                                              $location.path('/empresa/principal');
                                        },
                                        function(response){ //respuesta fallo
                                              delete $window.sessionStorage.token;
                                              $scope.incorrectCredentials=true; 
                                              if (response.status == 401) {
                                                      $scope.loginMessage = "Usuario o Password Incorrecto!.";                                                  
                                                    } else {                                                  
                                                      $scope.loginMessage = "Error de conexión a servicio.";
                                              }
                                        });
                      }


                };

      }]);
