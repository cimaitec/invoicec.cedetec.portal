'use strict';


invoicecApp.controller('CliModalInstanceCtrl', ['$scope','$modalInstance','items', 'Restangular', 'LoginFactory','alerts',function ($scope,$modalInstance,items, Restangular, LoginFactory,alerts) {
	$scope.items = items;
	$scope.alerts = alerts;
	//seteo correo por defecto = del usuario.
	var params = {
		id : LoginFactory.getUserId(),
		type : LoginFactory.getUserType()
	};
	Restangular.one('user').get(params).then(function(resp) {
		$scope.listEmails = resp.data.email;
	});


	$scope.ok = function () {
		Restangular.one('document/send').get({id:$scope.items[0],emails:$scope.listEmails}).then(function(resp) {
			$scope.alerts.push({type : "success" , msg: 'Correo enviado correctamente!.'});
			$modalInstance.close("sent");
		}, 
		function(resp) {
			$scope.alerts.push({type : "danger" , msg: 'Error al enviar correo.'});
			$modalInstance.close("sent");
		});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};

}]);


invoicecApp.controller('DropDownCtrl', ['$scope',function ($scope) {
  $scope.status = {
    isopen: false
  };

  $scope.toggleDropdown = function($event) {
    $event.preventDefault();
    $event.stopPropagation();
    $scope.status.isopen = !$scope.status.isopen;
  };
}]);

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