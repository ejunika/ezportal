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
        .directive('portalHeader', [
            function () {
                return {
                    restrict: 'E',
                    templateUrl: 'app/views/portal_header.view.html',
                    controller: 'portal_header.ctrl'
                };
            }
        ]);
});