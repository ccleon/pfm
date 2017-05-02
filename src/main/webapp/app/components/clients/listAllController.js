pfm.controller('ListClientsController', [ '$timeout', 'Alertify', 'ClientsService',
	
	function($timeout, Alertify, ClientsService) {
		"use strict";
		var vm = this;
		vm.completed = false;
		vm.error = false;
			
		vm.client_id;
		vm.initList = initList;
		vm.search = search;

		function initList() {
			ClientsService.initList().then(function(result) {
				vm.completed = true;
				vm.data = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
			
		function search(){
			ClientsService.search(vm.client_id).then(function(result) {
				vm.completed = true;
				vm.data2 = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	}				
]);