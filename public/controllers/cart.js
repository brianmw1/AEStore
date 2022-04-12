/**
 * 
 */
 

 
app.controller('Cart', function($scope, $location, $http) {
		$scope.checkoutButtonClick = function () {
			$location.path('/login');
		}
		
		$scope.var = 10;

	});