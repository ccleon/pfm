pfm.controller('ListBookingsController', [ '$timeout', 'Alertify', 'BookingsService',
	
	function($timeout, Alertify, BookingsService) {
		"use strict";
		var vm = this;
		vm.completed = false;
		vm.error = false;
			
		vm.booking_id;
		//vm.listBookings = listBookings;
		vm.search = search;
		vm.sortBy = sortBy;

		/*function listBookings() {
			BookingsService.listBookings().then(function(result) {
				vm.completed = true;
				vm.data = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}*/
			
		function search(){
			BookingsService.search(vm.client_id).then(function(result) {
				vm.completed = true;
				vm.data2 = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
		
		function sortBy(parameter){
			BookingsService.sortBy(parameter).then(function(result) {
				vm.completed = true;
				vm.data = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	}				
]);