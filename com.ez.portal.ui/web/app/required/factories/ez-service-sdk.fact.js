/**
 * Service factory as utilities for the use of application
 * @author azaz.akhtar 
 */
(function (ctx, fn) {
    'use strict';
    fn(ctx);
})(this, function (ctx) {
    angular
        .module('ez-utils', [])
        .factory('ez-services.fact', [
            '$rootScope',
            '$http',
            '$timeout',
            ezServiceFactorty
        ]);
    
    /**
     * Angular factory method for creating EZServices factory
     * */
    function ezServiceFactorty($rootScope, $http, $timeout) {
        var services = EZServices.prototype;
        /**
         *  EZServies constructor
         * */
        function EZServices() {}
        services.$http = $http;
        services.request = function (config, data) {
            var defaultConfig = {
                method: 'GET',
                traditional: true,
                crossDomain: true,
                async: true,
                timeout: 10000,
                headers: {
                    'Content-Type': 'application/json' 
                },
                data: data
            };
            config = angular.extend(defaultConfig, config);
            return $http(config);
        };
        services.post = function (url, data, scb, ecb) {
            var config = {
                url: url,
                method: "POST"
            };
            return services.request(config, data);
        };
        services.securePost = function (url, data, scb, ecb) {
            
        };
        return new EZServices();
    }
});