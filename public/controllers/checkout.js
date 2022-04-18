/**
 * 
 */
 

 
app.controller('Checkout', function($scope, $location, $http, $rootScope) {
	
		// functionality for confirmOrder button, confirms the order
		$scope.confirmOrder = function (order) {
			const user = {
				username: $rootScope.currentUser
			}
			const userOrder = Object.assign(user,order);
			console.log($rootScope.currentUser)
			console.log(userOrder);
			$http.post('http://aestore-env.eba-iyvbduef.us-east-1.elasticbeanstalk.com/cart/checkout', userOrder).
				then(function(response) {
					$scope.status = response.data.status;
				})
		}
		
		$scope.var3 = 'Checkout Page';

	});