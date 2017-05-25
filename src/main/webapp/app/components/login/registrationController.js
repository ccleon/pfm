pfm.controller('RegistrationController', ['$timeout', 'LoginService',
		
	function($timeout, LoginService) {
		"use strict";
		var vm = this;

		vm.completed = false;
		vm.error = false;
		vm.password;
		vm.role;
		vm.registration = registration;
		vm.respuesta = "";
		vm.username;

		function registration() {
			const delay = 5000;
			LoginService.registration(vm.username, vm.password, vm.role).then(function(result) {
				vm.completed = true;
				vm.response = "¡Hecho! El usuario se ha creado con éxito";
				$timeout(function() {
					vm.completed = false;
				}, delay)
			}, function(errors) {
				vm.error = true;
				vm.response = errors;
				$timeout(function() {
					vm.error = false;
				}, delay)
			});
		}
	} 
]);