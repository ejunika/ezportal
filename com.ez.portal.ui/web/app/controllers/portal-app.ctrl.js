/**
 * @author azaz.akhtar 
 */
(function (ctx, fn) {
    'use strict';
    fn(ctx);
})(this, function (ctx) {
    angular
        .module('portal-app.mdl')
        .controller('portal-app.ctrl', [
            '$scope',
            portalAppCtrl
        ]);
    function portalAppCtrl($scope) {
        $scope.ezOptions = [
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
                label: 'BHUTTAN'
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
        $scope.ezConfig = {
            isMultiselect: true,
            noSelectionText: 'Choose',
            maxSelectionText: 2,
            showSelectAll: true,
            showSearch: true
        };
        $scope.ezConfig1 = {
            isMultiselect: false,
            noSelectionText: 'Select',
            showSearch: true,
            selectionColor: 'green',
            width: ''
        };
        $scope.ezConfig2 = {
            isMultiselect: false,
            noSelectionText: 'Select',
            showSearch: false,
            selectionColor: 'orange'
        };
        $scope.ezOnselect = function (currentOption) {
            $scope.cities = [
                {
                    id: 'BANG',
                    label: 'Bangalore',
                    cId: 'IND'
                },
                {
                    id: 'KATH',
                    label: 'Kathmandoo',
                    cId: 'NEP'
                }
            ];
        };
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
        $scope.cities = [
        ];
//        $scope.selectedCountry
        $scope.selectedCity = {};
    }
});