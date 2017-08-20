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
        .controller('login.ctrl', [
            '$scope',
            '$cookies',
            '$state',
            '$portalHttpService',
            function ($scope, $cookies, $state, $portalHttpService) {
                
            	/**
                 * A temporary variable to hold the emailId for last emailId used for querying
                 * the userSpaces for specified emailId.
                 * */
            	var lastUsedEmailId;
                
            	/**
                 * Initialize the login.ctrl
                 * 
                 * @return undefined
                 * */
            	$scope.init = function () {
                    
            		/**
            		 * 
            		 * */
            		$scope.userSpaces = [];
            		
            		/**
            		 * 
            		 * */
                    $scope.selectedUserSpace = {};
                    
                    /**
                     * 
                     * */
                    $scope.login = {
                        emailId: '',
                        password: ''
                    };
                    
                    if ($portalHttpService.loggedInUser) {
                    	$scope.checkSession();
                    }
                };
                
                /**
                 * initialize userSpaces
                 * 
                 * @param {String} emailId the emailId
                 * @return undefined
                 * */
                $scope.initUserSpaces = function (e, emailId) {
                    if (emailId && emailId != lastUsedEmailId) {
                    	lastUsedEmailId = emailId;
                        $portalHttpService
                            .post($portalHttpService.Url.GET_ALL_USER_SPACES, {
                                emailId: emailId
                            })
                            .then(function (response) {
                                if (response.data && response.data.status) {
                                    $scope.userSpaces = response.data.userSpaces;
                                    if ($scope.userSpaces.length > 0) {
                                    	$scope.selectedUserSpace = $scope.userSpaces[0];
                                    }
                                }
                            });
                    }
                };
                
                /**
                 * Performs login operation
                 * 
                 * @param {Object} e the event
                 * @param {Object} login the login object
                 * @return undefined
                 * */
                $scope.doLogin = function (e, login) {
                    if (login.password) {
                        $portalHttpService
                            .post($portalHttpService.Url.DO_LOGIN, {
                                emailId: login.emailId,
                                password: login.password,
                                shardKey: $scope.selectedUserSpace.userSpaceId
                            })
                            .then(function (response) {
                                if (response.data.status) {
                                	if (response.data.authenticationToken) {
                                		$portalHttpService.setAuthToken(response.data.authenticationToken);
                                		$portalHttpService.setLoggedInUser(response.data.user);
                                	}
                                    $state.go('adminHome');
                                }
                            });
                    }
                };
            }
        ]);
});