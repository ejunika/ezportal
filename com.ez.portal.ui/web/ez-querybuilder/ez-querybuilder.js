/**
 * Query Builder
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
    function ezQuerybuilder($rootScope, $timeout, $parse) {
        return {
            restrict: 'AE',
            scope: {
                
            },
            require: 'ngModel',
            templateUrl: 'ez-querybuilder/ez-querybuilder.tpl.html',
            link: function ($scope, iEl, iAttrs, ngModelCtrl) {
                $scope.countries = [
                    {
                        id: 'IND',
                        label: 'India'
                    },
                    {
                        id: 'PAK',
                        label: 'Pakistan'
                    },
                    {
                        id: 'NEP',
                        label: 'Nepal'
                    },
                    {
                        id: 'BHU',
                        label: 'Bhuttan'
                    },
                    {
                        id: 'CHI',
                        label: 'China'
                    },
                    {
                        id: 'US',
                        label: 'United States'
                    }
                ];
            }
        };
    }
    
    /**
     * 
     * */
    angular
        .module('ez-querybuilder.mdl', [])
        .directive('ezQuerybuilder', [
            '$rootScope', 
            '$timeout', 
            '$parse', 
            ezQuerybuilder
        ]);
});