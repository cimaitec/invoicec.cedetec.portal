'use strict';

invoicecApp.controller('RoleCtrl', 
  ['$scope','Restangular','$modal',
  function ($scope,Restangular,$modal) {
  
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

}]);  