invoicecApp.controller('DBCtrl', ['$scope', 'LoginFactory', 'Restangular', '$window', '$timeout','$modal' , 
function ($scope, LoginFactory, Restangular, $window, $timeout, $modal) {
	
	Restangular.setDefaultHeaders({'Authorization': $window.sessionStorage.token});
	
	$scope.agregarEmisorUno = function() {   
        Restangular.all('emitteruno/save').post().then(function(response){
              $scope.correcto = "yeahhh";
        });
	}


	$scope.agregarEmisorDos = function() {   
        Restangular.all('emitterdos/save').post().then(function(response){
              $scope.correcto = "yeahhh";
        });
	}

	$scope.agregarDocumentoUno = function() {   
        Restangular.all('documentuno/save').post().then(function(response){
              $scope.correcto = "yeahhh";
        });
	}

	$scope.agregarDocumentoDos = function() {   
        Restangular.all('documentdos/save').post().then(function(response){
              $scope.correcto = "yeahhh";
        });
	}


}]);   
