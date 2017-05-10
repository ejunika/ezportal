/**
 * @author azaz.akhtar 
 */
(function (ctx, fn) {
    'use strict';
    fn(ctx);
})(this, function (ctx) {
    angular
        .module('portal-app.mdl', [
            'ez-utils',
            'ez-multiselect.mdl'
        ]);
});