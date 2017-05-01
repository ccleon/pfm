pfm.service('ClientsService', ['$http', '$q', function ($http, $q) {
   "use strict";
   
   const urlBase="http://localhost:8080/GestionReservas.0.0.1-SNAPSHOT/api";
   
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
	    		 "Error (" + response.status + ":" + response.statusText + ")" + errorMsg );
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

}]);