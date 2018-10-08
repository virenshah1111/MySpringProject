<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>TODO</title>
	<link rel="stylesheet" type="text/css" href="../static/css/bootstrap.min.css" />

</head>

<body ng-cloak ng-app="myApp">
	<div ng-controller="todoCtrl" ng-init="init()">
		<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
			<a class="navbar-brand" href="#">ToDo</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
			 aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarsExampleDefault">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link" href="#">Home<span class="sr-only">(current)</span></a></li>
				</ul>
				<div class="pull-right">
					<div class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown01" data-toggle="dropdown"
						 aria-haspopup="true" aria-expanded="false">Welcom, ${username}</a>
						<div class="dropdown-menu" aria-labelledby="dropdown01">
							<a class="dropdown-item" href="#" ng-click="logout()">Logout</a>
						</div>
					</div>
				</div>
			</div>
		</nav>

		<div class="container">
			<div class="py-5 mt-3">
				<h1>Todos</h1>
				<form ng-submit="add()">
					<input type="text" ng-model="newTodo" class="form-control" placeholder="Add todo">
				</form>
				<div class='row mt-3'>
					<div class='col-md-12'>
						<button type="button" ng-click="update()" ng-show="isAnyRecordSelected()" class="btn btn-success btn-sm">Mark Completed</button>
						<button type="button" ng-click="remove()" ng-show="isAnyRecordSelected()" class="btn btn-danger btn-sm">Delete</button>
					</div>
				</div>

				<hr>
				<ul class="list-group">
					<li ng-repeat="todo in todoList" class="list-group-item">
						<div class="form-check float-left">
							<input class="form-check-input" ng-model="todo.selected" type="checkbox" value="">
							<label class="form-check-label">
								<del ng-if="todo.completed">{{todo.name}}</del>
								<span ng-if="!todo.completed">{{todo.name}}</span>
							</label>
						</div>
						<button type="button" class="close" aria-label="Close" ng-click="remove(todo)">
							<span class="text-danger" aria-hidden="true">&times;</span>
						</button>
					</li>
				</ul>
			</div>
		</div>

	</div>
	<script type="text/javascript" src="../static/js/lib/angular.min.js"></script>
	<script type="text/javascript" src="../static/js/lib/angular-route.min.js"></script>
	<script type="text/javascript" src="../static/js/lib/jquery.min.js"></script>
	<script type="text/javascript" src="../static/js/lib/bootstrap.min.js"></script>

	<script type="text/javascript" src="../static/js/app.js"></script>
	<script type="text/javascript" src="../static/js/controllers/toDoCtrl.js"></script>
	<script type="text/javascript" src="../static/js/services/toDoService.js"></script>
</body>

</html>