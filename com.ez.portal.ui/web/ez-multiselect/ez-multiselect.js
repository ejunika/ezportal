/**
 * 
 * @author azaz.akhtar 
 */
(function (ctx, fn) {
    'use strict';
    fn(ctx);
})(this, function (ctx) {
    'use strict';
    function ezMultiselect($rootScope, $timeout, $parse) {
        return {
            restrict: 'AE',
            scope: {
                ezOptions: '=',
                ezConfig: '='
            },
            require: 'ngModel',
            templateUrl: 'ez-multiselect/ez-multiselect.tpl.html',
            link: function ($scope, iEl, iAttrs, ngModelCtrl) {
                var
                defaultConfig = {
                    isMultiselect: false,
                    width: '100%',
                    isDropUp: false
                };
                $scope.config = angular.extend({}, defaultConfig, $scope.ezConfig);
                ngModelCtrl.$formatters.push(function(modelValue) {
                    if ($scope.config.isMultiselect) {
                        delete modelValue.isChecked;
                    }
                    return modelValue;
                });
                ngModelCtrl.$render = function() {
                    updateDisplayText();
                };
                $scope.ezSelectOption = function (e, ezOption) {
                    if ($scope.config.isMultiselect) {
                        e.stopPropagation();
                        if (angular.isArray(ngModelCtrl.$viewValue)) {
                            if (!isDuplicate(ezOption)) {
                                ngModelCtrl.$viewValue.push(ezOption);
                            }
                        }
                    } else {
                        ngModelCtrl.$setViewValue(ezOption);
                    }
                    ngModelCtrl.$render();
                };
                $scope.clearSearch = function (e) {
                    e.stopPropagation();
                    $scope.searchText = '';
                };
                function updateDisplayText() {
                    if ($scope.config.isMultiselect) {
                        $scope.ezDisplayText = '';
                        if (angular.isArray(ngModelCtrl.$viewValue) && ngModelCtrl.$viewValue.length > 0) {
                            for (var i = 0; i < ngModelCtrl.$viewValue.length; i++) {
                                $scope.ezDisplayText += ngModelCtrl.$viewValue[i].label + ', ';
                            }
                            $scope.ezDisplayText = $scope.ezDisplayText
                                .substring(0, $scope.ezDisplayText.length - 2);
                        } else {
                            $scope.ezDisplayText = 'Select';
                        }
                    } else {
                        $scope.ezDisplayText = ngModelCtrl.$viewValue.label || 'Select';
                    }
                }
                function isDuplicate(option) {
                    return ngModelCtrl.$viewValue.indexOf(option) != -1;
                }
            }
        };
    }
    angular
        .module('ez-multiselect.mdl', [])
        .directive('ezMultiselect', ['$rootScope', '$timeout', '$parse', ezMultiselect]);
});