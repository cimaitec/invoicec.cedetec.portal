'use strict';

invoicecApp.controller('ModalInstanceCtrl', ['$scope','$modalInstance','items', 'Restangular',function ($scope,$modalInstance,items, Restangular) {
  $scope.items = items;
  console.log("emp");
  $scope.ok = function () {
    Restangular.one('document/send').get({id:$scope.items[0],emails:$scope.listEmails});
    $modalInstance.close("sent");
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };

}]);


invoicecApp.controller('RepModalInstanceCtrl', ['$scope','$modalInstance','items', 'Restangular',function ($scope,$modalInstance,items, Restangular) {
  $scope.items = items;
  $scope.ok = function () {
    Restangular.one('document/rew').get({id:$scope.items[0]});
    $modalInstance.close("sent");
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };

}]);
