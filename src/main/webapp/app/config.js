var pfm = angular.module("pfm", ["ngRoute", 'bw.paging', "Alertify", "angucomplete-alt", "ngMessages", "checklist-model"]);

pfm.controller ("NavController",function(){
    
});

pfm.config(function ($routeProvider) {
    "use strict";
    $routeProvider
        .when("/", {
        	templateUrl: "app/components/login/login.html",
            controller: "LoginController",
            controllerAs: "vm"
        }) 
        /*
         * Clientes 
         */
        .when("/clients", {
            templateUrl: "app/components/clients/list_clients.html",
            controller: "ListClientsController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthClients
              }
        })
        .when("/clients/create", {
            templateUrl: "app/components/clients/create_client.html",
            controller: "CreateClientController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthClients
              }
        })
        .when("/clients/modify/:idClient", {
            templateUrl: "app/components/clients/edit_client.html",
            controller: "EditClientController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthClients
              }
        })
        /*
         * Reservas
         */
        .when("/bookings", {
            templateUrl: "app/components/bookings/list_bookings.html",
            controller: "ListBookingsController",
            controllerAs: "vm"
        })
        .when("/bookings/create", {
            templateUrl: "app/components/bookings/create_booking.html",
            controller: "CreateBookingController",
            controllerAs: "vm"
        })
        .when("/bookings/modify/:idBooking", {
            templateUrl: "app/components/bookings/edit_booking.html",
            controller: "EditBookingController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthClients
              }
        })
        /*
         * Login
         */
        .when("/login", {
            templateUrl: "app/components/login/login.html",
            controller: "LoginController",
            controllerAs: "vm"
        })
        .when("/register", {
            templateUrl: "app/components/login/registration.html",
            controller: "RegistrationController",
            controllerAs: "vm",
            resolve: { //Solo el admin puede registrar usuarios
                notAutorized: checkAuthRegister
              }
        })
        /*
         * Planning
         */
        .when("/planning", {
            templateUrl: "app/components/planning/planning.html",
        })
        .otherwise({ /*aqui iría planning*/
            redirectTo: 'app/components/planning/planning.html'
        });
});


function checkAuthClients($window, $location, Alertify){
    var role = $window.sessionStorage.getItem('rol');
    if (!role || (role !== "ADMIN" && role !== "MANAGER")) {
      $location.url('/login');
      Alertify.error('No tienes acceso a esta función');
    }
 }

function checkAuthRegister($window, $location, Alertify){
    var role = $window.sessionStorage.getItem('rol');
    if (!role || role !== "ADMIN") {
      $location.url('/login');
      Alertify.error('Sólo el administrador tiene permiso para añadir usuarios. Si quiere añadir un usuario, contacte con el administrador.');
    }
  }

pfm.config(['$httpProvider',
  function ($httpProvider) {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
  }
]);