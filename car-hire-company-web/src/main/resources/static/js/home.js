angular.module('home', [ 'ngRoute' ])
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
        controller : 'navigation',
        controllerAs: 'controller'
    }).otherwise('/');

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

  })
  .directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}])
.service('vehicleService', ['$http', function ($http) {
    this.save = function(vehicle, saveUrl){
        return $http.post(saveUrl, vehicle, {
            transformRequest: angular.identity,
            headers: {'Content-Type': 'application/json'}
        });       
    };
    this.book = function(book, bookUrl){
        return $http.post(bookUrl, book, {
            transformRequest: angular.identity,
            headers: {'Content-Type': 'application/json'}
        });       
    };    
}])
  .controller('home', ['$scope', 'vehicleService', function($scope, vehicleService){
  
	var error = false;
	var errorMessage = "";
	var logMessage = "";
    
    $scope.save = function(){
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
			resetVehicleForm();
        })
        .error(function($error){
			$scope.errorMessage = !!$error.message ? $error.message : "Error saving vehicle!";
			$scope.error = true;
        });
    };
    
    $scope.book = function(){
        var book = {'vehicle':{'plate':$scope.vehiclePlate},'email':$scope.email}
        
        var saveUrl = "/book";
        var result = vehicleService.book(book, saveUrl );
		result.success(function($optionResult){
			$scope.error = $optionResult.error;			
			if ( $scope.error ){
				$scope.errorMessage = $optionResult.message;
			}
        })
        .error(function($error){
			$scope.errorMessage = !!$error.message ? $error.message : "Error saving vehicle!";
			$scope.error = true;
        });
    };
    
    $scope.resetVehicleForm = function(){
    	$scope.vehiclePlate = '';
    	$scope.vehicleType = '';
    	$scope.vehiclePassengers = '';
    	$scope.vehicleWheels = '';
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
});
  
  