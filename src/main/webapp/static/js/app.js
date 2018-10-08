angular.module('myApp', ['ngRoute']);
/*.config(function($routeProvider) {
	
	var routeConfig = {
		controller: 'todoCtrl',
	//	templateUrl: 'home/default',
		resolve: {
			todo: function (toDoService) {
				console.log('in config');
				toDoService.get().then(
						function (response) {
							console.log('in response: ',response.data.data);
							return response.data.data;
						},
						function (errorMessage) {
							console.warn(errorMessage);
						});
				
				
				return toDoService.get();
			}
		}
	};
    $routeProvider.when("/", routeConfig);
});*/