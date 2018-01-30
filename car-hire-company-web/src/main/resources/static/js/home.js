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
.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('file', file);
        return $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        });       
    }
}])
  .controller('home', ['$scope', 'fileUpload', function($scope, fileUpload){
  
	var error = false;
	var errorMessage = "";
	var logMessage = "";
    
    $scope.uploadFile = function(){
        var file = $scope.myFile;
		if ( console ){
			console.log('file is ' );
			console.dir(file);
		}
        var uploadUrl = "/fileUpload";
        var result = fileUpload.uploadFileToUrl(file, uploadUrl );
		result.success(function($uploadResult){
			$scope.error = false;			
			$scope.logMessage = $uploadResult.message;
        })
        .error(function($error){
			$scope.errorMessage = !!$error.message ? $error.message : "Error uploading file!";
			$scope.error = true;
        });
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
  
  