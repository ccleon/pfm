pfm.controller('BusquedaController', [ '$timeout', 'Alertify', 'BusquedaService', '$location',
	
	function($timeout, Alertify, BusquedaService, $location) {
		"use strict";
		var vm = this;
		vm.completed = false;
		vm.error = false;
		vm.search = search;
		vm.searchByBungalow = searchByBungalow;
		vm.arrival;
		vm.departure;
		vm.arrivalB;
		vm.departureB;
		

		function search(){
			BusquedaService.search(vm.arrival, vm.departure).then(function(result) {
				vm.completed = true;
				vm.data2 = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
		
		function searchByBungalow(){
			BusquedaService.searchByBungalow(vm.arrivalB, vm.departureB).then(function(result) {
				vm.completed = true;
				vm.resBungalow = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	}				
]);