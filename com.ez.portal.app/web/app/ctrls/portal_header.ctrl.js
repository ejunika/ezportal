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
        .controller('portal_header.ctrl', [
            '$scope',
            '$state',
            '$cookies',
            '$portalHttpService',
            function ($scope, $state, $cookies, $portalHttpService) {
                
            	/**
            	 * 
            	 * */
            	$scope.init = function () {
                	$scope.checkSession(function (loggedInUser, userSpace) {
                		$scope.loggedInUser = loggedInUser;
                		$portalHttpService.setLoggedInUser(loggedInUser);
                		initCustomerInfo(userSpace);
                    });
                };
                
                /**
            	 * 
            	 * */
                $scope.logout = function (e) {
                    $portalHttpService
                        .get($portalHttpService.Url.LOGOUT + $cookies.get('a_token'))
                        .then(function (response) {
                            if (response && response.data.status) {
                                $cookies.remove('a_token');
                                $state.go('login');
                            }
                        });
                };
                
                /**
            	 * 
            	 * */
                function initCustomerInfo(userSpace) {
                    $scope.portalCustomer = {
                        name: userSpace ? userSpace.userSpaceName : 'EZ Portal'
                    };
                }
            }
        ]);
});