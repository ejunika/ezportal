/**
 * @author azaz.akhtar
 */
(function (ctx, fn) {
    'use strict';
    fn(ctx);
})(this, function (ctx) {
    'use strict';
    angular
        .module('portal_app')
        .controller('portal_db_manager.ctrl', [
            '$scope',
            '$state',
            '$portalHttpService',
            'portal_util.fact',
            'toastr',
            function ($scope, $state, $portalHttpService, portalUtilFactory, messageToaster) {
                
            	var TOASTER_OWNER = 'Database Manager';
            	
            	
            	/**
                 * 
                 * */
            	$scope.init = function () {
            		$scope.userSpace = {};
            		getAllDBServers(function (dbServers) {
            			$scope.dbServers = dbServers;
            			$scope.dbServerLocalLang = {
            				nothingSelected: 'Select a server'
            			};
            		});
            		getAllDBServerTypes(function (dbServerTypes) {
            			$scope.dbServerTypes = dbServerTypes;
            			$scope.dbServerTypeLocalLang = {
            				nothingSelected: 'Select a server type'
            			};
            		});
            		getAllUserSpaces(function (userSpaces) {
            			$scope.userSpaces = userSpaces;
            		});
                };
                
                $scope.reloadUserSpaces = function(e) {
                	getAllUserSpaces(function (userSpaces) {
            			$scope.userSpaces = userSpaces;
            		});
                };
                
                $scope.enableServerForm = function () {
                	$scope.isEnableServerForm = true;
                };
                
                $scope.saveServer = function (e, server) {
                	var newServer = angular.copy(server);
                	if (newServer.dbServerName) {
                		newServer.dbServerDisplayName = newServer.dbServerDisplayName || newServer.dbServerName;
                		$scope.dbServers.push(newServer);
                	}
                	$scope.isEnableServerForm = false;
                };
                
                $scope.createSpace = function (e, userSpace) {
                	var reqData = angular.copy(userSpace);
                	reqData.dbServer = reqData.dbServer[0];
                	reqData.dbServer.dbServerType = 1;
                	delete reqData.dbServer.selected;
                	delete reqData.selected;
                	$portalHttpService
	                    .post($portalHttpService.Url.CREATE_USER_SPACE, {
	                    	userSpace: reqData
	                    })
	                    .then(function (response) {
	                        if (response && response.data && response.data.status) {
	                        	arguments[0] = response.data.userSpaces;
	                        	cb.apply(this, arguments);
	                        	messageToaster.info('User Spaces refreshed', TOASTER_OWNER);
	                        }
	                    });
                };
                
                /**
                 * 
                 * */
                function getAllUserSpaces(cb) {
                	var users = [];
                	if (angular.isFunction(cb)) {
                		$portalHttpService
	                        .get($portalHttpService.Url.GET_USER_SPACES)
	                        .then(function (response) {
	                            if (response && response.data && response.data.status) {
	                            	arguments[0] = response.data.userSpaces;
	                            	cb.apply(this, arguments);
	                            	messageToaster.info('User Spaces refreshed', TOASTER_OWNER);
	                            }
	                        });
                	}
                }
                
                /**
                 * 
                 * */
                function getAllDBServers(cb) {
                	var users = [];
                	if (angular.isFunction(cb)) {
                		arguments[0] = [
                			{
                				dbServerId: 12345,
                				dbServerName: 'MYSQL_RKDF',
                				dbServerDisplayName: 'MySql RKDF',
                				selected: false
                			},
                			{
                				dbServerId: 12346,
                				dbServerName: 'ORACLE_RKDF',
                				dbServerDisplayName: 'Oracle RKDF',
                				selected: false
                			},
                			{
                				dbServerId: 12347,
                				dbServerName: 'POSTGRADE_RKDF',
                				dbServerDisplayName: 'Post Grade RKDF',
                				selected: false
                			}
                		];
                		cb.apply(this, arguments);
                	}
                }
                
                function getAllDBServerTypes(cb) {
                	var users = [];
                	if (angular.isFunction(cb)) {
                		arguments[0] = [
                			{
                				dbServerTypeId: 12345,
                				dbServerTypeName: 'MYSQL_SERVER',
                				dbServerTypeDisplayName: 'MySql',
                				selected: false
                			},
                			{
                				dbServerTypeId: 12346,
                				dbServerTypeName: 'MSSQL_SERVER',
                				dbServerTypeDisplayName: 'MS Sql',
                				selected: false
                			},
                			{
                				dbServerTypeId: 12347,
                				dbServerTypeName: 'ORACLE',
                				dbServerTypeDisplayName: 'Oracle',
                				selected: false
                			},
                			{
                				dbServerTypeId: 12348,
                				dbServerTypeName: 'POSTGRADE',
                				dbServerTypeDisplayName: 'Post Grade',
                				selected: false
                			}
                		];
                		cb.apply(this, arguments);
                	}
                }
                
                
            }
        ]);
});