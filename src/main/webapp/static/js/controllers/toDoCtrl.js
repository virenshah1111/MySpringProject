angular.module('myApp')
	.controller('todoCtrl', ['$scope', 'toDoService', function ($scope, toDoService) {
//		console.log('in controller: ',todo);
		$scope.newTodo = '';
		$scope.todoList = [];

		$scope.isAnyRecordSelected = function () {
			for (let index in $scope.todoList) {
				if ($scope.todoList[index].selected) {
					return true;
				}
			}
			return false;
		}

		$scope.add = function () {
			if ($scope.newTodo) {
				toDoService.add($scope.newTodo.trim())
					.then(
						function (response) {
							$scope.getAll();
						},
						function (errorMessage) {
							console.warn(errorMessage);
						});
				$scope.newTodo = "";
			}
		};

		$scope.getAll = function () {
			toDoService.get().then(function (response) {
				if (response.data) {
					$scope.todoList = response.data.data;
				}
			},
				function (errorMessage) {
					console.warn(errorMessage);
				});
		};

		$scope.remove = function (todo) {
			console.log("in remove: ", todo)
			console.log("list: ", $scope.todoList)

			var ids;

			if (todo) {
				ids = todo.id;
			} else {
				ids = getSelectedIds();
			}

			if (ids) {
				toDoService.remove(ids).then(
					function (response) {
						$scope.getAll();
					},
					function (errorMessage) {
						console.warn(errorMessage);
					});
			}
		};

		$scope.update = function () {
			let toDoObj = [];
			let idArray = [];
			for (let index in $scope.todoList) {
				if ($scope.todoList[index].selected) {
					let todo = $scope.todoList[index];
					todo.completed = true;
					toDoObj.push(todo);
					idArray.push(todo.id);
				}
			}
			if (toDoObj.length && idArray.length) {
				toDoService.update(idArray.join(), toDoObj).then(
					function (response) {
						$scope.getAll();
					},
					function (errorMessage) {
						console.warn(errorMessage);
					});
			}
		};

		$scope.logout = function () {
			window.location = "/MySpringProject/logout";
		}
		
		function getSelectedIds() {
			let idArray = [];
			for (let index in $scope.todoList) {
				if ($scope.todoList[index].selected) {
					idArray.push($scope.todoList[index].id);
				}
			}
			return idArray.join();
		}

		$scope.init = function () {
			$scope.getAll();
			console.log("in init");
		};

	}]);
	
	/*.resolve =  {
	'todo': ['toDoService', function (toDoService) {
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
	}]
}*/

