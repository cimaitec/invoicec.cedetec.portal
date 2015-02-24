'use strict';


invoicecApp.controller('ClienteConfiguracionCtrl', ['$scope','LoginFactory','Restangular','$window',function ($scope, LoginFactory, Restangular, $window) {
	Restangular.setDefaultHeaders({'Authorization': $window.sessionStorage.token});

	$scope.alerts = [];
	$scope.closeAlert = function(index) {
    	$scope.alerts.splice(index, 1);
  	};

	$scope.loggedUserName = LoginFactory.getUserName();

	var params = {
		id : LoginFactory.getUserId(),
		type : LoginFactory.getUserType()
	};
	Restangular.one('user').get(params).then(function(resp) {
		$scope.clientInfo = resp.data;
	});

	$scope.logout = function() {
		LoginFactory.logout();
	};

	$scope.validInput = function () {
		if (!$scope.isUndefinedOrNull($scope.clientInfo)) {
			if ($scope.isUndefinedOrNull($scope.clientInfo.emails)) {
				return false
			}
		};
		if ($scope.isUndefinedOrNull($scope.newPassword) && $scope.isUndefinedOrNull($scope.newPasswordRepeat)) {
			return true;
		}
		if (!$scope.isUndefinedOrNull($scope.newPassword) && !$scope.isUndefinedOrNull($scope.newPasswordRepeat)) {
			if ($scope.newPassword === $scope.newPasswordRepeat) {
				if (!$scope.isUndefinedOrNull($scope.password)) {
					return true;
				}
			}
		}		
	};

	
	$scope.isUndefinedOrNull = function(val) {
    	return angular.isUndefined(val) || val === null || val === ''
	};

	$scope.saveClientData = function () {
		var sendData = {};
		sendData.codUsuario = LoginFactory.getUserId();
		sendData.tipoUsuario = LoginFactory.getUserType();
		sendData.emails = $scope.clientInfo.emails;
		sendData.email = $scope.clientInfo.email;
		sendData.password = $scope.password;
		sendData.newPassword = $scope.newPassword;
		sendData.newRepeatPassword = $scope.newPasswordRepeat;
		Restangular.all('user').post(sendData).then(function(response) {
          	$scope.alerts.push({type : "success" , msg: 'Datos grabados correctamente!'});
          }, 
          function(response) {
          	$scope.alerts.push({type : "danger" , msg: 'Error al grabar datos' + response.data});
          });
	};

	$scope.passwordsFilled= function() {
		return $scope.isUndefinedOrNull($scope.newPassword) && $scope.isUndefinedOrNull($scope.newPasswordRepeat) && $scope.isUndefinedOrNull($scope.password);
	};

	$scope.clearPasswords = function() {
		$scope.password = '';
		$scope.newPassword = '';
		$scope.newPasswordRepeat = '';
	};

	$scope.emailEmpty = function() {
		if ($scope.isUndefinedOrNull($scope.clientInfo)) { 
			return false;
		} else {
			return $scope.isUndefinedOrNull($scope.clientInfo.email);	
		}
	};
}]);
