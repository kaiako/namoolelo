angular.module('ngBoilerplate.place',[ 'ui.router', 'ngResource', 'base64','ngBoilerplate.moolelo' ])
		.config(function($stateProvider) {
	$stateProvider.state('createPlace', {
        url:'/places/create',
        views: {
            'main': {
                templateUrl:'place/create-place.tpl.html'
            }
        },
        resolve: {
            moolelo: function(mooleloService, $stateParams) {
                return mooleloService.getMyMoolelos();
            }
        },
        data : { pageTitle : "My Mo'olelos" }
	});
})
.factory('placeService', function($resource) {
	var service = {};
    return service;
})
;