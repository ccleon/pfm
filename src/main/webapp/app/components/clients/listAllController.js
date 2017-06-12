pfm.controller('ListClientsController', [ '$timeout', 'Alertify', 'ClientsService',
	
	function($timeout, Alertify, ClientsService) {
		"use strict";
		var vm = this;

		vm.searchBy;
		vm.searchData;
		vm.initList = initList;
		vm.search = search;
		vm.getBookingsByClient = getBookingsByClient;
		vm.getB;
		vm.clientB;

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
			ClientsService.getBookingsByClient().then(function(result) {
				vm.completed = true;
				vm.getB = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	}				
]);