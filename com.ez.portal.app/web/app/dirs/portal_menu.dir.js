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
		.directive('portalMenu', [
			function () {
				return {
					restrict: 'E',
					templateUrl: 'app/views/home/portal_menu.view.html',
					link: function ($scope, iEl, iAttrs) {}
				};
			}
		]);
});