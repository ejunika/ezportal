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
        .controller('admin_home.ctrl', [
            '$rootScope',
            '$scope',
            '$portalHttpService',
            function ($rootScope, $scope, $portalHttpService) {
                
            	/**
            	 * 
            	 * */
            	$scope.init = function () {
            		initAdminMenu();
            		getAllUsers(function (users) {
            			initUsers(users);
            		});
                };
                
                /**
            	 * Creates a new user in the current userSpace
            	 * 
            	 * @param {Object} e The event object
            	 * @param {Object} register The register object
            	 * @return undefined
            	 * */
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
                
                /**
            	 * Initialize menus for administrator
            	 * 
            	 * */
                function initAdminMenu() {
                    $portalHttpService
                        .getJson('app/data/portal_admin_menus.data.json')
                        .then(function (response) {
                            $scope.adminMenus = response.data;
                        });
                }
                
                function getAllUsers(cb) {
                	var users = [];
                	if (angular.isFunction(cb)) {
                		for (var i = 1; i < 100; i++) {
                			users.push({
                				userId: i,
                				emailId: 'akhtar.azaz@live.com',
                				username: 'ejudo',
                				userType: 'ADMIN'
                			});
                		}
                		arguments[0] = users;
                		cb.apply(this, arguments);
                	}
                }
                
                function initUsers(users) {
                	$scope.users = users;
                }
            }
        ]);
});