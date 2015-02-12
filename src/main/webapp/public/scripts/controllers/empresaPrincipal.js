'use strict';

invoicecApp.controller('EmpresaPrincipalCtrl', ['$scope', 'LoginFactory', 'Restangular', '$window', '$timeout','$modal' , function ($scope, LoginFactory, Restangular, $window, $timeout, $modal) {
    Restangular.setDefaultHeaders({'Authorization': $window.sessionStorage.token});

    //config menu izq
    $scope.accordionStatus = {open1 : true, open2 : false , open3 : false , open4 : false};
    $scope.accordionOneAtATime = true;

    //usuario firmado
    $scope.loggedUser = LoginFactory.getUserName();

    $scope.logout = function() {
      LoginFactory.logout();
    };

    $scope.filter = {};

    //secciones a mostrar
    $scope.seccion = [];

    $scope.seccion[0]=true;
    $scope.seccion[1]=false;
    $scope.seccion[2]=false;
    $scope.seccion[3]=false;
    $scope.seccion[4]=false;
    $scope.seccion[5]=false;
    $scope.seccion[6]=false;
    $scope.seccion[7]=false;
    $scope.seccion[8]=false;
    $scope.seccion[9]=false;

    $scope.selSeccion = function(number) { 
      for(var i=0;i<=9; i++) {
        if (i!=number) {
          $scope.seccion[i] = false;
        } else {
          $scope.seccion[i] = true;

          $scope.clearData() ;

          if (number == 1) {
            $scope.customerSec();
          } 

          if (number == 2) {
            $scope.userSec();
          }

          if (number == 3) {
            $scope.roleSec();
          }

          if (number == 4) {
            $scope.roleMenuSec();
          }

          if (number == 8) {
            $scope.emitterSec();
          }
        }
      }
    };

    $scope.clearData = function() {
      $scope.listDocument = null;
      $scope.listCustomer = null;
      $scope.listUser = null;
      $scope.listRole = null;
      $scope.listEmitter = null;
    };

    $scope.customerSec = function() {
      Restangular.all('customer').get('list').then(function(response) {
        $scope.listCustomer=response.data;
      });
    };

    $scope.userSec = function() {
      Restangular.all('user').get('list').then(function(response) {
        $scope.listUser=response.data;
      });
    };

    $scope.roleSec = function() {
      Restangular.all('role').get('list').then(function(response) {
        $scope.listRole=response.data;
      });
    };

    $scope.roleMenuSec = function() {
      //usa los mismos roles para el select
      $scope.roleSec();
    };

    $scope.emitterSec = function() {
      Restangular.all('emitter').get('list').then(function(response) {
        $scope.listEmitter=response.data;
      });
    };


    $scope.listDocuments = [];
    $scope.pbResultRefresh=false;
    $scope.pbValue=0;

    
    var applyFilter = function (filterData) {
      $scope.pbValue=50;
      Restangular.one('document').post('listFilter',filterData).then(function(response) {
        $scope.listDocuments = response.data;
        $scope.pbValue=100;
        $timeout(function(){$scope.pbResultRefresh=false;$scope.pbValue=0;}, 1000);
      });
    };


      //tablas helper
    var rDocumentTypes = Restangular.all('document/type/list');
    rDocumentTypes.getList().then(function(response){
      $scope.listDocTypes =response.data;
    });

    //listado inicial
    var sendData = {
        customerId : null,
        documentType : null,
        beginIssueDate : null,
        endIssueDate : null,
        beginSequence : null,
        endSequence :  null 
    };

    $scope.refreshResults = function() {
      $scope.isCollapsed = true;
      $scope.pbResultRefresh = true;
      var sendData = {
          customerId : null,
          documentType : null,
          beginIssueDate : null,
          endIssueDate : null,
          beginSequence : null,
          endSequence :  null 
      };
      applyFilter(sendData);
    };

    $scope.pbResultRefresh=true;
    applyFilter(sendData);

    //manejo de collapse de filtro
    $scope.isCollapsed = true;

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
    if (type === 'PEN') {desc='S/.';}
    return desc;
  };  

   

  $scope.filterResults = function() {
    $scope.pbResultRefresh = true;
    var idDoc = null;
    if (!$scope.isUndefinedOrNull($scope.filter.documentType)) {
      idDoc = $scope.filter.documentType.idDocumento;
    } 
    var sendData = {
      customerId : null,
      documentType : idDoc,
      beginIssueDate : $scope.filter.beginIssueDate,
      endIssueDate : $scope.filter.endIssueDate,
      beginSequence : $scope.filter.beginSequence,
      endSequence :  $scope.filter.endSequence
    };
    applyFilter(sendData);
  };

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
        $scope.filter.documentType = null;
      }
    };

    $scope.isUndefinedOrNull = function(val) {
      return angular.isUndefined(val) || val === null ;
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
          controller: 'ModalInstanceCtrl',
          resolve: {
            items: function () {
                  return $scope.items;
            }
          }
      });
    };

    $scope.openModalReprocess = function(fileName) {
    $scope.items = [fileName];

    var modalInstance = $modal.open({
      templateUrl: 'ReprocessModalContent.html',
          controller: 'RepModalInstanceCtrl',
          resolve: {
            items: function () {
                  return $scope.items;
            }
          }
      });
    };


    $scope.allowed = function() {
      if (LoginFactory.getUserRole() == 'Admin') return true; 
      return false;
    };

    $scope.disableButtons = function(status) {
      if (status.trim() == 'AT') return true;
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

invoicecApp.controller('CustomerCtrl', ['$scope','Restangular','$modal',function ($scope,Restangular,$modal) {
  $scope.inInquiry=false;
  $scope.inEdit = false;
  $scope.tipoIdentificacion={};
  //tablas helper
  var rDocumentTypes = Restangular.all('customer/idTypes');
  rDocumentTypes.getList().then(function(response){
    $scope.listIdTypes =response.data;
  });

  var rEmitters = Restangular.all('emitter/list');
  rEmitters.getList().then(function(response){
    $scope.listEmitter =response.data;
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


invoicecApp.controller('DelModalInstanceCtrl', ['$scope','$modalInstance','items', 'Restangular',function ($scope,$modalInstance,items, Restangular) {
  $scope.items = items;
  $scope.ok = function () {
    $modalInstance.close($scope.items);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };

}]);


invoicecApp.controller('UserCtrl', ['$scope','Restangular','$modal',function ($scope,Restangular,$modal) {

    $scope.inInquiry = false;
    $scope.inEdit = false;


    //tablas helper
    var rUserTypes = Restangular.all('user/types');
    rUserTypes.getList().then(function(response){
      $scope.listUserType =response.data;
    });

    var rEmitters = Restangular.all('emitter/list');
    rEmitters.getList().then(function(response){
      $scope.listEmitter =response.data;
    });

    var rRoles = Restangular.all('role/list');
    rRoles.getList().then(function(response){
      $scope.listRole =response.data;
    });    


    //cambiamos customer de acuerdo a emisor
    $scope.$watch('ruc', function (newValue) {
      if (newValue) {
        var param = $scope.ruc.ruc;

        if (!$scope.inInquiry && !$scope.inEdit) {
          Restangular.all('customer/list').getList({id : param}).then(function(response){
            $scope.listCustomer =response.data;
          });
        }
      }
    });  


      $scope.clear = function() {
        $scope.user = {};
        $scope.ruc=null;
        $scope.role=null;
        $scope.rucCliente=null;
        $scope.tipoUsuario={};

        $scope.inInquiry=false;
        $scope.inEdit=false;
      };


      $scope.refreshInput = function(index) {
        $scope.user = $scope.listUser[index];
        $scope.tipoUsuario = $scope.getUserType($scope.listUser[index].tipoUsuario);
        $scope.ruc = $scope.getEmitter($scope.listUser[index].ruc);
        //refrezco customer segun emisor antes de buscarlo async 

        var param= $scope.ruc.ruc;
        Restangular.all('customer/list').getList({id : param}).then(function(response){
            $scope.listCustomer =response.data;
            $scope.rucCliente = $scope.getCustomer($scope.listUser[index].rucEmpresa);
        });

        $scope.role = $scope.getRole($scope.listUser[index].role);
      };

     $scope.inquiry = function(index) {
        $scope.inInquiry = true;
        $scope.inEdit = false;
        $scope.refreshInput(index);
      };

      $scope.edit = function(index) {
        $scope.inInquiry = false;
        $scope.inEdit = true;
        $scope.user = $scope.listUser[index];
        $scope.refreshInput(index);
      };

      $scope.refresh = function() {
        Restangular.all('user').get('list').then(function(response) {
          $scope.listUser=response.data;
        });
      };

      $scope.saveUser = function() {

        $scope.user.ruc = $scope.ruc.ruc;
        $scope.user.tipoUsuario = $scope.tipoUsuario.codigo;
        $scope.user.rucEmpresa = $scope.rucCliente.rucCliente;
        $scope.user.role = $scope.role.codRol;
        $scope.user.newRepeatPassword = $scope.user.newPassword;

        Restangular.all('user').post($scope.user).then(function(response) {
          console.log(response.data);
          $scope.refresh();
          $scope.clear();
        }, 
        function(response) {
          console.log(response.data);
        });
      };

      $scope.getUserType=function(id) {
      var type = {};
      angular.forEach($scope.listUserType, function(item) {
          if (item.codigo === id) {
            type=item;
          }
        });
        return type;
      };


      $scope.getEmitter=function(id) {
      var type = {};
      angular.forEach($scope.listEmitter, function(item) {
          if (item.ruc === id) {
            type=item;
          }
        });
        return type;
      };

      $scope.getCustomer=function(id) {
      var type = {};
      angular.forEach($scope.listCustomer, function(item) {

          if (item.rucCliente.trim() === id.trim()) {
            type=item;
          }
        });
        return type;
      };


      $scope.getRole=function(id) {
      var type = {};
      angular.forEach($scope.listRole, function(item) {

          if (item.codRol.trim() === id.trim()) {
            type=item;
          }
        });
        return type;
      };


      $scope.validInput = function () {
        if (!$scope.isUndefinedOrNull($scope.user)) {

          if ($scope.isUndefinedOrNull($scope.user.codUsuario)) {
            return false;
          }

          if ($scope.isUndefinedOrNull($scope.user.nombre)) {
            return false;
          }

          if ($scope.isUndefinedOrNull($scope.user.isActive)) {
            return false;
          }          

          if ($scope.inInquiry) return false;

          return true;

        }
    };

     $scope.isUndefinedOrNull = function(val) {
        return angular.isUndefined(val) || val === null || val === ''
    };


    $scope.openModalDelete = function(user) {
    $scope.items = [user];

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
      Restangular.one('user').remove({id:$scope.items[0].codUsuario,emitterId:$scope.items[0].ruc,type:$scope.items[0].tipoUsuario}).then(function(){
        $scope.refresh();  
      });
    }, function () {
    });
    };

}]);


invoicecApp.controller('UserPaginationCtrl', ['$scope',function ($scope) {
  $scope.$watch('listUser', function (newValue) {
    if (newValue) {
      $scope.totalItems = $scope.listUser.length;
      $scope.currentPage = 1;   
    }
  });
  $scope.currentPage = 1;
  $scope.itemsPerPage=10;
  $scope.setPage = function (pageNo) {
    $scope.currentPage = pageNo;
  };
}]);

invoicecApp.controller('RoleCtrl', ['$scope','Restangular','$modal',function ($scope,Restangular,$modal) {
  $scope.inInquiry = false;
  $scope.inEdit = false;

  $scope.refreshInput = function(index) {
    $scope.role = $scope.listRole[index];
  };

  $scope.inquiry = function(index) {
    $scope.inInquiry = true;
    $scope.inEdit = false;
    $scope.refreshInput(index);
  };

  $scope.clear = function() {
    $scope.inInquiry=false;
    $scope.inEdit=false;
    $scope.role = null;
  };

  $scope.edit = function(index) {
    $scope.inInquiry = false;
    $scope.inEdit = true;
    $scope.refreshInput(index);
  };

  $scope.refresh = function() {
    Restangular.all('role').get('list').then(function(response) {
      $scope.listRole=response.data;
    });
  };


  $scope.saveRole = function(){
    Restangular.all('role').post($scope.role).then(function(response) {
          console.log(response.data);
          $scope.refresh();
          $scope.clear();
        }, 
        function(response) {
          console.log(response.data);
        });
  };

  $scope.validInput = function() {
    if (!$scope.isUndefinedOrNull($scope.role)) {

          if ($scope.isUndefinedOrNull($scope.role.codRol)) {
            return false;
          }

          if ($scope.isUndefinedOrNull($scope.role.descripcion)) {
            return false;
          }

          if ($scope.isUndefinedOrNull($scope.role.isActive)) {
            return false;
          }          

          if ($scope.inInquiry) return false;

          return true;
    }
  };

  $scope.openModalDelete = function(role) {
    $scope.items = [role];

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
      Restangular.one('role').remove({id:$scope.items[0].codRol}).then(function(){
        $scope.refresh();  
      });
    }, function () {
    });
    };

}]);  


invoicecApp.controller('MenuCtrl', ['$scope','Restangular','$modal',function ($scope,Restangular,$modal) {
$scope.opcion0 = false;
$scope.opcion01 = false;
$scope.opcion1 = false;
$scope.opcion11 = false;
$scope.opcion12 = false;
$scope.opcion13 = false;
$scope.opcion14 = false;
$scope.opcion2 = false;
$scope.opcion21 = false;
$scope.opcion22 = false;
$scope.opcion23 = false;
$scope.opcion3 = false;
$scope.opcion31 = false;


$scope.toggleOptions = function(index) {
  
  if (index == 0) $scope.opcion01 = $scope.opcion0;
  if (index == 1) $scope.opcion0 = $scope.opcion01;
  if (index == 2) {
    $scope.opcion11 = $scope.opcion1; 
    $scope.opcion12 = $scope.opcion1; 
    $scope.opcion13 = $scope.opcion1; 
    $scope.opcion14 = $scope.opcion1; 
  }
  //TODO
  
};

}]);    


invoicecApp.controller('EmitterCtrl', ['$scope','Restangular','$modal',function ($scope,Restangular,$modal) {
  $scope.inInquiry = false;
  $scope.inEdit = false;


 $scope.inquiry = function(index) {
    $scope.inInquiry = true;
    $scope.inEdit = false;
    $scope.refreshInput(index);
  };

  $scope.edit = function(index) {
    $scope.inInquiry = false;
    $scope.inEdit = true;
    $scope.refreshInput(index);
  };

 $scope.refreshInput = function(index) {
    $scope.emitter = $scope.listEmitter[index];
  };

  $scope.clear = function() {
    $scope.inInquiry = false;
    $scope.inEdit = false;
    $scope.emitter = null;
  };


}]);    

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

