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
.directive('draggable', function($document, mapService) {
  return function(scope, element, attr) {
	var width = 22, height=40;
	element.css({
		position : 'fixed',
		display : 'none',
		width : width+'px',
		height : height+'px'
	});
	element.addClass('grabbable');
	element.addClass('unselectable');

	mapService.getMap().dragMarkerStart = function(x, y) {
		x -= width/2;
		y -= height;
		element.css({
			display : 'block',
			top : y + 'px',
			left : x + 'px'
		});
	};
	mapService.getMap().dragMarkerEnd = function() {
		element.css({
			display : 'none'
		});
	};
  };
})
.factory('mapService', function(uiGmapIsReady){
	var service = {};
	function convertPoint(map,latLng) {
		var topRight = map.getProjection().fromLatLngToPoint(map.getBounds().getNorthEast());
		var bottomLeft = map.getProjection().fromLatLngToPoint(map.getBounds().getSouthWest());
		var scale = Math.pow(2, map.getZoom());
		var worldPoint = map.getProjection().fromLatLngToPoint(latLng);
		return new google.maps.Point((worldPoint.x - bottomLeft.x) * scale,	(worldPoint.y - topRight.y) * scale);
	} 

	service.getMap = function(){
		if(!service.map){
			service.map = {
				center : {
					latitude : 20.515360,  
					longitude : -157.465245
				},
				markers : [],
				events : {
					mouseup : function(map, eventName, originalEventArgs) {
						if(service.map.currentMarker){
							var marker = service.map.currentMarker;
							var e = originalEventArgs[0];
							var lat = e.latLng.lat(), lon = e.latLng
									.lng();
							marker.coords = {
									latitude : lat,
									longitude : lon
							};
							service.map.markers.push(marker);
							service.map.currentMarker = null;
							service.map.setMarkerControlHighlight(false);
							service.map.refreshMap(map);
							service.map.addPlaceDialog(marker, service.map.zoom);
						}
					},
					mousemove : function(map, eventName, originalEventArgs) {
						if(service.map.currentMarker){
							var e = originalEventArgs[0];
							var latLngInPx = e.pixel;
							service.map.dragMarkerStart(latLngInPx.x ,latLngInPx.y);
						}
					}
				},
				control : {},
				refreshMap: function(){
					uiGmapIsReady.promise(1).then(function(instances){
						instances.forEach(function(inst) {
							var map = inst.map;
							google.maps.event.trigger(map, 'resize');
							service.map.dragMarkerEnd();
						});
					});				
				},
				showMarker : function(){
					if(service.map.currentMarker){
						return true;
					} else {
						return false;
					}
				},
				zoom : 7
			};
		}
		return service.map;
	};
    return service;
})
.factory('mooleloService',	function($resource) {
	var service = {};
	// Client Side Calls
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
	service.updateLocation = function(moolelo, markerId, location, callback) {
		var places = moolelo.places;
		for (var i = 0; i < places.length; i++) {
			if (places[i].markerId == markerId) {
				places[i].location = location;
			}
		}
		if(callback){
			callback();		
		}
	};
	service.getIsland = function(latLng){
		var enums = service.getEnums();
		var islands = enums.islands;

		for (var i = 0; i < islands.length; i++) {
			var latLngBounds = new google.maps.LatLngBounds(islands[i].northeast, island[i].southeast);
			if(latLngBounds.contains(latLng)){
				return island[i];
			}
		}
		return null;
	};
	service.getEnums = function() {
		var MooleloEnums = $resource("/namoolelo/rest/moolelos/enums");
		if (service.mooleloEnums) {
			return service.mooleloEnums;
		} else {
			return service.getEnumsCall();
		}
	};
	
	// Rest Calls
	service.getEnumsCall = function() {
		var MooleloEnums = $resource("/namoolelo/rest/moolelos/enums");
		return MooleloEnums.get().$promise.then(function(data){
			service.mooleloEnums = data;
			return data;
        });
	};
	service.getEnums();
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
		moolelo, marker, zoom, mapService, mooleloService, placeService) {
	var self = this,selectedIsland;
	$scope.place = {
			markerId : marker.id,
			location : {
				latitude : marker.coords.latitude,
				longitude : marker.coords.longitude,
				zoom : zoom
			}
	};	
	$scope.islands = enums.islands;
	self.placeAdd = function(){
		mooleloService.addPlace(moolelo,$scope.place, function(){
			$mdDialog.cancel();
		});
	};
	self.cancel = function($event){
		$mdDialog.cancel();
	};
})
.controller('CreateMooleloCtrl',function($scope, $state, $mdDialog, 
		mapService, mooleloService, sessionService) {
	if(!$scope.moolelo){
		$scope.moolelo = {};
	}

	$scope.map = mapService.getMap();
	$scope.map.moolelo = $scope.moolelo;
	$scope.map.addPlaceDialog = function(marker, zoom){
		$mdDialog.show({
            locals:{moolelo: $scope.moolelo, marker : marker, zoom:zoom, enums:mooleloService.getEnums()},  
			controller : 'AddPlaceCtrl',
			controllerAs: "placeCtrl",
			templateUrl : 'place/place.tpl.html',
			parent : angular.element(document.body),
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
	$scope.showTempMarker = mapService.map.showMarker();
})
.controller('controlCtrl', function($scope, mapService, mooleloService) {
	$scope.map = mapService.getMap();
	$scope.controlText = 'Add a Place';
	$scope.danger = false;
	$scope.map.setMarkerControlHighlight = function(toggle) {
		$scope.danger = toggle;
	};
	
	$scope.controlMouseDown = function($event) {
		var markerId = $scope.map.markers.length+1;
		var marker = {
            id: markerId,
			options : {
				draggable:true
			},
			events:{
				dragend : function(marker2, eventName, args) {
					var location = {
								latitude : marker2.position.lat(),
								longitude : marker2.position.lng(),
								zoom : $scope.map.zoom
							};
					mooleloService.updateLocation($scope.map.moolelo,markerId,location, null);
				}				
			}
        };
        $scope.map.currentMarker = marker;
		$scope.danger = true;		
	};
})
;