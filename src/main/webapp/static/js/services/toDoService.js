const url = "/MySpringProject/todos";
angular.module('myApp').
	service('toDoService', function ($http, $q) {

		// Return public API.
		return ({
			add: add,
			update: update,
			get: get,
			remove: remove
		});

		function add(name) {
			return $http.post(url, { name: name }, { headers: { 'Content-Type': 'application/json' } });
		}

		function update(ids, data) {
			return $http.put(url + "/" + ids, data, { headers: { 'Content-Type': 'application/json' } });
		}

		function get() {
			return $http.get(url);
		}

		function remove(ids) {
			return $http.delete(url + "/" + ids);
		}

	});