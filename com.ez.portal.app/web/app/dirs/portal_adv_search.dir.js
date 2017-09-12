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
		.directive('portalAdvSearch', [
			'$parse',
			'$compile',
			function ($parse, $compile) {
				return {
					restrict: 'E',
					templateUrl: 'app/views/portal_adv_search.view.html',
					link: function ($scipe, iEl, iAttrs, ngModel) {
						debugger;
					}
				};
			}
		]);
});