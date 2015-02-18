'use strict';

invoicecApp.controller('CustomerCtrl', ['$scope','Restangular','$modal',function ($scope,Restangular,$modal) {
  $scope.inInquiry=false;
  $scope.inEdit = false;
  $scope.tipoIdentificacion={};
  $scope.moneda={};
  //tablas helper
  var rDocumentTypes = Restangular.all('customer/idTypes');
  rDocumentTypes.getList().then(function(response){
    $scope.listIdTypes =response.data;
  });

  var rEmitters = Restangular.all('emitter/list');
  rEmitters.getList().then(function(response){
    $scope.listEmitter =response.data;
  });


    var rCurrency = Restangular.all('document/currencyTypes');
  rCurrency.getList().then(function(response){
    $scope.listIdCurrency =response.data;
  });



  $scope.inquiry = function(index) {
    $scope.inInquiry = true;
    $scope.inEdit = false;
    $scope.client = $scope.listCustomer[index];
    $scope.tipoIdentificacion=$scope.getIdType($scope.listCustomer[index].tipoIdentificacion);
    $scope.ruc=$scope.getEmitter($scope.listCustomer[index].ruc);
  };
  

  $scope.edit = function(index) {
    $scope.inInquiry = false;
    $scope.inEdit = true;

    $scope.client = $scope.listCustomer[index];
    $scope.tipoIdentificacion=$scope.getIdType($scope.listCustomer[index].tipoIdentificacion);
    $scope.ruc=$scope.getEmitter($scope.listCustomer[index].ruc);
  };

  $scope.saveClient = function() {
    $scope.client.ruc = $scope.ruc.ruc;
    $scope.client.tipoIdentificacion = $scope.tipoIdentificacion.codigo;

    Restangular.all('customer').post($scope.client).then(function(response) {
      console.log(response.data);
      $scope.refresh();
    }, 
    function(response) {
      console.log(response.data);
    });
  };

  $scope.refreshList = function() {
     Restangular.all('customer').get('list').then(function(response) {
        $scope.listCustomer=response.data;
      });
  };

  $scope.getIdType=function(idType) {
    var type = {};
    angular.forEach($scope.listIdTypes, function(item) {
      if (item.codigo === idType) {
        type= item;
      }
    });
    return type;
  };

  $scope.getEmitter=function(id) {
    var emit = {};
    angular.forEach($scope.listEmitter, function(item) {
      if (item.ruc === id) {
        emit=item;
      }
    });
    return emit;
  }; 

  $scope.clear = function() {
    $scope.client = {};
    $scope.ruc=null;
    $scope.tipoIdentificacion={};

    $scope.inInquiry=false;
    $scope.inEdit=false;
  };


  $scope.validInput = function () {
      if (!$scope.isUndefinedOrNull($scope.client)) {

        if ($scope.isUndefinedOrNull($scope.client.email)) {
          return false;
        }

        if ($scope.isUndefinedOrNull($scope.client.rucCliente)) {
          return false;
        }

        if ($scope.isUndefinedOrNull($scope.client.razonSocial)) {
          return false;
        }

        if ($scope.isUndefinedOrNull($scope.client.direccion)) {
          return false;
        }

        if ($scope.isUndefinedOrNull($scope.client.telefono)) {
          return false;
        }

        if ($scope.inInquiry) return false;

        return true;

      }


    };

  $scope.isUndefinedOrNull = function(val) {
        return angular.isUndefined(val) || val === null || val === '';
  };

 $scope.openModalDelete = function(customer) {
    $scope.items = [customer];

    var modalInstance = $modal.open({
      templateUrl: 'DelModalContent.html',
          controller: 'DelModalInstanceCtrl',
          resolve: {
            items: function () {
                  return $scope.items;
            }
          }
      });

    modalInstance.result.then(function (selectedItem) {
      Restangular.one('customer').remove({id:$scope.items[0].rucCliente,emitterId:$scope.items[0].ruc}).then(function(){

        $scope.refreshList();  

      });

    }, function () {

    });

    };

    

}]);


invoicecApp.controller('CustomerPaginationCtrl', ['$scope',function ($scope) {
  $scope.$watch('listCustomer', function (newValue) {
    if (newValue) {
      $scope.totalItems = $scope.listCustomer.length;
      $scope.currentPage = 1;   
    }
  });
  $scope.currentPage = 1;
  $scope.itemsPerPage=10;
  $scope.setPage = function (pageNo) {
    $scope.currentPage = pageNo;
  };
}]);

