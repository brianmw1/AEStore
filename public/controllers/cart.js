/**
 * 
 */
 

 
app.controller('Cart', function($scope, $location, $http) {
		
		$scope.cart = [];
	

		$scope.loadCart = function() {
		// get the items in the cart
		$http.get('http://localhost:5000/cart').
			then(function(response) {
				$scope.cartItems = response.data;
				// iterate over the cart items and get the bid of each
				for (const item in $scope.cartItems) {
					$scope.items.filter(e => e.bid === item)[0]['cartQuantity'] = $scope.cartItems[item];
					$scope.cart.push($scope.items.filter(e => e.bid === item)[0]);
				}

			});
		}
		
		// loads all the items from the database into items variable
		$http.get('http://localhost:5000/items').
			then(function(response) {
				$scope.items = response.data._embedded.itemList;
				$scope.loadCart();
			})
			
		// add items to the client's cart'
		$scope.addToCart = function(item) {
			$http.post('http://localhost:5000/cart/addItem/' + item.bid).
				then(function(response) {
					console.log(response.data);
				});
			}
		
		$scope.removeOne = function(item) {
			$http.post('http://localhost:5000/cart/removeItem/' + item.bid).
				then(function(response) {
					console.log(response.data);
				});
		}
		

		
		$scope.checkoutButtonClick = function () {
			$location.path('/login');
		}
		
		$scope.var = 10;

	});