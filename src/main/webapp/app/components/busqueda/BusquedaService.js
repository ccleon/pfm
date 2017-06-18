pfm.service('BusquedaService', ['$http', '$q', function ($http, $q) {
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
	    		  errorMsg = response.data.description;
	    	  }
	    	//deferred.reject("Error (" + response.status + ":" + response.statusText + ")" + errorMsg);
	    	  deferred.reject("ERROR: " + errorMsg);
	      });
	      return deferred.promise;	   
   }
   
   this.search = function (arrival, departure){
  	   let config = {
  			   method: 'POST',
  			   url: urlBase+"/bookings/search",
  			   data:{
  				   'arrival': arrival, 
  				   'departure': departure
  			   }
  	   };
  	  return this.request(config);
    }
   
   this.searchByBungalow = function (arrivalB, departureB){
  	   let config = {
  			   method: 'POST',
  			   url: urlBase+"/bungalows/search",
  			   data:{
  				   'arrival': arrivalB, 
  				   'departure': departureB
  			   }
  	   };
  	  return this.request(config);
    }

}]);