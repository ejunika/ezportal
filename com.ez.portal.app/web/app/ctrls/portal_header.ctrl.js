/**
 * 
 */
(function (ctx, fn) {
    'use strict';
    fn(ctx);
})(this, function (ctx) {
    'use strict';
    angular
        .module('portal_app')
        .controller('portal_header.ctrl', [
            '$scope',
            '$state',
            '$cookies',
            '$portalHttpService',
            function ($scope, $state, $cookies, $portalHttpService) {
                $scope.init = function () {
                    initCustomerInfo();
                    $scope.authenticationToken = $portalHttpService.authenticationToken;
                    $scope.loggedInUser = $portalHttpService.loggedInUser;
                };
                
                $scope.$on('set_logged_in_user.portal', function (e, loggedInUser) {
                    $scope.loggedInUser = loggedInUser;
                });
                
                $scope.logout = function (e) {
                    $portalHttpService
                        .get($portalHttpService.Url.LOGOUT 
                            + $cookies.get('a_token'))
                        .then(function (response) {
                            if (response && response.data.status) {
                                $cookies.remove('a_token');
                                $state.go('login');
                            }
                        })
                };
                
                function initCustomerInfo() {
                    $scope.portalCustomer = {
                        name: 'RKDF CE'
                    };
                }
            }
        ]);
});