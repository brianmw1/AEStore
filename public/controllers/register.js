/**
 * 
 */
 

 
app.controller('Register', function($scope, $location, $http) {
	
		$scope.loginButtonClick = function () {
			$location.path('/login');
		}
		
		$scope.registerClick = function () {
			$location.path('/checkout');
		}
		
		$scope.var3 = 'Register';

	});