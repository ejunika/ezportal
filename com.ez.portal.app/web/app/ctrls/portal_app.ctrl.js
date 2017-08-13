/**
 * @author azaz.akhtar
 */
(function (ctx, fn) {
    'use strict';
    fn(ctx);
})(this, function (ctx) {
    'use strict';
    angular
        .module('portal_app')
        .controller('portal_app.ctrl', [
            '$rootScope',
            '$scope', 
            '$cookies',
            '$state',
            '$log', 
            'portal_interceptor.srvc',
            '$portalHttpService',
            function ($rootScope, $scope, $cookies, $state, $log, portalInterceptorService, $portalHttpService) {
                $scope.showPassword = false;
                $scope.togglePasswordVisiblity = function () {
                    $scope.showPassword = !$scope.showPassword;
                };
                $scope.$on('portal.handle_loader', function (e, flag) {
                    $scope.showLoader = flag; 
                });
                
                $scope.launchMenu = function (e, menu) {
                    $log.info(menu);
                };
                
                $scope.checkSession = function (cb) {
                    $portalHttpService
                        .get($portalHttpService.Url.GET_USER_BY_AUTH_TOKEN
                                + $cookies.get('a_token'))
                        .then(function (response) {
                            if (response && response.data && response.data.status) {
                                $portalHttpService.loggedInUser = response.data.users[0];
                                portalInterceptorService.loggedInUser = $portalHttpService.loggedInUser;
                                if (angular.isFunction(cb)) {
                                    cb.call(this, $portalHttpService.loggedInUser);
                                    $rootScope.$broadcast('set_logged_in_user.portal', $portalHttpService.loggedInUser);
                                }
                                $state.go('adminHome');
                            } else {
                                $cookies.remove('a_token');
                                portalInterceptorService.loggedInUser = null;
                                $state.go('login');
                            }
                        });
                }
            }
        ]);
});