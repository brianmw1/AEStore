/**
 * 
 */
 
app.controller('Login', function($scope, $location, $http, $rootScope) {
	
		// leads user to the register page
		$scope.registerButtonClick = function () {
			$location.path('/register');
		}
		
		// function to log the user in and make http call
		$scope.submitLogin = function (info) {
			$http.post('http://aestore-env.eba-iyvbduef.us-east-1.elasticbeanstalk.com/authentication/login', info).
				// defines a function if request has been successfull
				then(function(response) {
					$rootScope.currentUser = info.username;
					console.log($rootScope.currentUser)
					$location.path('/checkout');
				}, function(response) {
					console.log("error state");
				});
			
		}
		
		$scope.var2 = 'LOGIN PAGE';

	});