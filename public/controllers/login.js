/**
 * 
 */
 

 
app.controller('Login', function($scope, $location, $http) {
		$scope.registerButtonClick = function () {
			$location.path('/register');
		}
		
		$scope.loginClick = function () {
			$location.path('/checkout');
		}
		
		$scope.var2 = 'LOGIN PAGE';

	});