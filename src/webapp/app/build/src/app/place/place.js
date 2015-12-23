angular.module('ngBoilerplate.place',[ 'ui.router','ngBoilerplate.moolelo'])
		.config(function($stateProvider) {
	$stateProvider.state('createPlace', {
        url:'/places/create?mooleloId',
        views: {
            'main': {
                templateUrl:'place/create-place.tpl.html',
                controller: 'CreatePlaceCtrl'
            }
        },
        data : { pageTitle : "Create Place" }
	});
})
.factory('placeService', function($resource) {
	var service = {};
    return service;
})
.controller('CreatePlaceCtrl', function($scope, $state, $stateParams, mooleloService, placeService) {
	var mooleloId = $stateParams.mooleloId;
	if(mooleloId == null){
		$state.go("myMoolelos");
	}
	if($scope.place == null){
		$scope.place = {};
	}
	$scope.placeCreate = function(){
		mooleloService.addPlace(mooleloId,$scope.place);
	};
})
;