'use strict';

invoicecApp.controller('ClienteLoginCtrl', ['$scope', '$http', '$window', '$location', 'LoginFactory','Restangular', function ($scope,$http,$window,$location, LoginFactory, Restangular) {

    $scope.incorrectCredentials=false;

    //funcion principal de logueo
    $scope.login = function(isValid) {
      $scope.incorrectCredentials=false;
      $scope.loginMessage='';
      if (isValid) {
        $scope.user.userType = 'C';
        Restangular.all('user/login').post($scope.user)
          .then(function(response) { //respuesta ok
            Restangular.setDefaultHeaders({'Authorization': response.data.token});
            $window.sessionStorage.token = response.data.token;
            //actualizo datos usuario para resto controllers.
            LoginFactory.setUserId($scope.user.userName);
            LoginFactory.setUserName(response.data.userDescription);
            LoginFactory.setUserType('C');
            //redireccionar a pagina principal
            $location.path('/cliente/principal');
          }, function(response){ //respuesta fallo
            delete $window.sessionStorage.token;
            if (response.status == 401) {
              $scope.loginMessage = "Usuario o Password Incorrecto!.";
              $scope.incorrectCredentials=true; 
            } else {
              $scope.incorrectCredentials=true; 
              $scope.loginMessage = "Error de conexión a servicio.";
            }
          });
      }
  };
  }]);

invoicecApp.controller('EmpresaLoginCtrl', ['$scope','$http','$window','$location','LoginFactory','Restangular',function ($scope,$http,$window,$location, LoginFactory, Restangular) {

    $scope.incorrectCredentials=false;

    //funcion principal de logueo
    $scope.login = function(isValid) {
      $scope.incorrectCredentials=false;
      $scope.loginMessage='';
      if (isValid) {
        //convierto json a form-encoded
        $scope.user.userType = 'E';
        Restangular.all('user/login').post($scope.user)
          .then(function(response) { //respuesta ok
            Restangular.setDefaultHeaders({'Authorization': response.data.token});
            $window.sessionStorage.token = response.data.token;
            //actualizo datos usuario para resto controllers.
            LoginFactory.setUserId($scope.user.userName);
            LoginFactory.setUserName(response.data.userDescription);
            LoginFactory.setUserType('E');
            LoginFactory.setUserRole(response.data.userRole);
            //redireccionar a pagina principal
            $location.path('/empresa/principal');
          }, function(response){ //respuesta fallo
            delete $window.sessionStorage.token;
            if (response.status == 401) {
              $scope.loginMessage = "Usuario o Password Incorrecto!.";
              $scope.incorrectCredentials=true; 
            } else {
              $scope.incorrectCredentials=true; 
              $scope.loginMessage = "Error de conexión a servicio.";
            }
          });
      }
  };
  }]);


invoicecApp.controller('MarioCtrl', ['$scope','$http','$window','$location','LoginFactory','Restangular',function ($scope,$http,$window,$location, LoginFactory, Restangular) {

    $scope.marioes="il capo";
    var rDocumentTypes = Restangular.all('document/type/list');
    rDocumentTypes.getList().then(function(response){
      $scope.listDocTypes =response.data;
    });


  }]);
