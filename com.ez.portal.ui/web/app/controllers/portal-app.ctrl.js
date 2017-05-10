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
            'ez-services.fact',
            portalAppCtrl
        ]);
    function portalAppCtrl($scope, ezServices) {
        $scope.options1 = [
            {id: 'ID01', label: 'High'},
            {id: 'ID02', label: 'Low'},
        ];
        $scope.options2 = [
            {id: 'ID01', label: 'Hollywood'},
            {id: 'ID02', label: 'Bollywood'},
            {id: 'ID03', label: 'Pollywood'},
        ];
        $scope.selected1 = {id: 'ID01', label: 'High'};
        $scope.selected2 = [];
        $scope.space = {};
        
        $scope.change = function () {
            $scope.selected2 = {id: 'ID03', label: 'Pollywood'};
        };
        
        $scope.config1 = {
            isMultiselect: false
        };
        $scope.config2 = {
                isMultiselect: true
        };
        
        
//        $scope.createSpace = function () {
//            ezServices.post("http://localhost:8082/com.ez.portal/rest/install/create_user_space", {
//                "userSpace": {
//                    "userSpaceName": $scope.space.spaceName
//                }
//            })
//            .then(function (resData) {
//                debugger;
//            }, function (error) {
//                debugger;
//            });
//        };
    }
});