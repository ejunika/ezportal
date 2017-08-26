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
            'portal_interceptor.srvc',
            '$portalHttpService',
            function ($rootScope, $scope, $cookies, $state, $log, portalInterceptorService, $portalHttpService) {
            	
            	/**
            	 * Initialize portal_app.ctrl
            	 * */
            	$scope.init = function () {
            		
            		/**
            		 * Flag to tell whether password in password field will be visible or hidden.
            		 * */
            		$scope.showPassword = false;
            		
            		$scope.breadcrumbStack = [
            			{
            				label: 'Home',
            				isActive: true
            			}
            		];
            		
            		
//            		var ws = new WebSocket('ws://192.168.0.101:8082/com.ez.portal/questions');
//
//            		 ws.onmessage = function(e) {
//            		     console.log('message', e.data);
//            		     alert(e.data);
//            		 };
//
//            		 ws.onclose = function() {
//            		     console.log('close');
//            		 };
            	};
            	
            	
            	/**
            	 * Toggle visibility of password in password field
            	 * */
                $scope.togglePasswordVisiblity = function () {
                    $scope.showPassword = !$scope.showPassword;
                };
                
                /**
                 * Launches the menu
                 * */
                $scope.launchMenu = function (e, menu) {
                    $log.info(menu);
                    angular.forEach($scope.breadcrumbStack, function (breadcrumbStackItem, index) {
                    	breadcrumbStackItem.isActive = false;
                    });
                    $scope.breadcrumbStack.push({
                    	label: menu.label,
                    	isActive: true
                    });
                    $scope.isMenuLaunched = true;
                };
                
                /**
                 * Launches the menu
                 * */
                $scope.backToMainMenu = function (e) {
                	$scope.breadcrumbStack.pop();
                	$scope.isMenuLaunched = false;
                };
                
                /**
                 * Checks the validity of the session
                 * */
                $scope.checkSession = function (cb) {
                    var authToken = $cookies.get('a_token');
                    if (authToken) {
                    	$portalHttpService
                    		.get($portalHttpService.Url.CHECK_PORTAL_SESSION + authToken)
                			.then(function (response) {
                				if (response && response.data && response.data.status) {
                					$portalHttpService.setLoggedInUser(response.data.user);
                					if (angular.isFunction(cb)) {
                						cb.call(this, response.data.user);
                					}
                					$state.go('adminHome');
                				} else {
                					$cookies.remove('a_token');
                					$state.go('login');
                				}
                			});
                    } else {
                    	$state.go('login');
                    }
                };
                
                $scope.$on('portal.handle_loader', function (e, flag) {
                    $scope.showLoader = flag; 
                });
            }
        ]);
});