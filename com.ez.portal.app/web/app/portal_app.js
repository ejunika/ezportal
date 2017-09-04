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
            'ngAnimate',
            'toastr',
            'cp.ngConfirm',
            'ui.router',
            'ngCookies',
            'portal_service.sdk'
        ])
        .config([
            '$stateProvider',
            '$urlRouterProvider',
            '$httpProvider',
            '$portalHttpServiceProvider',
            'toastrConfig',
            function ($stateProvider, $urlRouterProvider, $httpProvider, $portalHttpServiceProvider, toastrConfig) {
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
                    .addUrl('GET_ALL_USER_SPACES_FOR_LOGIN', 'login/get-all-user-spaces')
                    .addUrl('DO_LOGIN', 'login/do-login')
                    .addUrl('SIGN_UP', 'login/sign-up')
                    .addUrl('LOGOUT', 'portal-session/logout/')
                    .addUrl('CHECK_PORTAL_SESSION', 'portal-session/check-portal-session/')
                    .addUrl('GET_ALL_USERS', 'user/get-all-users')
                    .addUrl('CREATE_USER', 'user/add-user')
                    .addUrl('REMOVE_USER', 'user/remove-user/')
                    .addUrl('BLOCK_USER', 'user/block-user/')
                    .addUrl('UNBLOCK_USER', 'user/unblock-user/')
                    .addUrl('GET_ALL_USER_SPACES', 'user-space/get-all-user-spaces');
                
                angular.extend(toastrConfig, {
                    autoDismiss: false,
                    containerId: 'toast-container',
                    maxOpened: 0,    
                    newestOnTop: true,
                    positionClass: 'toast-bottom-right',
                    preventDuplicates: false,
                    preventOpenDuplicates: false,
                    target: 'body'
                });
            }
        ]);
});