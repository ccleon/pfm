pfm.controller('EditBookingController', [ '$timeout', 'Alertify', 'BookingsService', '$location','$routeParams',

	function($timeout, Alertify, BookingsService, $location, $routeParams) {
	"use strict";
	var vm = this;

	vm.id = $routeParams.idBooking;
	vm.getBookingById = getBookingById;
	vm.modifyBooking = modifyBooking;
			
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
}				
]);