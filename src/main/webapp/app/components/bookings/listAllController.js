pfm.controller('ListBookingsController', [ '$timeout', 'Alertify', 'BookingsService',
	
	function($timeout, Alertify, BookingsService) {
		"use strict";
		var vm = this;
		
		vm.booking_id;
		vm.search = search;
		vm.sortBy = sortBy;
		vm.deleteBooking = deleteBooking;
		
		function search(){
			BookingsService.search(vm.client_id).then(function(result) {
				vm.data2 = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
		
		function sortBy(parameter){
			BookingsService.sortBy(parameter).then(function(result) {
				vm.bookings = result;
				vm.currentDate = new Date();
			}, function(errors) {
				Alertify.error(errors);
			});
		}
		
		function deleteBooking(){
			BookingsService.deleteBooking(vm.booking_id).then(function(result) {
				Alertify.success("La reserva ha sido cancelada");
				sortBy('id');
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	}				
]);