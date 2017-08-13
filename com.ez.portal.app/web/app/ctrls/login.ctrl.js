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
            'portal_interceptor.srvc',
            function ($scope, $cookies, $state, $portalHttpService, portalInterceptorService) {
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
                
                $scope.initUserSpaces = function (e, emailId) {debugger;
                    if (emailId && emailId != tempEmailId) {
                        tempEmailId = emailId;
                        $portalHttpService
                            .post($portalHttpService.Url.GET_ALL_USER_SPACES, {
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
                        $portalHttpService
                            .post($portalHttpService.Url.DO_LOGIN, {
                                emailId: login.emailId,
                                password: login.password
                            })
                            .then(function (response) {
                                if (response.data.status) {
                                    var currentDate = new Date();
                                    var expireDate = new Date(currentDate);
                                    expireDate.setMinutes(currentDate.getMinutes() + 100);
                                    $portalHttpService.authenticationToken = response.data.authenticationToken;
                                    $cookies.put('a_token', $portalHttpService.authenticationToken, {
                                        expires: expireDate
                                    });
                                    $state.go('adminHome');
                                }
                            });
                    } else {
                        
                    }
                };
            }
        ]);
});