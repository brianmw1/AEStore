/**
 * 
 */
 

 
app.controller('Register', function($scope, $location, $http, $rootScope) {
	
		$scope.loginButtonClick = function () {
			$location.path('/login');
		}
		
		$scope.submitRegister = function(userInfo) {
			console.log(userInfo);
			$http.post('http://localhost:5000/authentication/register', userInfo).
				// defines a function if request has been successfull
				then(function(response) {
					$rootScope.currentUser = userInfo.username;
					$location.path('/checkout');
				}, function(response) {
					console.log("error state");
				});
		}
		
		$scope.var3 = 'Register';

	});