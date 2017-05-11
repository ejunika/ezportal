/**
 * @author azaz.akhtar 
 */
(function (ctx, fn) {
    'use strict';
    fn(ctx);
})(this, function (ctx) {
    angular
        .module('portal-app.mdl', [
            'ez-multiselect.mdl',
            'ez-querybuilder.mdl'
        ]);
});