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
;