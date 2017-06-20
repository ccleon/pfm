pfm.controller('ListBookingsController', [ '$timeout', 'Alertify', 'BookingsService',
	
	function($timeout, Alertify, BookingsService) {
		"use strict";
		var vm = this;
		
		vm.booking_id;
		vm.search = search;
		vm.sortBy = sortBy;
		vm.deleteBooking = deleteBooking;
		vm.searchBookings = searchBookings;
		
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
				searchBookings(vm.arrival, vm.departure, vm.bungalow);
			}, function(errors) {
				Alertify.error(errors);
			});
		}
		
		function searchBookings(){
			BookingsService.searchBookings(vm.arrival, vm.departure, vm.bungalow).then(function(result) {
				vm.resultBookings = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	}				
]);