/**
 * 
 */
(function (ctx, fn) {
    'use strict';
    fn(ctx);
})(this, function (ctx) {
    //'use strict';
    angular
        .module('portal_app')
        .service('portal_interceptor.srvc', [
           '$rootScope',
           function ($rootScope) {
               var portalInterceptorService = this;
               portalInterceptorService.request = function (config) {
                   $rootScope.$broadcast('portal.handle_loader', true);
                   if (portalInterceptorService.selectedUserSpace 
                           && portalInterceptorService.selectedUserSpace.userSpaceId) {
                       config.headers['US-KEY'] = portalInterceptorService.selectedUserSpace.userSpaceId;
                   } else {
                       delete config.headers['US-KEY'];
                   }
                   return config;
               };
               portalInterceptorService.requestError = function (rejection) {
                   $rootScope.$broadcast('portal.handle_loader', false);
                   return rejection;
               };
               portalInterceptorService.response = function (config) {
                   $rootScope.$broadcast('portal.handle_loader', false);
                   return config;
               };
               portalInterceptorService.responseError = function (rejection) {
                   $rootScope.$broadcast('portal.handle_loader', false);
                   return rejection;
               };
           }
        ])
        .factory('portal_service.fact', [
            '$http',
            function ($http) {
                return {
                    post: function (url, data) {
                        return $http({
                            url: url,
                            method: 'POST',
                            data: data
                        });
                    },
                    getJson: function (url) {
                        return $http({
                            url: url,
                            method: 'GET'
                        });
                    },
                    get: function (url) {
                        return $http({
                            url: url,
                            method: 'GET'
                        });
                    }
                };
            }
        ]);
});