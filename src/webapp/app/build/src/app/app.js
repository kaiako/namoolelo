angular.module( 'ngBoilerplate', [
  'templates-app',
  'templates-common',
  'ngBoilerplate.home',
  'ngBoilerplate.about',
  'ngBoilerplate.account',
  'ngBoilerplate.moolelo',
  'ui.router',
  'hateoas'
])

.config( function myAppConfig ( $stateProvider, $urlRouterProvider, HateoasInterceptorProvider) {
  $urlRouterProvider.otherwise( '/home' );
  HateoasInterceptorProvider.transformAllResponses();
})

.run( function run () {
})

.controller( 'AppCtrl', function AppCtrl ( $scope, $location, sessionService ) {
  $scope.isLoggedIn = sessionService.isLoggedIn;
  $scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){
    if ( angular.isDefined( toState.data.pageTitle ) ) {
      $scope.pageTitle = toState.data.pageTitle + " | Na Mo'olelo" ;
    }
    
  });
})

;

