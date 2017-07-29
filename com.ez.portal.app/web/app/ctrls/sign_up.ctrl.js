/**
 * 
 */
(function (ctx, fn) {
    'use strict';
    fn(ctx);
})(this, function (ctx) {
    'use strict';
    angular
        .module('portal_app')
        .controller('sign_up.ctrl', [
            '$scope',
            'portal_service.fact',
            function ($scope, portalServiceFactory) {
//                portalServiceFactory
//                    .post('http://localhost:8082/com.ez.portal/rest/login/sign-up', {
//                        user: {
//                            emailId: 'akhtar.azaz@live.com',
//                            username: 'pink_123',
//                            password: 'PASS',
//                            shardKey: '1'
//                        }
//                    })
//                    .then(function (response) {
//                        
//                    });
            }
        ]);
});