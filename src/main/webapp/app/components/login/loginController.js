pfm.controller('LoginController', [ '$timeout', 'LoginService',
		function($timeout, LoginService) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.error = false;
			vm.login = login;
			vm.username;
			vm.password;
			vm.respuesta = "";

			function login() {
				LoginService.login(vm.username, vm.password).then(function(result) {
					vm.completed = true;
					vm.response = result.token + ":" + result.rol;
					sessionStorage.token = result.token;
					sessionStorage.rol = result.rol;
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
				});
			}
		} ]);