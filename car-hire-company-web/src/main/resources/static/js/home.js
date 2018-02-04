angular.module('home', [ 'ngRoute', 'ui.bootstrap' ])
  .config(function($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
      templateUrl : 'home.html',
      controller : 'home',
      controllerAs: 'controller'
    }).when('/login', {
      templateUrl : 'login.html',
      controller : 'navigation',
      controllerAs: 'controller'
    }).when('/register', {
        templateUrl : 'register.html',
        controller : 'register',
        controllerAs: 'controller'
    }).when('/listBooks', {
        templateUrl : 'listBooks.html',
        controller : 'listBooked',
        controllerAs: 'controller'
    }).when('/listAvailableVehicles', {
        templateUrl : 'listAvailableVehicles.html',
        controller : 'listAvailableVehicles',
        controllerAs: 'controller'
    }).otherwise('/');

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

  })
.service('vehicleService', ['$http', function ($http) {
    this.book = function(book, bookUrl){
        return $http.post(bookUrl, book, {
            headers: {'Content-Type': 'application/json'}
        });       
    };    
    this.save = function(vehicle, saveUrl){
        return $http.post(saveUrl, vehicle, {
            headers: {'Content-Type': 'application/json'}
        });       
    }; 
    this.listAllBooked = function (){
    	return $http.get('/listAllBooked');
    };
    this.listAllAvailable = function (){
    	return $http.get('/listAllAvailable');
    };
    this.findVehicles = function ( val ){
	    return $http.get('/findVehicles', {
	    	params:{
	        	 text:val
	         }
	    }).then(function(response){
	      return response.data;
	    });
    };
}])
  .controller('home', ['$http', '$scope', 'vehicleService', function($http, $scope, vehicleService){
	  
	$scope.findVehicles = function( val ){
	    return vehicleService.findVehicles( val );
	};
	
    $scope.book = function(){
    	if ( !validateBook() ){
    		$scope.errorMessage = 'All fields are required!';
    		$scope.error = true;
    		return;
    	}    	
        var book = {'vehicle':{'plate':$scope.vehicleText},'customer':{'email':$scope.customerEmail}}
        
        var bookUrl = "/book";
        var result = vehicleService.book(book, bookUrl );
		result.success(function($optionResult){
			if (!!$optionResult.errorMessage){
				$scope.error = true;
				$scope.errorMessage = $optionResult.errorMessage;
			} else {
				$scope.success = true;
				$scope.message = 'Vechicle booked with success!';
			}
			resetBookForm();
        })
        .error(function($error){
			$scope.errorMessage = !!$error.errorMessage ? $error.errorMessage : "Error booking vehicle!";
			$scope.error = true;
        });
    };
    
   var resetBookForm = function(){
    	$scope.customerEmail = '';
    	$scope.vehicleText = '';
    };
    
    var validateBook = function(){
    	return ( !!$scope.customerEmail && !!$scope.vehicleText );
    }; 
    
}])
  .controller('navigation', function($rootScope, $http, $location) {

  var self = this
  
  var authenticate = function(credentials, callback) {

    var headers = credentials ? {authorization : "Basic "
        + btoa(credentials.username + ":" + credentials.password)
    } : {};

    $http.get('user', {headers : headers}).then(function(response) {
      if (response.data.name) {
        $rootScope.authenticated = true;
      } else {
        $rootScope.authenticated = false;
      }
      callback && callback();
    }, function() {
      $rootScope.authenticated = false;
      callback && callback();
    });

  }

  authenticate();
  self.credentials = {};
  self.login = function() {
      authenticate(self.credentials, function() {
        if ($rootScope.authenticated) {
          $location.path("/");
          self.error = false;
        } else {
          $location.path("/login");
          self.error = true;
        }
      });
  };
	self.logout = function() {
	  $http.post('logout', {}).finally(function() {
		$rootScope.authenticated = false;
		$location.path("/");
	  });
	}  
})
 .controller('register', ['$scope', 'vehicleService', function($scope, vehicleService){
  
    $scope.save = function(){
    	if ( !validateSave() ){
    		$scope.errorMessage = 'All fields are required!';
    		$scope.error = true;
    		return;
    	}
        var vehicle = {'plate':$scope.vehiclePlate,'type':$scope.vehicleType,'passengers':$scope.vehiclePassengers,'wheels':$scope.vehicleWheels};
        
        var saveUrl = "/save";
        var result = vehicleService.save(vehicle, saveUrl );
		result.success(function($optionResult){
			if (!!$optionResult.errorMessage){
				$scope.error = true;
				$scope.errorMessage = $optionResult.errorMessage;
			} else {
				$scope.success = true;
				$scope.message = 'Vechicle saved with success!';
			}
			resetSaveVehicleForm();
        })
        .error(function($error){
			$scope.errorMessage = !!$error.errorMessage ? $error.errorMessage : "Error saving vehicle!";
			$scope.error = true;
        });
    };
    
    var validateSave = function(){
    	return ( !!$scope.vehiclePlate && !!$scope.vehicleType && !!$scope.vehiclePassengers && !!$scope.vehicleWheels );
    };    
    
    var resetSaveVehicleForm = function(){
    	$scope.vehiclePlate = '';
    	$scope.vehicleType = '';
    	$scope.vehiclePassengers = '';
    	$scope.vehicleWheels = '';
    };
    
    
}])
 .controller('listBooked', ['$scope', 'vehicleService', function($scope, vehicleService){
	 
    $scope.bookeds = [];
  
    var listBooked = function(){
        var result = vehicleService.listAllBooked();
		result.success(function($optionResult){
			if (!!$optionResult.errorMessage){
				$scope.error = true;
				$scope.errorMessage = $optionResult.errorMessage;
			} else {
				$scope.bookeds = $optionResult.result;
			}
        })
        .error(function($error){
			$scope.errorMessage = !!$error.errorMessage ? $error.errorMessage : "Error listing booked vehicles";
			$scope.error = true;
        });
    };
    
    listBooked();
    
}]).controller('listAvailableVehicles', ['$scope', 'vehicleService', function($scope, vehicleService){
	 
    $scope.vehicles = [];
  
    var listAllAvailable = function(){
        var result = vehicleService.listAllAvailable();
		result.success(function($optionResult){
			if (!!$optionResult.errorMessage){
				$scope.error = true;
				$scope.errorMessage = $optionResult.errorMessage;
			} else {
				$scope.vehicles = $optionResult.result;
			}
        })
        .error(function($error){
			$scope.errorMessage = !!$error.errorMessage ? $error.errorMessage : "Error listing available vehicles";
			$scope.error = true;
        });
    };
    
    listAllAvailable();
    
}]);
  
  