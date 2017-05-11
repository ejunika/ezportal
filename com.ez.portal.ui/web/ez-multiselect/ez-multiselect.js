/**
 * Multiselect dropdown
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
    function ezMultiselect($rootScope, $timeout, $parse) {
        return {
            restrict: 'AE',
            scope: {
                ezOptions: '=',
                ezConfig: '=',
                ezOnselect: '=',
                ngModel: '='
            },
            require: 'ngModel',
            templateUrl: 'ez-multiselect/ez-multiselect.tpl.html',
            link: function ($scope, iEl, iAttrs, ngModelCtrl) {
                var
                
                /**
                 * 
                 * */
                defaultConfig = {
                    isMultiselect: false,
                    width: '100%',
                    isDropUp: false,
                    noSelectionText: 'Select',
                    maxSelectionText: 1,
                    showSelectAll: false,
                    showSearch: true,
                    selectionColor: 'blue'
                };
                
                /**
                 * 
                 * */
                $scope.selectedOption = {};
                
                /**
                 * 
                 * */
                $scope.isoEzOptions = angular.copy($scope.ezOptions);
                
                /**
                 * 
                 * */
                $scope.config = angular.extend({}, defaultConfig, $scope.ezConfig);
                
                /**
                 * 
                 * */
                ngModelCtrl.$formatters.push(function(modelValue) {
//                    if ($scope.config.isMultiselect && !angular.isArray(modelValue)) {
//                        throw new Error('ngModel for multiselect should be an array');
//                    }
//                    if (!$scope.config.isMultiselect && angular.isArray(modelValue)) {
//                        throw new Error('ngModel for single select should be an object');
//                    }
                    if (!angular.isArray(modelValue)) {
                        modelValue = [];
                        ngModelCtrl.$setViewValue(modelValue);
                    }
                    return modelValue;
                });
                
                /**
                 * 
                 * */
                ngModelCtrl.$render = function() {
                    if (angular.isArray(ngModelCtrl.$viewValue)) {
                        for (var i = 0; i < ngModelCtrl.$viewValue.length; i++) {
                            for (var j = 0; j < $scope.isoEzOptions.length; j++) {
                                if (ngModelCtrl.$viewValue[i].id == $scope.isoEzOptions[j].id) {
                                    $scope.isoEzOptions[j].isChecked = true;
                                    break;
                                }
                            }
                        }
                    }
                    updateDisplayText();
                };
                
                /**
                 * 
                 * */
                $scope.selectAll = function (e) {
                    e.preventDefault();
                    e.stopPropagation();
                };
                
                /**
                 * 
                 * */
                $scope.unselectAll = function (e) {
                    e.preventDefault();
                    e.stopPropagation();
                };
                
                /**
                 * 
                 * */
                $scope.ezSelectOption = function (e, ezOption) {
                    var selectedOption;
                    if ($scope.config.isMultiselect) {
                        e.stopPropagation();
//                        e.preventDefault();
                        if (angular.isArray(ngModelCtrl.$viewValue)) {
                            if (ezOption.isChecked) {
                                ezOption.isChecked = false;
                                for (var i = 0; i < ngModelCtrl.$viewValue.length; i++) {
                                    if (ngModelCtrl.$viewValue[i].id == ezOption.id) {
                                        ngModelCtrl.$viewValue.splice(i, 1);
                                        break;
                                    }
                                }
                            } else {
                                ezOption.isChecked = true;
                                selectedOption = angular.copy(ezOption);
                                delete selectedOption.isChecked;
                                ngModelCtrl.$viewValue.push(selectedOption);
                            }
                        }
                    } else {
                        var view = angular.copy(ezOption);
                        delete view.isChecked;
                        delete view.isSelected;
                        if ($scope.selectedOption.id == ezOption.id) {
                            if ($scope.selectedOption.isSelected) {
                                $scope.selectedOption.isSelected = false;
                                ngModelCtrl.$setViewValue([]);
                            } else {
                                ezOption.isSelected = true;
                                $scope.selectedOption = ezOption;
                                ngModelCtrl.$setViewValue([view]);
                            }
                            
                        } else {
                            $scope.selectedOption.isSelected = false;
                            ezOption.isSelected = true;
                            $scope.selectedOption = ezOption;
                            ngModelCtrl.$setViewValue([view]);
                        }
                        
                    }
                    if (angular.isFunction($scope.ezOnselect)) {
                        $scope.ezOnselect.call(this, selectedOption || ezOption);
                    }
                };
                
                /**
                 * 
                 * */
                $scope.clearSearch = function (e) {
                    e.stopPropagation();
                    $scope.searchText = '';
                };
                
                /**
                 * 
                 * */
                $scope.$watch('ngModel', function (newValue, oldValue) {
                    if (newValue != oldValue) {
                        ngModelCtrl.$render();
                    }
                }, true);
                
                /**
                 * 
                 * */
                $scope.$watch('ezOptions', function (newValue, oldValue) {
                    if (newValue != oldValue) {
                        $scope.isoEzOptions = angular.copy(newValue);
                    }
                }, true);
                
                /**
                 * 
                 * */
                $scope.getSelectionStyle = function () {
                    return {
                        width: '3px',
                        height: '100%',
                        position: 'absolute',
                        top: '0',
                        left: '1px',
                        background: $scope.config.selectionColor
                    }
                };
                
                /**
                 * 
                 * */
                function updateDisplayText() {
                    if ($scope.config.isMultiselect) {
                        $scope.ezDisplayText = '';
                        if (angular.isArray(ngModelCtrl.$viewValue) && ngModelCtrl.$viewValue.length > 0) {
                            for (var i = 0; i < ngModelCtrl.$viewValue.length && i < $scope.config.maxSelectionText; i++) {
                                $scope.ezDisplayText += ngModelCtrl.$viewValue[i].label + ', ';
                            }
                            $scope.ezDisplayText = $scope.ezDisplayText
                                .substring(0, $scope.ezDisplayText.length - 2);
                            if (ngModelCtrl.$viewValue.length > $scope.config.maxSelectionText) {
                                $scope.ezDisplayText += ',..+' + (ngModelCtrl.$viewValue.length - $scope.config.maxSelectionText);
                            }
                        } else {
                            $scope.ezDisplayText = $scope.config.noSelectionText;
                        }
                    } else {
                        $scope.ezDisplayText = (ngModelCtrl.$viewValue[0] && ngModelCtrl.$viewValue[0].label) || $scope.config.noSelectionText;
                    }
                }
            }
        };
    }
    
    /**
     * 
     * */
    angular
        .module('ez-multiselect.mdl', [])
        .directive('ezMultiselect', ['$rootScope', '$timeout', '$parse', ezMultiselect]);
});