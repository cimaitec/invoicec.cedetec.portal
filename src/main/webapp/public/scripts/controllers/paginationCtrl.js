'use strict';

invoicecApp.controller('PaginationCtrl', ['$scope',function ($scope) {
  $scope.$watch('listDocuments', function (newValue) {
    if (newValue) {
      $scope.totalItems = $scope.listDocuments.length;
      $scope.currentPage = 1;   
    }
  });
  $scope.currentPage = 1;
  $scope.itemsPerPage=10;
  $scope.setPage = function (pageNo) {
    $scope.currentPage = pageNo;
  };

  
}]);