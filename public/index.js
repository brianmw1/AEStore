/**
 * 
 */
// Define the main module for the application
var app = angular.module('AEStore', ['ngRoute']);

app.config(['$locationProvider',
	function($locationProvider) {
		$locationProvider.hashPrefix('');
	}
])

app.config(function($routeProvider) {
		$routeProvider
			.when('/', {
				templateUrl : '/views/main.html'
			})
			.when('/cart', {
				templateUrl : '/views/cart.html'
			})
			.when('/login', {
				templateUrl : '/views/login.html'
			})
			.when('/register', {
				templateUrl : '/views/register.html'
			})
			.when('/checkout', {
				templateUrl : '/views/checkout.html'
			})
	}	
)

