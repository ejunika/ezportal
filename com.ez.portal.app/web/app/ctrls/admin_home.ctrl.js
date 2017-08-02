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
            '$portalHttpService',
            function ($rootScope, $scope, $portalHttpService) {
                $scope.init = function () {
                    initAdminMenu();
                    $scope.checkSession(function (loggedInUser) {
                        
                    });
                };
                
                $scope.addUser = function (e, register) {
                    $portalHttpService
                        .post($portalHttpService.Url.SIGN_UP, {
                            emailId: register.emailId,
                            password: register.password,
                            userType: register.userType || 1,
                            shardKey: $portalHttpService.loggedInUser.shardKey,
                            createdBy: $portalHttpService.loggedInUser.userId
                        })
                        .then(function (response) {
                            debugger;
                        });
                };
                function initAdminMenu() {
                    $portalHttpService
                        .getJson('app/data/portal_admin_menus.data.json')
                        .then(function (response) {
                            $scope.adminMenus = response.data;
                        });
                }
            }
        ]);
});