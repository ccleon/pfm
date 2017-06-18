pfm.service('BungalowsService', ['$http', '$q', function ($http, $q) {
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
   
   this.listBungalows = function (){
	   let config = {
			   method: 'GET',
			   url: urlBase+"/bungalows"
	   };
		  return this.request(config);
	  }
   
   this.listBungalowType = function (){
	   let config = {
			   method: 'GET',
			   url: urlBase+"/bungalowType"
	   };
		  return this.request(config);
	  }
   
   /*this.createBungalow = function (bungalow){
	   let config = {
			   method: 'POST',
			   url: urlBase+"/bungalows",
			   data:{
				   
			   }
	   };
	  return this.request(config);
   }*/
   
   this.modifyBungalowType = function (bungalowType) {
   	let config = {
			   method: 'PUT',
			   url: urlBase+"/bungalowType",
			   data:{
				   'id': bungalowType.id, 
				   'type': bungalowType.type,
				   'octToDecPrice': bungalowType.octToDecPrice,
				   'decToJanPrice': bungalowType.decToJanPrice,
				   'janToAprPrice': bungalowType.janToAprPrice,
				   'aprToJunPrice': bungalowType.aprToJunPrice,
				   'julToOctPrice': bungalowType.julToOctPrice
			   }
	   };
		  return this.request(config);
	}
   
   this.getBungalowTypeById = function (id){
   	let config = {
			   method: 'GET',
			   url: urlBase+"/bungalowType/"+id
	   };
		  return this.request(config);
   }
}]);