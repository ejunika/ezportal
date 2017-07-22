/**
 * @author azaz.akhtar 
 */
(function (ctx, fn) {
    'use strict';
    fn(ctx);
})(this, function (ctx) {
    'use strict';
    
    /**
     * 
     * */
    angular
        .module('ez-mouse-capture', [])
        .factory('MouseCaptureFactory', [
            '$rootScope',
            function ($rootScope) {
                var mouseCaptureFactory = MouseCaptureFactory.prototype;
                mouseCaptureFactory.registerElement = function (element) {
                    var mouseCaptureFactory = this;
                    mouseCaptureFactory.$element = element;
                };
                mouseCaptureFactory.release = function () {
                    var mouseCaptureFactory = this;
                    if (mouseCaptureFactory.mouseCaptureConfig) {
                        if (angular.isFunction(mouseCaptureFactory.mouseCaptureConfig.onMouseRelease)) {
                            mouseCaptureFactory.mouseCaptureConfig.onMouseRelease.call(this);
                        }
                        mouseCaptureFactory.mouseCaptureConfig = null;
                    }
                    mouseCaptureFactory.$element.off('mousemove');
                    mouseCaptureFactory.$element.off('mouseup');
                };
                function mouseMove(e) {
                    var mouseCaptureFactory = this;
                    if (angular.isFunction(mouseCaptureFactory.mouseCaptureConfig.onMouseMove)) {
                        mouseCaptureFactory.mouseCaptureConfig.onMouseMove.call(this, e);
                        $rootScope.$digest();
                    }
                };
                function mouseUp(e) {
                    var mouseCaptureFactory = this;
                    if (angular.isFunction(mouseCaptureFactory.mouseCaptureConfig.onMouseUp)) {
                        mouseCaptureFactory.mouseCaptureConfig.onMouseUp.call(this, e);
                        $rootScope.$digest();
                    }
                };
                mouseCaptureFactory.acquire = function (e, mouseCaptureConfig) {
                    var mouseCaptureFactory = this;
                    mouseCaptureFactory.release();
                    mouseCaptureFactory.mouseCaptureConfig = mouseCaptureConfig;
                    mouseCaptureFactory.$element.on('mousemove', mouseMove.bind(mouseCaptureFactory));
                    mouseCaptureFactory.$element.on('mouseup', mouseUp.bind(mouseCaptureFactory));
                };
                return new MouseCaptureFactory();
                function MouseCaptureFactory() {
                    this.$element = $(document);
                    this.mouseCaptureConfig = null; 
                }
            }
        ])
        .directive('mouseCapture', [function() {
            return {
                restrict: 'A',
                controller: [
                    '$scope',
                    '$element',
                    '$attrs',
                    'MouseCaptureFactory',
                    function ($scope, $element, $attrs, mouseCaptureFactory) {
                        mouseCaptureFactory.registerElement($element);
                    }
                ]
            }
        }]);
});