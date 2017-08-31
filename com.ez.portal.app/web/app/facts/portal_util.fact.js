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
    	.factory('portal_util.fact', [
    		function () {
    			var portalUtil = PortalUtil.prototype;
    			portalUtil.getUserTypeFactory = function () {
    				return {
    					0: 'SUPER_USER',
    					1: 'FIRST_USER',
    					2: 'ADMIN',
    					3: 'PRINCIPAL',
    					4: 'FACULTY',
    					5: 'ACCOUNTENT',
    					6: 'STUDENT',
    					7: 'OTHER_STAFF'
    				};
    			};
    			portalUtil.getEntryStatusFactory = function () {
    				return {
    					0: 'NEW',
    					1: 'ACTIVE',
    					2: 'BLOCKED',
    					3: 'DELETED',
    					4: 'ARCHIVED'
    				};
    			};
    			return new PortalUtil();
    			function PortalUtil() {}
    		}
    	]);
});