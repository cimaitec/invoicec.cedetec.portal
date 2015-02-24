'use strict';

invoicecApp.controller('ClientePrincipalCtrl', ['$scope','LoginFactory','Restangular','$window','$timeout','$modal',function ($scope, LoginFactory, Restangular,$window,$timeout,$modal) {
	Restangular.setDefaultHeaders({'Authorization': $window.sessionStorage.token});
	//
	$scope.alerts = [];

	$scope.listDocuments = [];
	$scope.pbResultRefresh=false;
	$scope.pbValue=0;
	$scope.seccion = [];
	$scope.seccion[0]=true;
    $scope.seccion[1]=false;


				$scope.selSeccion = function(number) { 
                        for(var i=0;i<=1; i++) {
                            if (i!=number) { $scope.seccion[i] = false; } 
                              else { 
                                  $scope.seccion[i] = true;
                                  //$scope.clearData() ;
                              }
                        }
                };

	var applyFilter = function (filterData) {
		$scope.pbValue=50;
		Restangular.one('document').post('customer/list',filterData).then(function(response) {
			$scope.listDocuments = response.data;
			$scope.pbValue=100;
			$timeout(function(){$scope.pbResultRefresh=false;$scope.pbValue=0;}, 1000);
		});
	}

	//tablas helper
	var rDocumentTypes = Restangular.all('combo/documenttype/list');
	rDocumentTypes.getList().then(function(response){
		$scope.listDocTypes =response.data;
	});

	//listado inicial
	var sendData = {
			customerId : LoginFactory.getUserId(),
			documentTypeId : null,
			beginIssueDate : null,
			endIssueDate : null,
			beginSequence : null,
			endSequence :  null	
	};

	$scope.pbResultRefresh=true;
	applyFilter(sendData);

	$scope.refreshResults = function() {
		$scope.isCollapsed = true;
		$scope.pbResultRefresh = true;
		var sendData = {
				customerId : LoginFactory.getUserId(),
				documentTypeId : null,
				beginIssueDate : null,
				endIssueDate : null,
				beginSequence : null,
				endSequence :  null	
		};
		applyFilter(sendData);
	};
	

	//nombre usuario firmado
	$scope.loggedUserName = LoginFactory.getUserName();
	//manejo de collapse de filtro
	$scope.isCollapsed = true;

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

  	//funcion validacion parametros filtro
  	$scope.filterParamsOK = function() {

  		if ($scope.isUndefinedOrNull($scope.filter)) {
  			return false;
  		} else {
  			if (!$scope.isUndefinedOrNull($scope.filter.beginIssueDate) && !$scope.isUndefinedOrNull($scope.filter.endIssueDate)) {
  				return true;
  			} else {
  				if (!$scope.isUndefinedOrNull($scope.filter.beginSequence) && !$scope.isUndefinedOrNull($scope.filter.endSequence)) {
  					return true;
  				} else if (!$scope.isUndefinedOrNull($scope.filter.documentType)) {
  					return true;
  				}
  			} 
  		}
  		return false;
  	};

  	$scope.filterParamsEmpty = function() {
  		if ($scope.isUndefinedOrNull($scope.filter)) {
  			return true;
  		} else {
  			return $scope.isUndefinedOrNull($scope.filter.beginIssueDate) && $scope.isUndefinedOrNull($scope.filter.endIssueDate) && $scope.isUndefinedOrNull($scope.filter.beginSequence)  && $scope.isUndefinedOrNull($scope.filter.endSequence) && $scope.isUndefinedOrNull($scope.filter.documentType);	
  		}
  	};

  	$scope.filterClear = function() {
  		if (!$scope.isUndefinedOrNull($scope.filter)) {
  			$scope.filter.beginIssueDate = null;
	  		$scope.filter.endIssueDate = null;
	  		$scope.filter.beginSequence = null;
	  		$scope.filter.endSequence = null;
	  		$scope.filter.documentTypeId = null;
	  	}
  	};

  	$scope.isUndefinedOrNull = function(val) {
    	return angular.isUndefined(val) || val === null ;
	};

	$scope.filterResults = function() {
		$scope.pbResultRefresh = true;
		var idDoc = null;
		if (!$scope.isUndefinedOrNull($scope.filter.documentType)) {
			idDoc = $scope.filter.documentType.idDocumento;
		} 
		var sendData = {
			customerId : LoginFactory.getUserId(),
			documentType : idDoc,
			beginIssueDate : $scope.filter.beginIssueDate,
			endIssueDate : $scope.filter.endIssueDate,
			beginSequence : $scope.filter.beginSequence,
			endSequence :  $scope.filter.endSequence
		};
		applyFilter(sendData);
	};
	

	$scope.logout = function() {
		LoginFactory.logout();
	};

	$scope.getDocumentTypeDesc=function(type) {
		var desc = '';
		angular.forEach($scope.listDocTypes, function(item) {
			if (item.typeId === type) {
				desc= item.descr;
			}
		});
		return desc;
	};

	$scope.getCurrencyPrefix=function(type){
		var desc= type;
		//TODO : tomar de tabla de monedas
		if (type === 'PEN') {desc='S/.'};
		return desc;
	};	

	$scope.downloadFile = function(fileName){
		Restangular.one('document/download').withHttpConfig({responseType: 'blob'}).get({id:fileName}).then(function(response) {
			var blob=new Blob([response.data],{type:"application/octet"});
			saveAs(blob,fileName);
		});
	};

	$scope.openModalEmail = function(fileName) {
		$scope.items = [fileName];

		var modalInstance = $modal.open({
			templateUrl: 'EmailModalContent.html',
      		controller: 'CliModalInstanceCtrl',
      		resolve: {
        		items: function () {
          				return $scope.items;
        		},
        		alerts : function() {
        				return $scope.alerts;
        		}
      		}
    	});
  	};

  	$scope.closeAlert = function(index) {
    	$scope.alerts.splice(index, 1);
  	};


}]);