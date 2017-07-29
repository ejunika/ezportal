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
            'portal_service.fact', 
            'portal_interceptor.srvc',
            function ($scope, $cookies, $state, portalServiceFactory, portalInterceptorService) {
                var tempEmailId;
                $scope.init = function () {
                    $scope.userSpaces = [];
                    portalInterceptorService.selectedUserSpace = $scope.selectedUserSpace = {};
                    $scope.login = {
                        emailId: '',
                        password: ''
                    };
                    $scope.checkSession();
                };
                
                $scope.onChangeSpace = function (e, selectedUserSpace) {
                    portalInterceptorService.selectedUserSpace = selectedUserSpace;
                };
                
                $scope.initUserSpaces = function (e, emailId) {
                    if (emailId && emailId != tempEmailId) {
                        tempEmailId = emailId;
                        portalServiceFactory
                            .post('http://localhost:8082/com.ez.portal/rest/login/get-all-user-spaces', {
                                emailId: emailId
                            })
                            .then(function (response) {
                                if (response.data) {
                                    $scope.userSpaces = response.data.userSpaces;
                                    portalInterceptorService.selectedUserSpace = $scope.selectedUserSpace = $scope.userSpaces[0];
                                }
                            });
                    }
                };
                
                $scope.doLogin = function (e, login) {
                    if (login.password) {
                        portalServiceFactory
                            .post('http://localhost:8082/com.ez.portal/rest/login/do-login', {
                                emailId: login.emailId,
                                password: login.password
                            })
                            .then(function (response) {
                                if (response.data.status) {
                                    portalServiceFactory.authenticationToken = response.data.authenticationToken;
                                    $cookies.put('a_token', portalServiceFactory.authenticationToken);
                                    portalServiceFactory
                                        .get("http://localhost:8082/com.ez.portal/rest/login/get-user-by-authentication-token/" 
                                                + portalServiceFactory.authenticationToken)
                                        .then(function (response) {
                                            if (response && response.data.status) {
                                                portalServiceFactory.loggedInUser = response.data.users[0];
                                                $state.go('adminHome');
                                            }
                                        });
                                }
                            });
                    } else {
                        
                    }
                };
            }
        ]);
});