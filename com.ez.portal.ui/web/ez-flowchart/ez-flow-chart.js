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
        .module('ez-flow-chart', [
            'ez-mouse-drag'
        ])
        .directive('ezFlowChart', [function () {
            return {
                restrict: 'E',
                templateUrl: 'ez-flowchart/ez-flow-chart.html',
                replace: true,
                scope: {
                    ezChart: '='
                },
                controller: [
                    '$scope', 
                    '$element', 
                    'MouseDragFactory', 
                    function ($scope, $element, mouseDragFactory) {
                        $scope.onMouseDown = function (e) {
                            mouseDragFactory.startDrag(e, {
                                onDragStart: function (xCo, yCo) {
                                    
                                },
                                onDragging: function (xCo, yCo) {
                                    
                                },
                                onDragStop: function () {
                                    
                                }
                            });
                        };
                        $scope.onMouseMove = function (e) {
                            
                        };
                }]
            }
        }]);
});