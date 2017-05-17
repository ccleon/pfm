pfm.controller('CreateBookingController', [ '$timeout', 'Alertify', 'BookingsService', '$location',
	
	function($timeout, Alertify, BookingsService, $location) {
		"use strict";
		var vm = this;
		vm.completed = false;
		vm.error = false;
		vm.createBooking = createBooking;
		vm.getClients = getClients;
		vm.getBungalows = getBungalows;

		function createBooking() {
			BookingsService.createBooking(vm.booking).then(function(result) {
				Alertify.success("¡La reserva ha sido creada con éxito!");
				$location.path('/bookings');
			}, function error(errors){
		    	Alertify.error("¡ERROR! La reserva no se ha podido crear"+ " -- " +errors);
		    });
		}
		
		function getClients() {
			BookingsService.getClients().then(function(result) {
				vm.completed = true;
				vm.clients = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
		
		function getBungalows() {
			BookingsService.getBungalows().then(function(result) {
				vm.completed = true;
				vm.bungalows = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	}				
]);