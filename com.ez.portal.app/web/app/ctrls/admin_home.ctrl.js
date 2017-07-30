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
        .controller('admin_home.ctrl', [
            '$rootScope',
            '$scope',
            'portal_service.fact',
            function ($rootScope, $scope, portalServiceFactory) {
                $scope.init = function () {
                    initAdminMenu();
                    $scope.checkSession(function (loggedInUser) {
                        
                    });
                };
                
                $scope.addUser = function (e, register) {
                    portalServiceFactory
                        .post('http://localhost:8082/com.ez.portal/rest/login/sign-up', {
                            emailId: register.emailId,
                            password: register.password,
                            userType: register.userType || 1,
                            shardKey: portalServiceFactory.loggedInUser.shardKey,
                            createdBy: portalServiceFactory.loggedInUser.userId
                        })
                        .then(function (response) {
                            debugger;
                        });
                };
                function initAdminMenu() {
                    portalServiceFactory
                        .getJson('app/data/portal_admin_menus.data.json')
                        .then(function (response) {
                            $scope.adminMenus = response.data;
                        });
                }
            }
        ]);
});