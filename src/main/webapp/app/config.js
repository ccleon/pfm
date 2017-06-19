var pfm = angular.module("pfm", ["ngRoute", 'bw.paging', "Alertify", "angucomplete-alt", "ngMessages", "checklist-model"]);

pfm.controller ("NavController",function(){
    
});

pfm.config(function ($routeProvider) {
    "use strict";
    $routeProvider
        .when("/", {
        	templateUrl: "app/components/planning/planning.html",
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
            templateUrl: "app/components/clients/list_clients.html",
            controller: "ListClientsController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthAdminOrManager
              }
        })
        .when("/clients/create", {
            templateUrl: "app/components/clients/create_client.html",
            controller: "CreateClientController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthAdminOrManager
              }
        })
        .when("/clients/modify/:idClient", {
            templateUrl: "app/components/clients/edit_client.html",
            controller: "EditClientController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthAdminOrManager
              }
        })
        .when("/bookings/create/:idClient", {
            templateUrl: "app/components/clients/create_booking.html",
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
            templateUrl: "app/components/bookings/list_bookings.html",
            controller: "ListBookingsController",
            controllerAs: "vm",
            resolve: {
            	notAutorized: checkAuthAdminOrManager
            }
        })
        .when("/bookings/create", {
            templateUrl: "app/components/bookings/create_booking.html",
            controller: "CreateBookingController",
            controllerAs: "vm",
            resolve: {
            	notAutorized: checkAuthAdminOrManager
            }
        })
        .when("/bookings/modify/:idBooking", {
            templateUrl: "app/components/bookings/edit_booking.html",
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
            templateUrl: "app/components/login/login.html",
            controller: "LoginController",
            controllerAs: "vm"
        })
        /*
         * Register
         */
        .when("/register", {
            templateUrl: "app/components/login/registration.html",
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
            templateUrl: "app/components/planning/planning.html",
            controller: "PlanningController",
            controllerAs: "vm",
            resolve: {
            	notAutorized: checkLogged
            }
        })
        /*
         * Busqueda
         */
        .when("/busqueda", {
            templateUrl: "app/components/busqueda/busqueda.html",
            controller: "BusquedaController",
            controllerAs: "vm"
        })
        /*
         * Bungalows 
         */
        .when("/bungalows", {
            templateUrl: "app/components/bungalows/list_bungalows.html",
            controller: "ListBungalowsController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthAdmin
              }
        })
        .when("/bungalows/type/modify/:idBungalowType", {
            templateUrl: "app/components/bungalows/edit_bungalowType.html",
            controller: "EditBungalowTypeController",
            controllerAs: "vm",
            resolve: {
                notAutorized: checkAuthAdmin
              }
        })
        .otherwise({ 
            redirectTo: 'app/components/planning/planning.html'
        });
});

/*que esté logueado como manager o admin*/
function checkAuthAdminOrManager($window, $location, Alertify){
    var role = $window.sessionStorage.getItem('rol');
    if (!role || (role !== "ADMIN" && role !== "MANAGER")) {
      $location.url('/planning');
      Alertify.error('No tienes acceso a esta función');
    }
 }

/*que esté logueado como admin*/
function checkAuthAdmin($window, $location, Alertify){
    var role = $window.sessionStorage.getItem('rol');
    if (!role || role !== "ADMIN") {
      $location.url('/login');
      Alertify.error('Sólo el administrador tiene permiso para añadir usuarios. Si quiere añadir un usuario, contacte con el administrador.');
    }
  }

/*que esté logueado*/
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