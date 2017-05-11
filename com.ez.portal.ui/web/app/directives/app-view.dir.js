/**
 * @author azaz.akhtar 
 */
(function (ctx, fn) {
    'use strict';
    fn(ctx);
})(this, function (ctx) {
    angular
        .module('portal-app.mdl')
        .directive('appView', [
            '$rootScope',
            appViewDir
        ]);
    function appViewDir($rootScope) {
        return {
            restrict: 'E',
            templateUrl: 'app/views/app-view.tpl.html',
            link: function ($scope, iEl, iAttrs) {
                
            }
        };
    }
});