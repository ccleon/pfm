var pfm = angular.module("pfm", ["ngRoute",
                                 'bw.paging',
                                 "Alertify",
                                 "angucomplete-alt",
                                 "ngMessages",
                                 "checklist-model"]);

pfm.config(function ($routeProvider) {
    "use strict";
    $routeProvider
        .when("/", {
            templateUrl: "app/components/home/home.html"
        })
        .when("/feature00/version", {
            templateUrl: "app/components/feature00/version.html",
            controller: "VersionController",
            controllerAs: "vm"
        })
        .when("/feature00/login", {
            templateUrl: "app/components/feature00/login.html",
            controller: "LoginController",
            controllerAs: "vm"
        })
        .when("/feature00/registration", {
            templateUrl: "app/components/feature00/registration.html",
            controller: "RegistrationController",
            controllerAs: "vm"
        })
        .when("/feature00/delete-all", {
            templateUrl: "app/components/feature00/deleteAll.html",
            controller: "DeleteAllController",
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
