'use strict'; 


invoicecApp.controller('ContingencyCtrl', ['$scope','Restangular','FileUploader','$window',function ($scope,Restangular,FileUploader,$window) {

$scope.envTypes = [{type : '1', value : 'Pruebas'},{type : '2', value : 'Producción'}];
//valor por defecto
$scope.env = $scope.envTypes[0];
var uploader = $scope.uploader = new FileUploader({
  url : 'http://localhost:8080/PortalFacturaElectronica/api/v1/contingency/upload',
//  headers : {'Authorization': $window.sessionStorage.token}
});


uploader.onAfterAddingFile = function(addedFileItems) {
      addedFileItems.formData  =  [{type : $scope.env.type}];
      console.info('onAfterAddingAll', addedFileItems);
};

}]);