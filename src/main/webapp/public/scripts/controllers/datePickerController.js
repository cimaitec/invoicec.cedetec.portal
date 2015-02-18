'use strict';

//se extrae por manejar distinto scope vs el scope del include
invoicecApp.controller('DatePickerController', ['$scope',function ($scope) {
  //parametros para manejo de fecha
    $scope.dateParamFormat = 'dd/MM/yyyy';
    $scope.dateParamOptions = {
      formatYear: 'yyyy',
      startingDay: 1
    };

    //fechai inicial
    $scope.openBeginDate = function($event) {
      $event.preventDefault();
      $event.stopPropagation();
      $scope.openedBeginDate = true;
    };

    $scope.openedBeginDate = false;

    //fecha final
    $scope.openEndDate = function($event) {
      $event.preventDefault();
      $event.stopPropagation();
      $scope.openedEndDate = true;
    };
    $scope.openedEndDate = false;
}]);
