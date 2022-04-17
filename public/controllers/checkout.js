/**
 * 
 */
 

 
app.controller('Checkout', function($scope, $location, $http) {
	
		$scope.loginButtonClick = function () {
			$location.path('/login');
		}
		
		$scope.var3 = 'Checkout Page';

	});