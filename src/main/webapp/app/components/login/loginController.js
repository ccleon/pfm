pfm.controller('LoginController', [ '$timeout', 'LoginService','Alertify', '$location',
		function($timeout, LoginService, Alertify, $location) {
			"use strict";
			var vm = this;

			vm.error = false;
			vm.respuesta = "";
			
			vm.login = login;
			vm.username;
			vm.password;
			vm.isLogged = isLogged;
			vm.logout = logout;
			vm.isLoggedAuth = isLoggedAuth;
			vm.isLoggedAdmin = isLoggedAdmin;

			function login() {
				const delay = 5000;
				LoginService.login(vm.username, vm.password).then(function(result) {
					vm.response = result.token + ":" + result.rol;
					sessionStorage.token = result.token;
					sessionStorage.rol = result.rol;
					$location.path('/planning');
					Alertify.success("Te has logueado con éxito");
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
					$timeout(function() {
						vm.error = false;
					}, delay)
				});
			}
						
			function isLogged(){
				LoginService.isLogged().then(function(result) {
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
				});
			}
			
			function isLoggedAuth(){
				LoginService.isLoggedAuth().then(function(result) {
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
				});
			}
			
			function isLoggedAdmin(){
				LoginService.isLoggedAdmin().then(function(result) {
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
				});
			}
			
			function logout(){
				LoginService.logout().then(function(result) {
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
				});
			}
		} ]);