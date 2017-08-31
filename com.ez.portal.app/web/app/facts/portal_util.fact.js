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
    			
    			portalUtil.getUserType = function () {
    				return {
    					'SUPER_USER'	: 0,
    					'FIRST_USER'	: 1,
    					'ADMIN'			: 2,
    					'PRINCIPAL'		: 3,
    					'FACULTY'		: 4,
    					'ACCOUNTENT'	: 5,
    					'STUDENT'		: 6,
    					'OTHER_STAFF'	: 7
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
    			
    			portalUtil.getEntryStatus = function () {
    				return {
    					'NEW' 		: 0,
    					'ACTIVE' 	: 1,
    					'BLOCKED' 	: 2,
    					'DELETED' 	: 3,
    					'ARCHIVED' 	: 4
    				};
    			};
    			
    			return new PortalUtil();
    			function PortalUtil() {}
    		}
    	]);
});