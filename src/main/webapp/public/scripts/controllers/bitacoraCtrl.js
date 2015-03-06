'use strict';

invoicecApp.controller('BitacoraCtrl', 
      ['$scope','Restangular','$modal','$timeout',
      function ($scope,Restangular,$modal,$timeout) {

      	$scope.listDocument = {};

            
              
            $scope.loadPage = function() {
            var customers = Restangular.all('documentLog/list');
            customers.getList().then(function(response){
                      $scope.listDocument=response.data;
                                    });
                          };

            $scope.enableFilter = function() {
                            if ($scope.isUndefinedOrNull($scope.filter)) {
                                return false;
                            } else {
                                if (!$scope.isUndefinedOrNull($scope.filter.beginIssueDate) && !$scope.isUndefinedOrNull($scope.filter.endIssueDate)) {
                                        return true;
                                } 
                                if (!$scope.isUndefinedOrNull($scope.filter.beginSequence) && !$scope.isUndefinedOrNull($scope.filter.endSequence)) {
                                        return true;
                                }        
                                if (!$scope.isUndefinedOrNull($scope.filter.documentTypeSelected)) {
                                            return true; 
                                }
                                if (!$scope.isUndefinedOrNull($scope.filter.customerSelected)) {
                                            return true; 
                                }
                            };                
                            return false;
              };



   }]); 