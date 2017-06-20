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
	    		  //errorMsg = " --- " + response.data.error + ":" + response.data.description;
	    		  errorMsg = response.data.description;
	    	  }
	    	//deferred.reject("Error (" + response.status + ":" + response.statusText + ")" + errorMsg);
	    	  deferred.reject("ERROR: " + errorMsg);
	      });
	      return deferred.promise;	   
   }
   
   this.sortBy = function (parameter){
  	   let config = {
  			   method: 'POST',
  			   url: urlBase+"/bookings/sort",
  			   data:{
  				   'parameter': parameter
  			   }
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
   
   this.createBooking = function (booking, arrival, departure){
	   let config = {
			   method: 'POST',
			   url: urlBase+"/bookings",
			   data:{
				   'idCliente': booking.idcliente,
				   'idBungalow': booking.idbungalow,
				   'arrival': arrival, 
				   'departure': departure
			   }
	   };
	  return this.request(config);
   }
   
   this.modifyBooking = function (booking, arrival, departure) {
   	let config = {
			   method: 'PUT',
			   url: urlBase+"/bookings",
			   data:{
				   'id': booking.id, 
				   'idClient': booking.idclient,
				   'idBungalow': booking.idbungalow,
				   'arrival': arrival, 
				   'departure': departure 
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
   
   this.deleteBooking = function (booking_id){
  	   let config = {
  			   method: 'DELETE',
  			   url: urlBase+"/bookings/"+booking_id
  	   };
  	  return this.request(config);
    }
      
   this.checkDates = function (arrival, departure){
  	   let config = {
  			   method: 'POST',
  			   url: urlBase+"/bungalows/search",
  			   data:{
  				   'arrival': arrival, 
				   'departure': departure 
  			   }
  	   };
  	  return this.request(config);
    }
   
   this.checkDatesModify = function (id, arrival, departure){
  	   let config = {
  			   method: 'POST',
  			   url: urlBase+"/bungalows/search/modify",
  			   data:{
  				   'arrival': arrival, 
				   'idBooking': id,
				   'departure': departure
  			   }
  	   };
  	  return this.request(config);
    }
   
   this.searchBookings = function (arrival, departure, bungalow){
  	   let config = {
  			   method: 'POST',
  			   url: urlBase+"/bookings/search/bookings",
  			   data:{
  				   'arrival': arrival, 
  				   'departure': departure,
  				   'bungalow': bungalow
  			   }
  	   };
  	  return this.request(config);
    }

}]);