pfm.controller('EditBookingController', [ '$timeout', 'Alertify', 'BookingsService', '$location','$routeParams',

	function($timeout, Alertify, BookingsService, $location, $routeParams) {
	"use strict";
	var vm = this;

	vm.id = $routeParams.idBooking;
	vm.getBookingById = getBookingById;
	vm.modifyBooking = modifyBooking;
	vm.getClients = getClients;
	vm.getBungalows = getBungalows;
			
	function getBookingById() {
		BookingsService.getBookingById(vm.id).then(function(result) {
			vm.completed = true;
			vm.booking = result;
		}, function(errors) {
			Alertify.error(errors);
		});
	}
	
	function modifyBooking() {
		BookingsService.modifyBooking(vm.booking).then(function(result) {
			vm.completed = true;
			vm.booking = {};
	        Alertify.success("¡El usuario ha sido modificado con éxito!");
			$location.path('/bookings');
		}, function(errors) {
			Alertify.error(errors);
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