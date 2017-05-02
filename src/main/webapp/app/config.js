var pfm = angular.module("pfm", ["ngRoute", 'bw.paging', "Alertify", "angucomplete-alt", "ngMessages", "checklist-model"]);

pfm.config(function ($routeProvider) {
    "use strict";
    $routeProvider
        .when("/", {
            templateUrl: "app/components/home/home.html"
        })
        .when("/clients", {
            templateUrl: "app/components/clients/list_clients.html",
            controller: "ListClientsController",
            controllerAs: "vm"
        })
        .when("/clients/create", {
            templateUrl: "app/components/clients/create_client.html",
            controller: "CreateClientController",
            controllerAs: "vm"
        })
        .when("/clients/modify/:idClient", {
            templateUrl: "app/components/clients/edit_client.html",
            controller: "EditClientController",
            controllerAs: "vm"
        })
        .otherwise({
            redirectTo: '/'
        });
});

pfm.config(['$httpProvider',
  function ($httpProvider) {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
  }
]);
