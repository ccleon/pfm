pfm.controller('RegistrationController', ['$timeout', 'LoginService', 'Alertify',
		
	function($timeout, LoginService, Alertify) {
		"use strict";
		var vm = this;

		vm.password;
		vm.role;
		vm.registration = registration;
		vm.respuesta = "";
		vm.username;

		function registration() {
			LoginService.registration(vm.username, vm.password, vm.role).then(function(result) {
				Alertify.success("¡Hecho! El usuario se ha creado con éxito");
			}, function(errors) {
				vm.response = errors;
				Alertify.error("Error amijo");
			});
		}
	} 
]);