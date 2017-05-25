pfm.service('PlanningService', ['$http', '$q', function ($http, $q) {
   "use strict";
   
   const urlBase="http://192.168.0.160:8080/pfm.0.0.1-SNAPSHOT/api";
   
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
  
   this.showPlanning = function (month, year){
	   let config = {
			   method: 'POST',
			   url: urlBase+"/planning",
			   data:{
				   'month': month,
				   'year': year
			   }
	   };
		  return this.request(config);
	  } 
   
   this.getBungalows= function (){
	   let config = {
			   method: 'GET',
			   url: urlBase+"/bungalows"
	   };
		  return this.request(config);
	  } 
}]);