pfm.service('ClientsService', ['$http', '$q', function ($http, $q) {
   "use strict";
   
   const urlBase="http://localhost:8080/pfm.0.0.1-SNAPSHOT/api";
   
   this.request = function(config) {
	      let deferred = $q.defer();
	      $http(config).then(function (response) {
	    	  deferred.resolve(response.data);
	      }, function (response){
	    	  let errorMsg;
	    	  if(response.data.error === undefined) {
	    		  errorMsg="" + response.data.description;
	    	  }else{
	    		 //errorMsg = " --- " + response.data.error + ":" + response.data.description;
	    		 errorMsg = response.data.description;
	    	  }
	    	  //deferred.reject( "Error (" + response.status + ":" + response.statusText + ")" + errorMsg );
	    	  deferred.reject(errorMsg);
	      });
	      
	      return deferred.promise;	   
   }
  
   this.initList = function (){
	   let config = {
			   method: 'GET',
			   url: urlBase+"/clients"			  
	   };
		  return this.request(config);
	  }
   
   this.createClient = function (client){
	   let config = {
			   method: 'POST',
			   url: urlBase+"/clients",
			   data:{
				   'phone': client.phone, 
				   'name': client.name, 
				   'dni': client.dni, 
				   'surname': client.surname,
				   'email': client.email
			   }
	   };
	  return this.request(config);
   }
   
   this.modifyClient = function (client) {
   	let config = {
			   method: 'PUT',
			   url: urlBase+"/clients",
			   data:{
				   'id': client.id, 
				   'name': client.name,
				   'surname': client.surname,
				   'dni': client.dni, 
				   'phone': client.phone,
				   'email': client.email
			   }
	   };
		  return this.request(config);
	}
   
   this.getClientById = function (clientId){
   	let config = {
    		   //headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
			   method: 'GET',
			   url: urlBase+"/clients/"+clientId
	   };
		  return this.request(config);
	}
   
   this.search = function (searchBy, searchData){
  	   let config = {
  			   method: 'POST',
  			   url: urlBase+"/clients/search",
  			   data:{
  				   'searchBy': searchBy,
  				   'searchData': searchData
  			   }
  	   };
  	  return this.request(config);
    }
   
   this.getBookingsByClient = function (client_id){
	   let config = {
  			   method: 'POST',
  			   url: urlBase+"/bookings/clients",
  			   data:{
  				   'id': client_id	   
  			   }
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
   
   this.createBookingForClient = function (booking, arrival, departure){
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

}]);