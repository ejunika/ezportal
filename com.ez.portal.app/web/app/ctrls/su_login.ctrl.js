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
        .controller('su_login.ctrl', [
            '$scope',
            '$cookies',
            '$state',
            '$portalHttpService',
            'portal_interceptor.srvc',
            function ($scope, $cookies, $state, $portalHttpService, portalInterceptorService) {
                var tempEmailId;
                $scope.init = function () {
                    $scope.login = {
                        emailId: '',
                        password: ''
                    };
//                    $scope.checkSession();
                };
                
                $scope.doLogin = function (e, login) {
                    if (login.password) {
                        $portalHttpService
                            .post($portalHttpService.Url.DO_LOGIN, {
                                emailId: login.emailId,
                                password: login.password,
                                userType: 1
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