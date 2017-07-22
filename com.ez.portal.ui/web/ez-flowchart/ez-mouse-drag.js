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
        .module('ez-mouse-drag', [
            'ez-mouse-capture'
        ])
        .factory('MouseDragFactory', [
            '$rootScope',
            'MouseCaptureFactory',
            function ($rootScope, mouseCaptureFactory) {
                var mouseDragFactory = MouseDragFactory.prototype;
                mouseDragFactory.startDrag = function (e, mouseDragConfig) {
                    var xCo = e.pageX, 
                        yCo = e.pageY,
                        isDragging = false,
                        threshold = 5;
                    
                    mouseCaptureFactory.acquire(e, {
                        onMouseMove: onMouseMove,
                        onMouseUp: onMouseUp,
                        onMouseRelease: onMouseRelease
                    });
                    
                    e.stopPropagation();
                    e.preventDefault();
                    
                    function onMouseMove(e) {console.log('Hi')
                        if (!isDragging) {
                            if (Math.abs(e.pageX - xCo) > threshold || Math.abs(e.pageY - yCo) > threshold) {
                                isDragging = true;
                                if (angular.isFunction(mouseDragConfig.onDragStart)) {
                                    mouseDragConfig.onDragStart.call(this, e, xCo, yCo);
                                }
                                if (angular.isFunction(mouseDragConfig.onDragging)) {
                                    mouseDragConfig.onDragging.call(this, e, e.pageX, e.pageY);
                                }
                            }
                        } else {
                            if (angular.isFunction(mouseDragConfig.onDragging)) {
                                mouseDragConfig.onDragging.call(this, e, e.pageX, e.pageY);
                            }
                            xCo = e.pageX;
                            yCo = e.pageY;
                        }
                    }
                    
                    function onMouseRelease(e) {
                        if (isDragging) {
                            if (angular.isFunction(mouseDragConfig.onDragStop)) {
                                mouseDragConfig.onDragStop.call(this);
                            }
                        }
                    }
                    
                    function onMouseUp(e) {
                        mouseCaptureFactory.release();
                        e.stopPropagation();
                        e.preventDefault();
                    }
                };
                
                return new MouseDragFactory();
                function MouseDragFactory() {}
            }
        ]);
});