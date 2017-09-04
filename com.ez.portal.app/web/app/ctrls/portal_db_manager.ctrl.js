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
        .controller('portal_db_manager.ctrl', [
            '$scope',
            '$state',
            '$portalHttpService',
            'portal_util.fact',
            'toastr',
            function ($scope, $state, $portalHttpService, portalUtilFactory, messageToaster) {
                
            	var TOASTER_OWNER = 'Database Manager';
            	
            	
            	/**
                 * 
                 * */
            	$scope.init = function () {
            		getAllUserSpaces(function (userSpaces) {
            			$scope.userSpaces = userSpaces;
            		});
                };
                
                $scope.reloadUserSpaces = function(e) {
                	getAllUserSpaces(function (userSpaces) {
            			$scope.userSpaces = userSpaces;
            		});
                };
                
                $scope.enableServerForm = function () {
                	$scope.isEnableServerForm = true;
                };
                
                $scope.saveServer = function (e, server) {
                	$scope.isEnableServerForm = false;
                };
                
                /**
                 * 
                 * */
                function getAllUserSpaces(cb) {
                	var users = [];
                	if (angular.isFunction(cb)) {
                		$portalHttpService
	                        .get($portalHttpService.Url.GET_USER_SPACES)
	                        .then(function (response) {
	                            if (response && response.data && response.data.status) {
	                            	arguments[0] = response.data.userSpaces;
	                            	cb.apply(this, arguments);
	                            	messageToaster.info('User Spaces refreshed', TOASTER_OWNER);
	                            }
	                        });
                	}
                }
                
                
            }
        ]);
});