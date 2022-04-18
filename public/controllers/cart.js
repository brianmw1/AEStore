/**
 * 
 */
 

 
app.controller('Cart', function($scope, $location, $http) {
		
		$http.get('http://localhost:5000/cart').
			then(function(response) {
				$scope.cartItems = response.data;
				console.log($scope.cartItems);

			})
		
		
		
		
		$scope.checkoutButtonClick = function () {
			$location.path('/login');
		}
		
		$scope.var = 10;

	});