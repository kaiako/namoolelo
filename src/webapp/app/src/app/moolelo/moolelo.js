angular.module('ngBoilerplate.moolelo',[ 'ui.router', 'ngResource', 'base64','ngBoilerplate.account' ])
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
		url:'/accounts/myMoolelos/create',
		views: {
			'main':{
				templateUrl:'moolelo/create.tpl.html',
				controller: 'CreateMooleloCtrl'			
			}
		},
        data : { pageTitle : "Registration" }
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
.factory('mooleloService',	function($resource) {
	var service = {};
	service.create = function(accountId,moolelo, success, failure) {
		var Moolelo = $resource("/namoolelo/rest/accounts/:paramAccountId/moolelos");
		Moolelo.save({paramAccountId:accountId}, moolelo, success, failure);
	};
//	service.getMooleloById = function(mooleloId) {
//		var Moolelo = $resource("/namoolelo/rest/moolelos/:paramMooleloId");
//		return Moolelo.get({
//			paramMooleloId : mooleloId
//		}).$promise;
//	};
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
.controller('CreateMooleloCtrl',function($scope, $state, mooleloService, sessionService) {

	$scope.mooleloCreate = function() {
		var accountId = sessionService.getAccountId();
		var moolelo = $scope.moolelo;
		mooleloService.create(accountId,moolelo,function(returnedData) {
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