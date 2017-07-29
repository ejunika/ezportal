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