pfm.controller('ListClientsController', [ '$timeout', 'Alertify', 'ClientsService',
	
	function($timeout, Alertify, ClientsService) {
		"use strict";
		var vm = this;

		vm.searchBy;
		vm.searchData;
		vm.initList = initList;
		vm.search = search;
		vm.getBookingsByClient = getBookingsByClient;
		vm.bookingsByClient;
		vm.client_id;
		vm.client;


		function initList() {
			ClientsService.initList().then(function(result) {
				vm.completed = true;
				vm.data = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
			
		function search(){
			ClientsService.search(vm.searchBy, vm.searchData).then(function(result) {
				vm.completed = true;
				vm.data2 = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
		
		function getBookingsByClient(){
			ClientsService.getBookingsByClient(vm.client_id).then(function(result) {
				vm.completed = true;
				vm.bookingsByClient = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	}				
]);