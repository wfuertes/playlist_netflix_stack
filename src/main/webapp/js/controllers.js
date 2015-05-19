'use strict';

/* Controllers */
angular.module('myApp.controllers', [])
	.controller('FinanceController', function($scope, $http) {
		$scope.salary = 0;
		$scope.percentage = 0;
		
		$scope.calculate = function() {
			var request = {
				method: 'POST',
				url: '/nettec-lab/rest/hello/calc',
				headers: {
					'Content-Type': 'application/json',
					'Accept': 'application/json'	
				},
				data: { 
					salary: $scope.salary,
					percentage: $scope.percentage
				}
			};

			//$scope.salary * $scope.percentage * 0.01
			$http(request).success(function(data, status) {
				$scope.result = data.result;
			}).error(function(data) {
				alert('Something bad happened');
			});
		};
	});