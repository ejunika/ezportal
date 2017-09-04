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
		.directive('portalDbManager', [
			function () {
				return {
					restrict: 'E',
					templateUrl: 'app/views/home/portal_db_manager.view.html',
					controller: 'portal_db_manager.ctrl',
					link: function ($scope, iEl, iAttrs) {}
				};
			}
		]);
});