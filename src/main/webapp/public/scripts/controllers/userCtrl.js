'use strict';

invoicecApp.controller('UserCtrl', 
              ['$scope','Restangular','$modal',
              function ($scope,Restangular,$modal) {

                        $scope.inInquiry = false;
                        $scope.inEdit = false;


                       //tablas helper
                        var rUserTypes = Restangular.all('combo/usertype/list');
                        rUserTypes.getList().then(function(response){
                          $scope.listUserType =response.data;
                        });

                        var rEmitters = Restangular.all('combo/emitter/list');
                        rEmitters.getList().then(function(response){
                          $scope.listEmitter =response.data;
                        });

                        var rRoles = Restangular.all('combo/role/list');
                        rRoles.getList().then(function(response){
                          $scope.listRole =response.data;
                        });    


                        //cambiamos customer de acuerdo a emisor
                        $scope.$watch('emitterSelected', function (newValue) {
                          if (newValue) {
                            var emitterIdentification = $scope.emitterSelected.identification;

                            if (!$scope.inInquiry && !$scope.inEdit) {
                              Restangular.all('customer/list/emitter').getList({emitterId : emitterIdentification}).then(function(response){
                                $scope.listCustomer =response.data;
                              });
                            }
                          }
                        });  

                        $scope.loadPage = function() {
                         
                        var user = Restangular.all('user/list');
                        user.getList().then(function(response){
                        $scope.listUser=response.data;
                                    });
                                    };

                        $scope.saveUser = function() {

                        $scope.user.ruc = $scope.emitterSelected.identification;
                        $scope.user.tipoUsuario = $scope.tipoUsuario.codigo;
                        $scope.user.rucEmpresa = $scope.rucCliente.identification;
                        $scope.user.role = $scope.role.codRol;
                        $scope.user.newRepeatPassword = $scope.user.newPassword;

                        Restangular.all('user').post($scope.user).then(function(response) {
                          console.log(response.data);
                          //$scope.refresh();
                          $scope.clear();
                          $scope.loadPage();
                        }, 
                        function(response) {
                          console.log(response.data);
                        });
                      };

                          $scope.openModalDelete = function(user) {
                                            $scope.items = [user];
                                            var modalInstance = $modal.open({
                                                      templateUrl: 'DelModalContent.html',
                                                      controller: 'DelModalInstanceCtrl',
                                                      resolve: {
                                  items: function () {
                                        return $scope.items;}}
                          });

                          modalInstance.result.then(function (selectedItem) {
                            Restangular.one('user').remove({id:$scope.items[0].codUsuario,emitterId:$scope.items[0].ruc,type:$scope.items[0].tipoUsuario}).then(function(){
                            $scope.loadPage();  
                            });
                        }, 
                            function () {}
                                    );
                          };

                         $scope.inquiry = function(index) {
                            $scope.inInquiry = true;
                            $scope.inEdit = false;
                            $scope.user = $scope.listUser[index];
                            $scope.tipoUsuario = $scope.getUserType($scope.listUser[index].tipoUsuario);
                            $scope.emitterSelected = $scope.getEmitter($scope.listUser[index].ruc);
                            //$scope.rucCliente = $scope.getCustomer($scope.listUser[index].rucEmpresa);
                            $scope.role = $scope.getRole($scope.listUser[index].role);
                           
                            $scope.refreshInput(index);
                          };

                          $scope.edit = function(index) {
                            $scope.inInquiry = false;
                            $scope.inEdit = true;
                            $scope.user = $scope.listUser[index];
                            $scope.tipoUsuario = $scope.getUserType($scope.listUser[index].tipoUsuario);
                            $scope.emitterSelected = $scope.getEmitter($scope.listUser[index].ruc);
                            //$scope.rucCliente = $scope.getCustomer($scope.listUser[index].rucEmpresa);
                            $scope.role = $scope.getRole($scope.listUser[index].role);
                            $scope.refreshInput(index);
                          };

                        $scope.refreshInput = function(index) {
                        $scope.user = $scope.listUser[index];
                        
                        var customers = Restangular.all('customer/list');
                                    customers.getList().then(function(response){
                                            $scope.listCustomer=response.data;
                          $scope.rucCliente = $scope.getCustomer($scope.listUser[index].rucEmpresa);
                         
                          });
                          };

                          $scope.clear = function() {
                                  $scope.user = {};
                                  $scope.ruc={};
                                  $scope.role={};
                                  $scope.rucCliente={}

                                  $scope.tipoUsuario={};
                                  $scope.emitterSelected={};

                                  $scope.inInquiry=false;
                                  $scope.inEdit=false;
                                   $scope.loadPage();
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
                                          if (item.identification === id) {
                                            type=item;
                                          }
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


                                      $scope.getRole=function(id) {
                                      var type = {};
                                      angular.forEach($scope.listRole, function(item) {

                                          if (item.codRol.trim() === id.trim()) {
                                            type=item;
                                          }
                                        });
                                        return type;
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
