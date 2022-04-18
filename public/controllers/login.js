/**
 * 
 */
 
app.controller('Login', function($scope, $location, $http) {
		$scope.registerButtonClick = function () {
			$location.path('/register');
		}
		
		
		$scope.submitLogin = function (info) {
			
			$http.post('http://localhost:5000/authentication/login', info).
				// defines a function if request has been successfull
				then(function(response) {
					$location.path('/checkout');
				}, function(response) {
					console.log("error state");
				});
			
		}
		
		$scope.var2 = 'LOGIN PAGE';

	});