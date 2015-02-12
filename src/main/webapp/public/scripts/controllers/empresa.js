'use strict';

/**
 * @ngdoc function
 * @name invoicecPortalApp.controller:EmpresaCtrl
 * @description
 * # EmpresaCtrl
 * Controller of the invoicecPortalApp
 */
angular.module('InvoicecPortalApp')
  .controller('empresaCtrl', function ($scope) {
  	$scope.test='a';
  });

angular.module('InvoicecPortalApp').controller('AccordionDemoCtrl', function ($scope) {
  $scope.oneAtATime = true;

  $scope.groups = [
    {
      title: 'Dynamic Group Header - 1',
      content: 'Dynamic Group Body - 1'
    },
    {
      title: 'Dynamic Group Header - 2',
      content: 'Dynamic Group Body - 2'
    }
  ];

  $scope.items = ['Item 1', 'Item 2', 'Item 3'];

  $scope.addItem = function() {
    var newItemNo = $scope.items.length + 1;
    $scope.items.push('Item ' + newItemNo);
  };

  $scope.status = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
});