angular.module('ngBoilerplate.moolelo',[ 'ui.router', 'ngResource', 'base64' ])
		.config(function($stateProvider) {
	$stateProvider.state('mooleloSearch', {
		url : '/moolelos',
		views : {
			'main' : {
				templateUrl : 'moolelo/search.tpl.html',
				controller : 'MooleloSearchCtrl'
			}
		},
		data : {
			pageTitle : "Search Mo'olelos"
		}
	}).state('mooleloCreate', {
		url : '/moolelo/create',
		views : {
			'main' : {
				templateUrl : "moolelo/create.tpl.html",
				controller : "MooleloCtrl"
			}
		},
		data : {
			pageTitle : "Create a Mo'olelo"
		}
	});
})
.factory('mooleloService',	function($resource) {
	var service = {};
	service.create = function(moolelo, success, failure) {
		var Moolelo = $resource("/namoolelo/rest/moolelos");
		Moolelo.save({}, moolelo, success, failure);
	};
	service.getMooleloById = function(mooleloId) {
		var Moolelo = $resource("/namoolelo/rest/moolelos/:paramMooleloId");
		return Moolelo.get({
			paramMooleloId : mooleloId
		}).$promise;
	};
	service.getAllMoolelos = function() {
		var Moolelo = $resource("/namoolelo/rest/moolelos");
		return Moolelo.get().$promise.then(function(data) {
			return data.moolelos;
		});
	};
})
.controller('MooleloSearchCtrl', function($scope, moolelos) {
	$scope.moolelos = moolelos;
})
.controller('MooleloCtrl',function($scope, sessionService, $state, accountService) {

	$scope.mooleloCreate = function() {
		mooleloService.create($scope.moolelo, function(
				returnedData) {
			$state.go("mooleloSearch");
		}, function() {
			alert("Error registering user");
		});
	};
});