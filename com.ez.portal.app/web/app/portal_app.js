/**
 * 
 */
(function (ctx, fn) {
    'use strict';
    fn(ctx);
})(this, function (ctx) {
    'use strict';
    angular
        .module('portal_app', [
            'ui.router',
            'ngCookies',
            'portal_service.sdk'
        ])
        .config([
            '$stateProvider',
            '$urlRouterProvider',
            '$httpProvider',
            '$portalHttpServiceProvider',
            function ($stateProvider, $urlRouterProvider, $httpProvider, $portalHttpServiceProvider) {
                $stateProvider.state({
                   name: 'login',
                   url: '/login',
                   views: {
                       mainScreen: {
                           templateUrl: 'app/views/login/login.view.html',
                           controller: 'login.ctrl'
                       }
                   }
                });
                $stateProvider.state({
                    name: 'suLogin',
                    url: '/su/login',
                    views: {
                        mainScreen: {
                            templateUrl: 'app/views/login/su_login.view.html',
                            controller: 'su_login.ctrl'
                        }
                    }
                 });
                $stateProvider.state({
                    name: 'signUp',
                    url: '/login/signup',
                    views: {
                        mainScreen: {
                            templateUrl: 'app/views/login/sign_up.view.html',
                            controller: 'sign_up.ctrl'
                        }
                    }
                });
                $stateProvider.state({
                    name: 'adminHome',
                    url: '/home/admin',
                    views: {
                        mainScreen: {
                            templateUrl: 'app/views/home/admin_home.view.html',
                            controller: 'admin_home.ctrl'
                        }
                    }
                });
                $stateProvider.state({
                    name: 'userManagement',
                    url: '/home/admin/manage-user',
                    views: {
                        mainScreen: {
                            template: 'app/views/home/admin_home.view.html'
                        }
                    }
                });
                $urlRouterProvider.otherwise('/login');
                
                $httpProvider.interceptors.push('portal_interceptor.srvc');
                
                $portalHttpServiceProvider
                    .setDomain('localhost')
                    .setPort('8082')
                    .setSecure(false)
                    .setAppCtx('com.ez.portal')
                    .setBaseUrl('rest')
                    .addUrl('DO_LOGIN', 'login/do-login')
                    .addUrl('SIGN_UP', 'login/sign-up')
                    .addUrl('LOGOUT', 'login/logout/')
                    .addUrl('GET_USER_BY_AUTH_TOKEN', 'login/get-user-by-authentication-token/')
                    .addUrl('GET_ALL_USER_SPACES', 'login/get-all-user-spaces');
            }
        ]);
});