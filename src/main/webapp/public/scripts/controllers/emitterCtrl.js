                                  'use strict';    


                                  invoicecApp.controller('EmitterCtrl', 
                                    ['$scope','Restangular','$modal',
                                    function ($scope,Restangular,$modal) {
                                    $scope.inInquiry = false;
                                    $scope.inEdit = false;



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



                                    $scope.validInput = function () {
                                    if (!$scope.isUndefinedOrNull($scope.emitter)) {
                                    if ($scope.isUndefinedOrNull($scope.emitter.identification)) { return true; }
                                    if ($scope.isUndefinedOrNull($scope.client.emitter.name)) { return true;}
                                    if ($scope.isUndefinedOrNull(emitter.businessName)) { return true; }
                                    if ($scope.isUndefinedOrNull(emitter.address)) { return true; }
                                    if ($scope.isUndefinedOrNull($scope.emitter.active)) { return true; }
                                    if ($scope.isUndefinedOrNull($scope.emitter.pathGen)) { return true; }
                                    if ($scope.isUndefinedOrNull($scope.emitter.patchRec)) { return true; }
                                    if ($scope.isUndefinedOrNull($scope.emitter.pathSig)) { return true; }
                                    if ($scope.isUndefinedOrNull($scope.emitter.pathAut)) { return true; }
                                    if ($scope.isUndefinedOrNull($scope.emitter.pathRej)) { return true; }
                                    if ($scope.isUndefinedOrNull($scope.emitter.pathJas)) { return true; }
                                    if ($scope.isUndefinedOrNull($scope.emitter.smtpServer)) { return true; }
                                    if ($scope.isUndefinedOrNull($scope.emitter.smtpPort)) { return true; }
                                    if ($scope.isUndefinedOrNull($scope.emitter.smtpUser)) { return true; }
                                    if ($scope.isUndefinedOrNull($scope.emitter.smtpPassword)) { return true; }
                                    if ($scope.isUndefinedOrNull($scope.emitter.smtpMail)) { return true; }
                                    if ($scope.isUndefinedOrNull($scope.emitter.pathCertificate)) { return true; }
                                    if ($scope.isUndefinedOrNull($scope.emitter.certificateType)) { return true; }
                                    if ($scope.isUndefinedOrNull($scope.emitter.certificateAlias)) { return true; }
                                    if ($scope.isUndefinedOrNull($scope.emitter.certificatePassword)) { return true; }
                                    if ($scope.inInquiry) return true;
                                    return true;
                                    }
                                    };

                                      $scope.getEmitter=function(id) {
                                                                  var emit = {};
                                                                  angular.forEach($scope.listEmitter, function(item) {
                                                                            if (item.identification === id) { emit=item; }
                                                                  });
                                                                  return emit;
                                                            }; 


                                     }]);    
