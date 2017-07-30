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
            'portal_service.fact', 
            'portal_interceptor.srvc',
            function ($scope, $cookies, $state, portalServiceFactory, portalInterceptorService) {
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
                        portalServiceFactory
                            .post('http://localhost:8082/com.ez.portal/rest/login/do-login', {
                                emailId: login.emailId,
                                password: login.password,
                                userType: 1
                            })
                            .then(function (response) {
                                if (response.data.status) {
                                    
                                }
                            });
                    } else {
                        
                    }
                };
            }
        ]);
});