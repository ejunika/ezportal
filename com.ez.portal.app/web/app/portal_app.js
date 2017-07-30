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
            'ngCookies'
        ])
        .config([
            '$stateProvider',
            '$urlRouterProvider',
            '$httpProvider',
            function ($stateProvider, $urlRouterProvider, $httpProvider) {
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
            }
        ]);
});