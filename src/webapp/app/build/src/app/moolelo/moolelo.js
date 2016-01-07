angular.module('ngBoilerplate.moolelo',[ 'ui.router', 
                                         'ngResource', 
                                         'base64', 
                                         'ngMaterial',
                                         'uiGmapgoogle-maps',
                                         'nemLogging'])
		.config(function($stateProvider) {
	$stateProvider.state('myMoolelos', {
        url:'/accounts/myMoolelos',
        views: {
            'main': {
                templateUrl:'moolelo/moolelo-list.tpl.html',
                controller: 'MyMoolelosCtrl'
            }
        },
        resolve: {
            moolelos: function(mooleloService, $stateParams) {
                return mooleloService.getMyMoolelos();
            }
        },
        data : { pageTitle : "My Mo'olelos" }
	})
	.state('createMoolelo',{
		url:'/moolelo/create',
		views: {
			'main':{
				templateUrl:'moolelo/create.tpl.html',
				controller: 'CreateMooleloCtrl'			
			}
		},
        data : { pageTitle : "Create Mo'olelo" }
	})
	.state('moolelo',{
		url:'/moolelo',
		views: {
			'main':{
				templateUrl:'moolelo/create.tpl.html',
				controller: 'MooleloCtrl'			
			}
		},
        data : { pageTitle : "My Mo'olelo" }
	})
//	.state('createMoolelo.createPlace', {
//		url:'/createPlace',
//		templateUrl:'place/create-place.tpl.html',
//		controller: 'CreatePlaceCtrl'
//	})
	.state('allMoolelos',{
		url:'/moolelos/search',
		views: {
			'main':{
				templateUrl:'moolelo/moolelo-list.tpl.html',
				controller: 'MooleloSearchCtrl'			
			}
		},
        resolve: {
            moolelos: function(mooleloService, $stateParams) {
                return mooleloService.getAllMoolelos();
            }
        },
        data : { pageTitle : "Mo'olelo Search" }
	});
})
.factory('mooleloService',	function($resource) {
	var service = {};
	//Client Side Calls
	service.mooleloMap = {};
	service.createNewMoolelo = function(moolelo){
		var id =  Math.floor((Math.random() * 10000) + 1);
		while(id in service.mooleloMap){
			id =  Math.floor((Math.random() * 10000) + 1);
		}
		service.mooleloMap[id] = moolelo;
		return id;
	};
	service.getMoolelo = function(mooleloId){
		return service.mooleloMap[mooleloId];
	};
	service.addPlace = function(moolelo, place, callback){
		var places = moolelo.places;
		if(!places){
			places = [];
			moolelo.places = places;
		}
		places.push(place);
		callback();
		
	};
	service.getEnums = function() {
		var MooleloEnums = $resource("/namoolelo/rest/moolelos/enums");
		if (service.mooleloEnums) {
			return service.mooleloEnums;
		} else {
			return service.getEnumsCall();
		}
	};
	
	//Rest Calls
	service.getEnumsCall = function() {
		var MooleloEnums = $resource("/namoolelo/rest/moolelos/enums");
		return MooleloEnums.get().$promise.then(function(data){
			service.mooleloEnums = data;
			return data;
        });
	};
	service.create = function(accountId,moolelo, success, failure) {
		var Moolelo = $resource("/namoolelo/rest/accounts/:paramAccountId/moolelos");
		Moolelo.save({paramAccountId:accountId}, moolelo, success, failure);
	};
	service.createPlace = function(mooleloId,place, success, failure) {
		var Moolelo = $resource("/namoolelo/rest/accounts/:paramAccountId/moolelos");
		Moolelo.save({paramAccountId:accountId}, moolelo, success, failure);
	};
	service.getAllMoolelos = function() {
		var Moolelo = $resource("/namoolelo/rest/moolelos");
		return Moolelo.get().$promise.then(function(data) {
			return data.moolelos;
		});
	};
    service.getMyMoolelos = function() {
        var Moolelos = $resource("/namoolelo/rest/moolelos/myMoolelos");
        return Moolelos.get().$promise.then(function(data){
            return data.moolelos;
        });
    };

    service.getMoolelosForAccount = function(accountId) {
        var deferred = $q.defer();
        var Account = $resource("/namoolelo/rest/accounts/:paramAccountId");
        Account.get({paramAccountId:accountId}, function(account) {
            var Moolelo = account.resource('moolelos');
            Moolelo.get(null,
                function(data) {
                  deferred.resolve(data.moolelos);
                },
                function() {
                  deferred.reject();
                }
            );
        });
        return deferred.promise;
    };
    return service;
})
.controller('MooleloSearchCtrl', function($scope, moolelos) {
	$scope.moolelos = moolelos;
})
.controller('MyMoolelosCtrl', function($scope, moolelos) {
	$scope.moolelos = moolelos;
})
.controller('MooleloCtrl', function($scope, $state, mooleloService) {
	if(!$scope.moolelo){
		$state.go('myMoolelos');
	}
	
})
.controller('AddPlaceCtrl', function($scope, $state, $stateParams, $mdDialog, enums, 
		moolelo, mooleloService, placeService) {
	var self = this,selectedIsland;
	$scope.place = {
			location : {}
	};	
	$scope.map = {
			center : {
				latitude : 20.515360,  
				longitude : -157.465245
			},
			zoom : 7
		};
    $scope.coordsUpdates = 0;
    $scope.dynamicMoveCtr = 0;
    $scope.marker = {
      id: 0,
      coords : {
		latitude : 20.515360,
		longitude : -157.465245
      },
      options: { draggable: true },
      events: {
        dragend: function (marker, eventName, args) {
          var lat = marker.getPosition().lat();
          var lon = marker.getPosition().lng();
          $scope.place.location.latitude = lat;
          $scope.place.location.longitude = lon;    
          $scope.place.location.zoom = $scope.map.zoom;       
          $scope.marker.options = {
            draggable: true
          };
        }
      }
    };

    $scope.$watchCollection("marker.coords", function (newVal, oldVal) {
      if (_.isEqual(newVal, oldVal)){
        return;
      }
      $scope.coordsUpdates++;
    });
	$scope.islands = enums.islands;
	$scope.selectedIsland = function(){
		if($scope.place.island){
			if(selectedIsland != $scope.place.island){				
				$scope.mokus = enums.mokus[$scope.place.island];
				$scope.map.center.latitude = $scope.islands[$scope.place.island].location.latitude;
				$scope.map.center.longitude = $scope.islands[$scope.place.island].location.longitude;
				$scope.map.zoom = $scope.islands[$scope.place.island].location.zoom;
				$scope.marker.coords.latitude = $scope.islands[$scope.place.island].location.latitude;
				$scope.marker.coords.longitude = $scope.islands[$scope.place.island].location.longitude;
				selectedIsland = $scope.place.island;
			}
			return true;
		} else {
			return false;
		}
	};
	self.placeAdd = function(){
		mooleloService.addPlace(moolelo,$scope.place, function(){
			$mdDialog.cancel();
		});
	};
	self.cancel = function($event){
		$mdDialog.cancel();
	};
})
.controller('CreateMooleloCtrl',function($scope, $state, $mdDialog, mooleloService, sessionService) {
	if(!$scope.moolelo){
		$scope.moolelo = {};
	}

	$scope.map = {
			center : {
				latitude : 20.515360,  
				longitude : -157.465245
			},
			zoom : 7
		};
	$scope.addPlaceDialog = function($event){
		$mdDialog.show({
            locals:{moolelo: $scope.moolelo, enums:mooleloService.getEnums()},  
			controller : 'AddPlaceCtrl',
			controllerAs: "placeCtrl",
			templateUrl : 'place/place.tpl.html',
			parent : angular.element(document.body),
			targetEvent : $event,
			clickOutsideToClose : true
		});
	};
	$scope.mooleloCreate = function() {
		var accountId = sessionService.getAccountId();
		mooleloService.create(accountId,$scope.moolelo,function(returnedData) {
			$state.go("myMoolelos");
		}, function(data) {
			if(data.status == 409){
				alert("Error cannot create this Mo'olelo : "+moolelo.title+" already exist!");
			}else {
				alert("System Error! Mo'olelo not created!");
			}			
		});
	};
})
;