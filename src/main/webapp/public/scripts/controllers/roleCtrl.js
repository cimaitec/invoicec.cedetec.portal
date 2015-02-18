'use strict';


invoicecApp.controller('RoleCtrl', 
  ['$scope','Restangular','$modal',
  function ($scope,Restangular,$modal) {
  
        $scope.inInquiry = false;
        $scope.inEdit = false;


        $scope.initFirst=function(){
            console.log('ejecutando mi init first');
            Restangular.all('role').get('list').then(function(response) {
                  $scope.listRole=response.data;
            });
        }  

        $scope.refreshInput = function(index) {
          $scope.role = $scope.listRole[index];
        };

        $scope.inquiry = function(index) {
          $scope.inInquiry = true;
          $scope.inEdit = false;
          $scope.refreshInput(index);
        };

        $scope.clear = function() {
          $scope.inInquiry=false;
          $scope.inEdit=false;
          $scope.role = null;
        };

        $scope.edit = function(index) {
          $scope.inInquiry = false;
          $scope.inEdit = true;
          $scope.refreshInput(index);
        };

        $scope.refresh = function() {
          Restangular.all('role').getList().then(function(response) {
            $scope.listRole=response.data;
          });
        };


        $scope.saveRole = function(){
          Restangular.all('role').post($scope.role).then(function(response) {
                console.log('yeahhhhhh');
                console.log(response.data);
                $scope.initFirst();
                $scope.clear();
              }, 
              function(response) {
                console.log('nooooooooo');
                console.log(response.data);
              });
          
        };

        $scope.validInput = function() {
          if (!$scope.isUndefinedOrNull($scope.role)) {

                if ($scope.isUndefinedOrNull($scope.role.codRol)) {
                  return false;
                }

                if ($scope.isUndefinedOrNull($scope.role.descripcion)) {
                  return false;
                }

                if ($scope.isUndefinedOrNull($scope.role.isActive)) {
                  return false;
                }          

                if ($scope.inInquiry) return false;

                return true;
          }
        };

        $scope.openModalDelete = function(role) {
          $scope.items = [role];

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
            Restangular.one('role').remove({id:$scope.items[0].codRol}).then(function(){
              $scope.refresh();  
            });
          }, function () {
          });
          };

}]);  
