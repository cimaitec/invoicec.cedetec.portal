'use strict';

invoicecApp.controller('EmpresaPrincipalCtrl', 
    ['$scope', 'LoginFactory', 'Restangular', 
    '$window', '$timeout','$modal', 
    function ($scope, LoginFactory, Restangular, $window, $timeout, $modal) {
       //Configuramos Headers
      Restangular.setDefaultHeaders({'Authorization': $window.sessionStorage.token});

      //inicializamos variables para menus
      $scope.accordionStatus = {open1 : true, open2 : false , open3 : false , open4 : false};
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
      
      var sendData = { customerId : null, documentType : null, beginIssueDate : null,
          endIssueDate : null, beginSequence : null, endSequence :  null };
      
      var rDocumentTypes = Restangular.all('document/type/list');
      rDocumentTypes.getList().then(function(response){
        $scope.listDocTypes =response.data;
      });
      

      

      /** funcion para logout**/
      $scope.logout = function() { LoginFactory.logout(); };
      
      /** funciones para seleccion de menu **/
      $scope.selSeccion = function(number) { 
          for(var i=0;i<=9; i++) {
              if (i!=number) { $scope.seccion[i] = false; } 
                else { 
                    $scope.seccion[i] = true;
                    $scope.clearData() ;
                    if (number == 1) { $scope.customerSec(); } 
                    if (number == 2) { $scope.userSec(); }
                    if (number == 3) { $scope.roleSec(); }
                    if (number == 4) { $scope.roleMenuSec(); }
                    if (number == 8) { $scope.emitterSec(); }
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

      $scope.customerSec = function() {
        //Restangular.all('customer').get('list').then(function(response) {
        //  $scope.listCustomer=response.data;
        //});
      };

      $scope.userSec = function() {
        Restangular.all('user').get('list').then(function(response) {
          $scope.listUser=response.data;
        });
      };

      $scope.roleSec = function() {
        //Restangular.all('role').get('list').then(function(response) {
         // $scope.listRole=response.data;
        //});
      };

      $scope.roleMenuSec = function() {
          $scope.roleSec();  //usa los mismos roles para el select
      };

      $scope.emitterSec = function() {
        Restangular.all('emitter').get('list').then(function(response) {
          $scope.listEmitter=response.data;
        });
      };


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
        var sendData = {
            customerId : null,
            documentType : null,
            beginIssueDate : null,
            endIssueDate : null,
            beginSequence : null,
            endSequence :  null 
        };
        applyFilter(sendData);
      };


    $scope.filterResults = function() {
      $scope.pbResultRefresh = true;
      var idDoc = null;
      if (!$scope.isUndefinedOrNull($scope.filter.documentType)) {
        idDoc = $scope.filter.documentType.idDocumento;
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
            } else if (!$scope.isUndefinedOrNull($scope.filter.documentType)) {
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
          $scope.filter.documentType = null;
        }
      };

      $scope.isUndefinedOrNull = function(val) {
        return angular.isUndefined(val) || val === null ;
      };



     /** funciones para permisos **/
     $scope.allowed = function() {
        if (LoginFactory.getUserRole() == 'Admin') return true; 
        return false;
      };

      $scope.disableButtons = function(status) {
        if (status.trim() == 'AT') return true;
      };


     /** Funciones para DOWNLOAD Archivos **/
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

 
}]);