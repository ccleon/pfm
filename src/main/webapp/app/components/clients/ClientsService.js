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
   
   this.createClient = function (client){
	   let config = {
			   method: 'POST',
			   url: urlBase+"/clients",
			   data:{
				   'phone': client.phone, 
				   'name': client.name, 
				   'dni': client.dni, 
				   'surname': client.surname
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
				   'phone': client.phone 
			   }
	   };
		  return this.request(config);
	}
   
   this.getClientById = function (id){
   	let config = {
    		   //headers : { Authorization: 'Basic ' + Base64.encode(sessionStorage.token + ':')},
			   method: 'GET',
			   url: urlBase+"/clients/"+id
	   };
		  return this.request(config);
	}
   
   this.search = function (client_id){
  	   let config = {
  			   method: 'POST',
  			   url: urlBase+"/clients/search",
  			   data:{'id': client_id}
  	   };
  	  return this.request(config);
    }
   
   
}]);