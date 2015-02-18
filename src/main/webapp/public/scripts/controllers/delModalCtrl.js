'use strict';


invoicecApp.controller('DelModalInstanceCtrl', ['$scope','$modalInstance','items', 'Restangular',function ($scope,$modalInstance,items, Restangular) {
  $scope.items = items;
  $scope.ok = function () {
    $modalInstance.close($scope.items);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };

}]);
