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
        .directive('portalLoader', [
            function () {
                return {
                    restrict: 'E',
                    templateUrl: 'app/views/portal_loader.view.html'
                };
            }
        ]);
});