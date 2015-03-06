                                  'use strict';    


                                  invoicecApp.controller('EmitterCtrl', 
                                    ['$scope','Restangular','$modal',
                                    function ($scope,Restangular,$modal) {
                                    $scope.inInquiry = false;
                                    $scope.inEdit = false;
                                    $scope.emitter = {};
                                    $scope.emitter.active = "F";



                                     $scope.loadPage=function(){
                                            
                                              var emitters = Restangular.all('emitter/list');
                                              emitters.getList().then(function(response) {
                                                                      $scope.listEmitter=response.data;
                                              });
                                     }  
 

                                    $scope.save = function() {
                                    //$scope.emitter = {"identification": "12312323"};

                                        Restangular.all('emitter').post($scope.emitter).then(function(response) {
                                              console.log(response.data);
                                              $scope.loadPage();
                                              $scope.clear();
                                        }, 
                                    function(response) {
                                      console.log(response.data);
                                    });
                                     };


                                    $scope.deleteModal = function(emitter) {
                                      $scope.items = [emitter];
                                    var modalInstance = $modal.open({
                                                  templateUrl: 'DelModalContent.html',
                                                  controller: 'DelModalInstanceCtrl',
                                                  resolve: { items: function () { return $scope.items; } }
                                    });

                                    modalInstance.result.then(
                                                  function () {
                                                        Restangular.one('emitter').remove({identification:$scope.items[0].identification}).then(
                                                                                                  function(){ $scope.loadPage(); });
                                                  }, 
                                                  function () {}
                                    );
                                    }; 



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

                           $scope.enableFilter = function() {
                            if ($scope.isUndefinedOrNull($scope.emitter)) {
                                return false;
                            } else {
                                if (!$scope.isUndefinedOrNull($scope.emitter.identification) 
                                && !$scope.isUndefinedOrNull($scope.emitter.name)
                                && !$scope.isUndefinedOrNull($scope.emitter.address)
                                && !$scope.isUndefinedOrNull($scope.emitter.pathGen)
                                && !$scope.isUndefinedOrNull($scope.emitter.patchRec)
                                && !$scope.isUndefinedOrNull($scope.emitter.pathSig)
                                && !$scope.isUndefinedOrNull($scope.emitter.pathAut)
                                && !$scope.isUndefinedOrNull($scope.emitter.pathRej)
                                && !$scope.isUndefinedOrNull($scope.emitter.pathJas)
                                && !$scope.isUndefinedOrNull($scope.emitter.smtpServer)
                                && !$scope.isUndefinedOrNull($scope.emitter.smtpPort)
                                && !$scope.isUndefinedOrNull($scope.emitter.smtpMail)
                                && !$scope.isUndefinedOrNull($scope.emitter.pathCertificate)
                                && !$scope.isUndefinedOrNull($scope.emitter.certificateType)
                                && !$scope.isUndefinedOrNull($scope.emitter.certificateAlias)
                                && !$scope.isUndefinedOrNull($scope.emitter.certificatePassword)
                                && !$scope.isUndefinedOrNull($scope.emitter.receiveMail)
                                && !$scope.isUndefinedOrNull($scope.emitter.userReceiveMail)
                                && !$scope.isUndefinedOrNull($scope.emitter.passReceiveMail)) {
                                        return true;
                                } 
                     
                            };                
                            return false;
              };



                            $scope.isUndefinedOrNull = function(val) {
                            return angular.isUndefined(val) || val === null ;
                            };               
                          

                                      $scope.getEmitter=function(id) {
                                                                  var emit = {};
                                                                  angular.forEach($scope.listEmitter, function(item) {
                                                                            if (item.identification === id) { emit=item; }
                                                                  });
                                                                  return emit;
                                                            }; 


                                     }]);    
