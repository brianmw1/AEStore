/**
 * 
 */
 
app.controller('Item', function($scope, $location, $http) {

		//get the list of items
		$http.get('http://aestore-env.eba-iyvbduef.us-east-1.elasticbeanstalk.com/items').
			then(function(response) {
				$scope.items = response.data._embedded.itemList;
				console.log(response.data);
				$scope.getItemTypes();
			});
			
		// button changes view to the cart view
		$scope.viewCart = function () {
			$location.path('/cart')
		}
		
		// array to maintain the item 'type' filter
		$scope.itemTypeFilter = [];
		
		$scope.change = function(type, active) {
			if (active) {
				$scope.itemTypeFilter.push(type)
				console.log("push");
				console.log($scope.itemTypeFilter);
			}
			else {
				$scope.itemTypeFilter.splice($scope.itemTypeFilter.indexOf(type), 1);
				console.log("removed");
				console.log($scope.itemTypeFilter);
			}
		}
		
		// add items to the client's cart'
		$scope.addToCart = function(item) {
			// no quantity entered in input box, add 1 by default
			if (item.desiredQuantity == null) {
				$http.post('http://aestore-env.eba-iyvbduef.us-east-1.elasticbeanstalk.com/cart/addItem/' + item.bid).
					then(function(response) {
					console.log(response.data);
				});
				
			}
			// add the quantity the user requested to the cart
			else {
				for (let i = 0; i < item.desiredQuantity; i++) {
					$http.post('http://aestore-env.eba-iyvbduef.us-east-1.elasticbeanstalk.com/cart/addItem/' + item.bid).
						then(function(response) {
						console.log(response.data);
					});
				}
			}
			console.log(item.desiredQuantity);
		//	for (let i = 0; i < item.) 
		}
		
		
		// array to maintain the item 'brand' filter
		$scope.itemBrandFilter = [];
		
		// array to hold item types
		$scope.itemTypes = [];
		
		// array to hold item brands
		$scope.itemBrands = [];
		
		// get the item types from the response data
		$scope.getItemTypes = function() {
			angular.forEach($scope.items, function(item) {
				if (!$scope.itemTypes.includes(item.type)) {
					$scope.itemTypes.push(item.type);
				}
				
				if (!$scope.itemBrands.includes(item.brand)) {
					$scope.itemBrands.push(item.brand);
				}
			});
		}
		

		
		// get brands from response data
		

		//filter deleted record
		$scope.deletedFilter = function(item) {
			return !item.deleted;
		};



;
	});