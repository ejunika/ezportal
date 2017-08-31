/**
 * 
 */
(function (ctx, fn) {
	'use strict';
	fn(ctx);
})(this, function () {
	'use strict';
	angular
		.module('portal_app')
		.directive('portalUserManagement', [
			function () {
				return {
					restrict: 'E',
					templateUrl: 'app/views/home/portal_user_management.view.html',
					controller: 'portal_user_management.ctrl',
					link: function ($scope, iEl, iAttrs) {}
				};
			}
		]);
});