'use strict';

invoicecApp.controller('UserCtrl', ['$scope','Restangular','$modal',function ($scope,Restangular,$modal) {

    $scope.inInquiry = false;
    $scope.inEdit = false;


    //tablas helper
    var rUserTypes = Restangular.all('user/types');
    rUserTypes.getList().then(function(response){
      $scope.listUserType =response.data;
    });

    var rEmitters = Restangular.all('emitter/list');
    rEmitters.getList().then(function(response){
      $scope.listEmitter =response.data;
    });

    var rRoles = Restangular.all('role/list');
    rRoles.getList().then(function(response){
      $scope.listRole =response.data;
    });    


    //cambiamos customer de acuerdo a emisor
    $scope.$watch('ruc', function (newValue) {
      if (newValue) {
        var param = $scope.ruc.ruc;

        if (!$scope.inInquiry && !$scope.inEdit) {
          Restangular.all('customer/list').getList({id : param}).then(function(response){
            $scope.listCustomer =response.data;
          });
        }
      }
    });  


      $scope.clear = function() {
        $scope.user = {};
        $scope.ruc=null;
        $scope.role=null;
        $scope.rucCliente=null;
        $scope.tipoUsuario={};

        $scope.inInquiry=false;
        $scope.inEdit=false;
      };


      $scope.refreshInput = function(index) {
        $scope.user = $scope.listUser[index];
        $scope.tipoUsuario = $scope.getUserType($scope.listUser[index].tipoUsuario);
        $scope.ruc = $scope.getEmitter($scope.listUser[index].ruc);
        //refrezco customer segun emisor antes de buscarlo async 

        var param= $scope.ruc.ruc;
        Restangular.all('customer/list').getList({id : param}).then(function(response){
            $scope.listCustomer =response.data;
            $scope.rucCliente = $scope.getCustomer($scope.listUser[index].rucEmpresa);
        });

        $scope.role = $scope.getRole($scope.listUser[index].role);
      };

     $scope.inquiry = function(index) {
        $scope.inInquiry = true;
        $scope.inEdit = false;
        $scope.refreshInput(index);
      };

      $scope.edit = function(index) {
        $scope.inInquiry = false;
        $scope.inEdit = true;
        $scope.user = $scope.listUser[index];
        $scope.refreshInput(index);
      };

      $scope.refresh = function() {
        Restangular.all('user').get('list').then(function(response) {
          $scope.listUser=response.data;
        });
      };

      $scope.saveUser = function() {

        $scope.user.ruc = $scope.ruc.ruc;
        $scope.user.tipoUsuario = $scope.tipoUsuario.codigo;
        $scope.user.rucEmpresa = $scope.rucCliente.rucCliente;
        $scope.user.role = $scope.role.codRol;
        $scope.user.newRepeatPassword = $scope.user.newPassword;

        Restangular.all('user').post($scope.user).then(function(response) {
          console.log(response.data);
          $scope.refresh();
          $scope.clear();
        }, 
        function(response) {
          console.log(response.data);
        });
      };

      $scope.getUserType=function(id) {
      var type = {};
      angular.forEach($scope.listUserType, function(item) {
          if (item.codigo === id) {
            type=item;
          }
        });
        return type;
      };


      $scope.getEmitter=function(id) {
      var type = {};
      angular.forEach($scope.listEmitter, function(item) {
          if (item.ruc === id) {
            type=item;
          }
        });
        return type;
      };

      $scope.getCustomer=function(id) {
      var type = {};
      angular.forEach($scope.listCustomer, function(item) {

          if (item.rucCliente.trim() === id.trim()) {
            type=item;
          }
        });
        return type;
      };


      $scope.getRole=function(id) {
      var type = {};
      angular.forEach($scope.listRole, function(item) {

          if (item.codRol.trim() === id.trim()) {
            type=item;
          }
        });
        return type;
      };


      $scope.validInput = function () {
        if (!$scope.isUndefinedOrNull($scope.user)) {

          if ($scope.isUndefinedOrNull($scope.user.codUsuario)) {
            return false;
          }

          if ($scope.isUndefinedOrNull($scope.user.nombre)) {
            return false;
          }

          if ($scope.isUndefinedOrNull($scope.user.isActive)) {
            return false;
          }          

          if ($scope.inInquiry) return false;

          return true;

        }
    };

     $scope.isUndefinedOrNull = function(val) {
        return angular.isUndefined(val) || val === null || val === ''
    };


    $scope.openModalDelete = function(user) {
    $scope.items = [user];

    var modalInstance = $modal.open({
      templateUrl: 'DelModalContent.html',
          controller: 'DelModalInstanceCtrl',
          resolve: {
            items: function () {
                  return $scope.items;
            }
          }
    });

    modalInstance.result.then(function (selectedItem) {
      Restangular.one('user').remove({id:$scope.items[0].codUsuario,emitterId:$scope.items[0].ruc,type:$scope.items[0].tipoUsuario}).then(function(){
        $scope.refresh();  
      });
    }, function () {
    });
    };

}]);


invoicecApp.controller('UserPaginationCtrl', ['$scope',function ($scope) {
  $scope.$watch('listUser', function (newValue) {
    if (newValue) {
      $scope.totalItems = $scope.listUser.length;
      $scope.currentPage = 1;   
    }
  });
  $scope.currentPage = 1;
  $scope.itemsPerPage=10;
  $scope.setPage = function (pageNo) {
    $scope.currentPage = pageNo;
  };
}]);
