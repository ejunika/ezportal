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
		.directive('loadModule', [
			'$parse',
			'$compile',
			function ($parse, $compile) {
				return {
					restrict: 'E',
					link: function ($scope, iEl, iAttrs) {
						if (iAttrs.module) {
							iEl.append($compile($parse(iAttrs.module)($scope))($scope));
						}
					}
				};
			}
		]);
});