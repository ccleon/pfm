pfm.service('BookingsService', ['$http', '$q', function ($http, $q) {
   "use strict";
   
   const urlBase="http://localhost:8080/pfm.0.0.1-SNAPSHOT/api";
   
   this.request = function(config) {
	      let deferred = $q.defer();
	      $http(config).then(function (response) {
	    	  deferred.resolve(response.data);
	      }, function (response){
	    	  let errorMsg;
	    	  if(response.data.error === undefined) {
	    		  errorMsg="";
	    	  }else{
	    		  errorMsg = " --- " + response.data.error + ":" + response.data.description;
	    	  }
	    	  deferred.reject( 
	    		 "Error (" + response.status + ":" + response.statusText + ")" + errorMsg + response.data);
	      });
	      return deferred.promise;	   
   }
  
   this.listBookings = function (){
	   let config = {
			   method: 'GET',
			   url: urlBase+"/bookings"
	   };
		  return this.request(config);
	  }
   
   this.getClients = function (){
	   let config = {
			   method: 'GET',
			   url: urlBase+"/clients"
	   };
		  return this.request(config);
	  }
   
   this.getBungalows = function (){
	   let config = {
			   method: 'GET',
			   url: urlBase+"/bungalows"
	   };
		  return this.request(config);
	  }
   
   this.createBooking = function (booking){
	   let config = {
			   method: 'POST',
			   url: urlBase+"/bookings",
			   data:{
				   'idCliente': booking.idcliente,
				   'idBungalow': booking.idbungalow,
				   'arrival': booking.arrival, 
				   'departure': booking.departure
			   }
	   };
	  return this.request(config);
   }
   
   this.modifyBooking = function (booking) {
   	let config = {
			   method: 'PUT',
			   url: urlBase+"/bookings",
			   data:{
				   'id': booking.id, 
				   'idBungalow': booking.idbungalow,
				   'idClient': booking.idclient,
				   'arrival': booking.arrival, 
				   'departure': booking.departure 
			   }
	   };
		  return this.request(config);
	}
   
   this.getBookingById = function (id){
   	let config = {
    		   //headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
			   method: 'GET',
			   url: urlBase+"/bookings/"+id
	   };
		  return this.request(config);
   }
	
   
   this.search = function (client_id){
  	   let config = {
  			   method: 'POST',
  			   url: urlBase+"/bookings/search",
  			   data:{'id': client_id}
  	   };
  	  return this.request(config);
    }
   
   
}]);