/**
 * 
 */
angular.module('demo', [])
	.controller('Item', function($scope, $http) {

		//get the list of items
		$http.get('http://localhost:5000/items').
			then(function(response) {
				$scope.items = response.data._embedded.itemList;
				$scope.getItemTypes();
			});
		
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

		$scope.add = function() {
			var hasNewRecord = false;
			//check if another new record is still pending
			angular.forEach($scope.items, function(item) {
				if (item.id == null && !item.item)
					hasNewRecord = true;
			});
			if (!hasNewRecord)
				//add a new record
				$scope.items.push({ id: null });
		};

		$scope.delete = function(id) {
			angular.forEach($scope.items, function(item) {
				if (item.id == id) {
					//mark the record as deleted
					item.deleted = true;
				}
			});
		};

		$scope.save = function() {
			angular.forEach($scope.items, function(item) {
				if (item.deleted) {
					if (item.id != null) {
						//delete record
						$http.delete('http://localhost:5000/items/' + item.id).
							then(function(response) {
								var index = $scope.items.indexOf(item);
								$scope.items.splice(index, 1);
							});
					}
				} else if (item.id == null) {
					//create new record
					$http.post('http://localhost:5000/items', item).
						then(function(response) {
							item.id = response.data.id;
						});
				} else {
					//edit existing record
					$http.put('http://localhost:5000/items/' + item.id, item).
						then(function(response) { });
				}
			});
		};
	});