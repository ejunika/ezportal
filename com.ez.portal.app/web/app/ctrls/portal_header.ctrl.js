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
            'portal_service.fact',
            function ($scope, $state, $cookies, portalServiceFactory) {
                $scope.init = function () {
                    initCustomerInfo();
                    $scope.authenticationToken = portalServiceFactory.authenticationToken;
                    $scope.loggedInUser = portalServiceFactory.loggedInUser;
                };
                
                $scope.$on('set_logged_in_user.portal', function (e, loggedInUser) {
                    $scope.loggedInUser = loggedInUser;
                });
                
                $scope.logout = function (e) {
                    portalServiceFactory
                        .get("http://localhost:8082/com.ez.portal/rest/login/logout/" 
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