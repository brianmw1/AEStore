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
			$http.get('http://localhost:5000/cart/checkout', userOrder).
				then(function(response) {
					console.log("Order Submitted");
				})
		}
		
		$scope.var3 = 'Checkout Page';

	});