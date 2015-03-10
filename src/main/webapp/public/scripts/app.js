'use strict';

var invoicecApp = angular.module('InvoicecPortalApp', [
    'ngResource',
    'ngRoute',
    'ngAnimate',
    'ngSanitize',
    'ngTouch',
    'ui.bootstrap',
    'restangular',
    'angularFileUpload'
  ])
  .config(['$routeProvider','$locationProvider',function ($routeProvider,$locationProvider) {
    $routeProvider
      .when('/cliente/login', {
        templateUrl: 'views/login.html',
        controller: 'ClienteLoginCtrl'
      })
      .when('/empresa/login', {
        templateUrl: 'views/login.html',
        controller: 'EmpresaLoginCtrl'
      })
      .when('/cliente/principal', {
        templateUrl: 'views/clientePrincipal.html',
        controller: 'ClientePrincipalCtrl'
      })
      .when('/cliente/principal/configuracion', {
        templateUrl: 'views/clienteConfiguracion.html',
        controller: 'ClienteConfiguracionCtrl'
      })
      .when('/empresa/principal', {
        templateUrl: 'views/empresaPrincipal.html',
        controller: 'EmpresaPrincipalCtrl'
      })
      .when('/db', {
        templateUrl: 'views/db.html',
        controller: 'DBCtrl'
      })
      .when('/victor', {
        templateUrl: 'views/victor.html',
        controller: 'VictorCtrl'
      })
      .when('/consulta', {
        templateUrl: 'views/documentClient.html',
        controller: 'DocumentClientCtrl'
      })
      .otherwise({
        redirectTo: '/empresa/login'
      });
    $locationProvider.html5Mode(false);
  }]);


invoicecApp.config(['RestangularProvider',function (RestangularProvider) {
        RestangularProvider.setBaseUrl('http://localhost:8080/Portal/api/v1');
        RestangularProvider.setFullResponse(true);
        RestangularProvider.setDefaultHeaders({'Content-Type': 'application/json'});
}]);


//manejo global entre controllers de datos de login
invoicecApp.factory('LoginFactory', 
      ['$window', '$location', 'Restangular', 
      function($window, $location, Restangular) {
            return {
                  setUserId : function(userId) { $window.sessionStorage.user = userId; },
                  getUserId : function() { return $window.sessionStorage.user; },
                  setUserName : function(userName) { $window.sessionStorage.name = userName; },
                  getUserName : function() { return $window.sessionStorage.name; },
                  setUserType : function(userType) { $window.sessionStorage.type = userType; },
                  getUserType : function() { return $window.sessionStorage.type; },
                  setUserRole : function(userRole) { $window.sessionStorage.role = userRole; },
                  getUserRole : function() { return $window.sessionStorage.role; },
                  logout : function() {
                                delete $window.sessionStorage.token;
                                delete $window.sessionStorage.user;
                                delete $window.sessionStorage.name;
                                delete $window.sessionStorage.type;
                                delete $window.sessionStorage.role;
                                Restangular.setDefaultHeaders({'Authorization': ''});
                                var urlPattern = new RegExp('cliente');
                                if (urlPattern.test($location.path())){ $location.path("/cliente/login");
                                } else { $location.path("/empresa/login"); }
                  }
            }
      }]);

//manejo global de no autorizado
invoicecApp.run(['Restangular','$location',function(Restangular, $location){
  Restangular.setErrorInterceptor(function(resp) {
    console.log('resp.status:' + resp.status);
    if (resp.status === 401 || resp.status === 0) {
      var searchPattern = new RegExp('login');
      if (!searchPattern.test($location.path())){
        //si no estoy en login , redireccionar a login
        var searchPattern2 = new RegExp('cliente');
        if (searchPattern2.test($location.path())){
          $location.path("/cliente/login");
        } else {
          $location.path("/empresa/login");
        }
        return false;
      } else {
        //estoy en login
      }
    };
  });
}]);




invoicecApp.filter('offset', function() {
  return function(input, start) {
    start = parseInt(start, 10);

    if ( input == null ) return null;

    return input.slice(start);
  };
});
