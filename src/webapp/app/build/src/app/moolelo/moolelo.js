angular.module('ngBoilerplate.moolelo',[ 'ui.router', 'ngResource', 'base64','ngBoilerplate.account' ])
		.config(function($stateProvider) {
	$stateProvider.state('myMoolelos', {
        url:'/myAccount/moolelos?accountId',
        views: {
            'main': {
                templateUrl:',moolelo/my-moolelo.tpl.html',
                controller: 'MyMoolelosCtrl'
            }
        },
        resolve: {
            account: function(accountService, $stateParams) {
                return accountService.getAccountById($stateParams.accountId);
            },
            moolelos: function(mooleloService, $stateParams) {
                return mooleloService.getMoolelosForAccount($stateParams.accountId);
            }
        },
        data : { pageTitle : "My Mo'olelos" }
	});
})
.factory('mooleloService',	function($resource) {
	var service = {};
//	service.create = function(moolelo, success, failure) {
//		var Moolelo = $resource("/namoolelo/rest/moolelos");
//		Moolelo.save({}, moolelo, success, failure);
//	};
//	service.getMooleloById = function(mooleloId) {
//		var Moolelo = $resource("/namoolelo/rest/moolelos/:paramMooleloId");
//		return Moolelo.get({
//			paramMooleloId : mooleloId
//		}).$promise;
//	};
//	service.getAllMoolelos = function() {
//		var Moolelo = $resource("/namoolelo/rest/moolelos");
//		return Moolelo.get().$promise.then(function(data) {
//			return data.moolelos;
//		});
//	};

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
//.controller('MooleloSearchCtrl', function($scope, moolelos) {
//	$scope.moolelos = moolelos;
//})
.controller('MyMoolelosCtrl', function($scope, moolelos) {
	$scope.moolelos = moolelos;
})
//.controller('MooleloCtrl',function($scope, sessionService, $state, accountService) {
//
//	$scope.mooleloCreate = function() {
//		mooleloService.create($scope.moolelo, function(
//				returnedData) {
//			$state.go("mooleloSearch");
//		}, function() {
//			alert("Error registering user");
//		});
//	};
//})
;