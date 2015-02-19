'use strict';


invoicecApp.controller('DelModalInstanceCtrl', 
      ['$scope','$modalInstance','items', 
      function ($scope, $modalInstance, items) {
              $scope.items = items;
              $scope.ok = function () {
                $modalInstance.close();
              };

              $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
              };

}]);


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


