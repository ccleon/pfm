pfm.controller('LoginController', [ '$timeout', 'LoginService','Alertify', '$location',
		function($timeout, LoginService, Alertify, $location) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.error = false;
			vm.login = login;
			vm.username;
			vm.password;
			vm.respuesta = "";
			vm.isLogged = isLogged;
			vm.logout = logout;

			function login() {
				LoginService.login(vm.username, vm.password).then(function(result) {
					vm.completed = true;
					vm.response = result.token + ":" + result.rol;
					sessionStorage.token = result.token;
					sessionStorage.rol = result.rol;

					$location.path('/bookings');
					Alertify.success("Te has logueado con éxito");

				}, function(errors) {
					vm.error = true;
					vm.response = errors;
				});
			}
						
			function isLogged(){
				LoginService.isLogged().then(function(result) {
					
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
				});
			}
			
			function logout(){
				LoginService.logout().then(function(result) {
					$location.path('/login');
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
				});
			}
		} ]);