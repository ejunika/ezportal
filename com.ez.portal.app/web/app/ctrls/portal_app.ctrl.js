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
            'portal_service.fact',
            function ($rootScope, $scope, $cookies, $state, $log, portalServiceFactory) {
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
                    portalServiceFactory
                        .get("http://localhost:8082/com.ez.portal/rest/login/get-user-by-authentication-token/" 
                                + $cookies.get('a_token'))
                        .then(function (response) {
                            if (response && response.data && response.data.status) {
                                portalServiceFactory.loggedInUser = response.data.users[0];
                                if (angular.isFunction(cb)) {
                                    cb.call(this, portalServiceFactory.loggedInUser);
                                    $rootScope.$broadcast('set_logged_in_user.portal', portalServiceFactory.loggedInUser);
                                }
                                $state.go('adminHome');
                            } else {
                                $state.go('login');
                            }
                        });
                }
            }
        ]);
});