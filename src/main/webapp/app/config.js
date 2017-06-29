var pfm = angular.module("pfm", ["ngRoute", 'bw.paging', "Alertify", "angucomplete-alt", "ngMessages", "checklist-model"]);

pfm.controller ("NavController",function(){
    
});

pfm.config(function ($routeProvider) {
    "use strict";
    $routeProvider
        .when("/", {
        	templateUrl: "app/views/planning.html",
            controller: "PlanningController",
            controllerAs: "vm",
            resolve: {
            	notAutorized: checkLogged
            }
        }) 
        /*
         * Clientes 
         */
        .when("/clients", {
            templateUrl: "app/views/list_clients.html",
            controller: "ListClientsController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthAdminOrManager
              }
        })
        .when("/clients/create", {
            templateUrl: "app/views/create_client.html",
            controller: "CreateClientController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthAdminOrManager
              }
        })
        .when("/clients/modify/:idClient", {
            templateUrl: "app/views/edit_client.html",
            controller: "EditClientController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthAdminOrManager
              }
        })
        .when("/bookings/create/:idClient", {
            templateUrl: "app/views/create_bookingByClient.html",
            controller: "CreateBookingClientController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthAdminOrManager
              }
        })
        /*
         * Reservas
         */
        .when("/bookings", {
            templateUrl: "app/views/list_bookings.html",
            controller: "ListBookingsController",
            controllerAs: "vm",
            resolve: {
            	notAutorized: checkAuthAdminOrManager
            }
        })
        .when("/bookings/create", {
            templateUrl: "app/views/create_booking.html",
            controller: "CreateBookingController",
            controllerAs: "vm",
            resolve: {
            	notAutorized: checkAuthAdminOrManager
            }
        })
        .when("/bookings/modify/:idBooking", {
            templateUrl: "app/views/edit_booking.html",
            controller: "EditBookingController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthAdminOrManager
              }
        })
        /*
         * Login
         */
        .when("/login", {
            templateUrl: "app/views/login.html",
            controller: "LoginController",
            controllerAs: "vm"
        })
        /*
         * Register
         */
        .when("/register", {
            templateUrl: "app/views/registration.html",
            controller: "RegistrationController",
            controllerAs: "vm",
            resolve: {
                notAutorized: checkAuthAdmin
              }
        })
        /*
         * Planning
         */
        .when("/planning", {
            templateUrl: "app/views/planning.html",
            controller: "PlanningController",
            controllerAs: "vm",
            resolve: {
            	notAutorized: checkLogged
            }
        })
        /*
         * Bungalows 
         */
        .when("/bungalows", {
            templateUrl: "app/views/list_bungalows.html",
            controller: "ListBungalowsController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthAdmin
              }
        })
        .when("/bungalows/type/modify/:idBungalowType", {
            templateUrl: "app/views/edit_bungalowType.html",
            controller: "EditBungalowTypeController",
            controllerAs: "vm",
            resolve: {
                notAutorized: checkAuthAdmin
              }
        })
        .otherwise({ 
            redirectTo: 'app/views/planning.html'
        });
});

/*logueado como manager o admin*/
function checkAuthAdminOrManager($window, $location, Alertify){
    var role = $window.sessionStorage.getItem('rol');
    if (!role || (role !== "ADMIN" && role !== "MANAGER")) {
      $location.url('/planning');
      Alertify.error('No tienes acceso a esta función');
    }
 }

/*logueado como admin*/
function checkAuthAdmin($window, $location, Alertify){
    var role = $window.sessionStorage.getItem('rol');
    if (!role || role !== "ADMIN") {
      $location.url('/planning');
      Alertify.error('Sólo el administrador tiene permiso para acceder a esta función.');
    }
  }

/*logueado*/
function checkLogged($window, $location, Alertify){
    var role = $window.sessionStorage.getItem('rol');
    if (!role) {
      $location.url('/login');
      Alertify.error('No estás logueado');
    }
  }

pfm.config(['$httpProvider',
  function ($httpProvider) {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
  }
]);