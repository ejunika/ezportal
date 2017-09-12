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
        .controller('portal_user_management.ctrl', [
            '$scope',
            '$filter',
            '$state',
            '$portalHttpService',
            'portal_util.fact',
            'toastr',
            '$ngConfirm',
            function ($scope, $filter, $state, $portalHttpService, portalUtilFactory, messageToaster, $ngConfirm) {
                
            	var TOASTER_OWNER = 'User Manager';
            	var userTypeFactory = portalUtilFactory.getUserTypeFactory();
        		var entryStatusFactory = portalUtilFactory.getEntryStatusFactory();
        		var USER_TYPE = portalUtilFactory.getUserType();
        		var ENTRY_STATUS = portalUtilFactory.getEntryStatus();
            	
            	/**
                 * 
                 * */
            	$scope.init = function () {
            		$scope.USER_TYPE = USER_TYPE;
            		$scope.ENTRY_STATUS = ENTRY_STATUS;
            		$scope.isUserFormVisible = false;
            		$scope.userTypes = getUserTypes();
            		$scope.user = {};
            		$scope.userInfos= [{}];
            		$scope.entryStatusCollection = [
            			{
            				id: 0,
            				entryStatusName: 'New',
            			},
            			{
            				id: 1,
            				entryStatusName: 'Active',
            				checked: true
            			},
            			{
            				id: 2,
            				entryStatusName: 'Blocked',
            			},
            			{
            				id: 3,
            				entryStatusName: 'Deleted'
            			}
            		];
            		$scope.entryStatusList = $filter('filter')($scope.entryStatusCollection, {
            			checked: true
            		});
            		$scope.userTypeLocalLang = {
            			nothingSelected: 'Select User Type'
            		};
            		getAllUsers(function (users) {
            			if (users) {
            				initUsers(users);
            			}
            		});
            		getAllUserInfoKeys(function (userInfoKeys) {
            			if (userInfoKeys) {
            				initUserInfoKeys(userInfoKeys);
            			}
            		});
                };
                
                /**
                 * 
                 * */
                $scope.reloadUsers = function (e) {
                	getAllUsers(function (users) {
            			if (users) {
            				initUsers(users);
            			}
            		});
                };
                
                $scope.addUserInfo = function (e, user) {
                	$scope.userInfos.push({});
                };
                
                /**
                 * 
                 * */
                $scope.removeUser = function (e, user) {
                	var userId = user.userId;
                	if (userId) {
	                	$ngConfirm({
	                        title: 'Confirm!',
	                        content: 'Do you want to delete this user?',
	                        scope: $scope,
	                        buttons: {
	                            no: {
	                            	text: 'No',
	                            	btnClass: 'btn-green',
	                            	action: function (scope, button) {
	                            		return true;
	                            	}
	                            },
	                            yes: {
	                                text: 'Yes',
	                                btnClass: 'btn-sm btn-blue',
	                                action: function (scope, button) {
	                                    removeUser(userId, function (users) {
	                            			if (users) {
	                            				initUsers(users);
	                            			}
	                            		});
	                                    return true;
	                                }
	                            }
	                        }
	                    });
                	}
                };
                
                /**
                 * 
                 * */
                $scope.toggleBlockUser = function (e, user) {
                	var userId = user.userId, entryStatus = user.entryStatus;
                	if (userId) {
                		if (entryStatus == 'ACTIVE') {
                			$ngConfirm({
    	                        title: 'Confirm!',
    	                        content: 'Do you want to block this user?',
    	                        scope: $scope,
    	                        buttons: {
    	                            no: {
    	                            	text: 'No',
    	                            	btnClass: 'btn-green',
    	                            	action: function (scope, button) {
    	                            		return true;
    	                            	}
    	                            },
    	                            yes: {
    	                                text: 'Yes',
    	                                btnClass: 'btn-sm btn-blue',
    	                                action: function (scope, button) {
    	                                	blockUser(userId, function (users) {
    	                        				if (users) {
    	                        					initUsers(users);
    	                        				}
    	                        			});
    	                                    return true;
    	                                }
    	                            }
    	                        }
    	                    });
                		} else {
                			$ngConfirm({
    	                        title: 'Confirm!',
    	                        content: 'Do you want to activate this user?',
    	                        scope: $scope,
    	                        buttons: {
    	                            no: {
    	                            	text: 'No',
    	                            	btnClass: 'btn-green',
    	                            	action: function (scope, button) {
    	                            		return true;
    	                            	}
    	                            },
    	                            yes: {
    	                                text: 'Yes',
    	                                btnClass: 'btn-sm btn-blue',
    	                                action: function (scope, button) {
    	                                	unblockUser(userId, function (users) {
    	                        				if (users) {
    	                        					initUsers(users);
    	                        				}
    	                        			});
    	                                    return true;
    	                                }
    	                            }
    	                        }
    	                    });
                		}
                	}
                };
            	
                /**
                 * 
                 * */
            	$scope.showUserForm = function () {
            		angular.forEach($scope.breadcrumbStack, function (breadcrumbStackItem, index) {
                    	breadcrumbStackItem.isActive = false;
                    });
            		$scope.breadcrumbStack.push({
                    	label: 'Create User',
                    	isActive: true
                    });
            		$scope.isUserFormVisible = true;
            	};
            	
            	$scope.entryStatusHandler = function () {
            		$scope.entryStatusList = $filter('filter')($scope.entryStatusCollection, {
            			checked: true
            		});
            		getAllUsers(function (users) {
            			if (users) {
            				initUsers(users);
            			}
            		});
            	};
            	
            	/**
                 * 
                 * */
            	$scope.saveUser = function (e, user, password, userInfos) {
            		if (user && user.emailId && user.username && user.userType) {
            			user.userType = user.userType[0] ? user.userType[0].typeKey * 1 : null;
            			$portalHttpService
	                        .post($portalHttpService.Url.CREATE_USER, {
	                        	user: user,
	                        	password: password,
	                        	userInfos: userInfos
	                        })
	                        .then(function (response) {
	                            if (response && response.data && response.data.status) {
	                            	
	                            }
	                        });
            		}
            	};
            	
            	/**
                 * 
                 * */
            	function getAllUsers(cb) {
                	var users = [], queryParams = '?ofs=1&lmt=100&';
                	if (angular.isFunction(cb)) {
                		angular.forEach($scope.entryStatusList, function(entryStatus, index) {
                			queryParams += 'esl=' + entryStatus.id + '&';
                		});
                		queryParams = queryParams.substr(0, queryParams.length - 1);
                		$portalHttpService
	                        .get($portalHttpService.Url.GET_ALL_USERS + queryParams)
	                        .then(function (response) {
	                            if (response && response.data && response.data.status) {
	                            	arguments[0] = response.data.users;
	                            	cb.apply(this, arguments);
	                            	messageToaster.info('User list refreshed', TOASTER_OWNER);
	                            }
	                        });
                	}
                }
            	
            	/**
            	 * 
            	 * */
            	function getAllUserInfoKeys(cb) {
            		var userInfoKeys = [];
            		if (angular.isFunction(cb)) {
//            			$portalHttpService
//	            			.get($portalHttpService.Url.GET_ALL_USERS)
//	            			.then(function (response) {
//	            				if (response && response.data && response.data.status) {
//	            					arguments[0] = response.data.users;
//	            					cb.apply(this, arguments);
//	            					messageToaster.info('User list refreshed', TOASTER_OWNER);
//	            				}
//	            			});
            			arguments[0] = [
            				{
            					infoKeyId: 1,
            					infoKeyName: 'First Name'
            				},
            				{
            					infoKeyId: 2,
            					infoKeyName: 'Middle Name'
            				},
            				{
            					infoKeyId: 3,
            					infoKeyName: 'Last Name'
            				},
            				{
            					infoKeyId: 4,
            					infoKeyName: 'Father\'s Name'
            				},
            				{
            					infoKeyId: 5,
            					infoKeyName: 'Mother\'s Name'
            				}
            			];
            			cb.apply(this, arguments);
            		}
            	}
            	
            	function removeUser(userId, cb) {
            		if (userId) {
            			$portalHttpService
	                        .get($portalHttpService.Url.REMOVE_USER + userId)
	                        .then(function (response) {
	                            if (response && response.data && response.data.status) {
	                            	getAllUsers(cb);
	                            }
	                        });
            		}
            	}
            	
            	function blockUser(userId, cb) {
            		if (userId) {
            			$portalHttpService
            			.get($portalHttpService.Url.BLOCK_USER + userId)
            			.then(function (response) {
            				if (response && response.data && response.data.status) {
            					getAllUsers(cb);
            				}
            			});
            		}
            	}
            	
            	function unblockUser(userId, cb) {
            		if (userId) {
            			$portalHttpService
            			.get($portalHttpService.Url.UNBLOCK_USER + userId)
            			.then(function (response) {
            				if (response && response.data && response.data.status) {
            					getAllUsers(cb);
            				}
            			});
            		}
            	}
            	
            	/**
                 * 
                 * */
            	function initUsers(users) {
            		angular.forEach(users, function (user, index) {
            			user.userType = userTypeFactory[user.userType];
            			user.entryStatus = entryStatusFactory[user.entryStatus];
            		});
                	$scope.users = users;
                }
            	
            	/**
            	 * 
            	 * */
            	function initUserInfoKeys(userInfoKeys) {
            		
            	}
            	
            	/**
                 * 
                 * */
            	function getUserTypes() {
            		var userTypes = [];
            		angular.forEach(userTypeFactory, function (typeName, typeKey) {
            			if (typeKey != USER_TYPE.SUPER_USER && typeKey != USER_TYPE.FIRST_USER) {
            				userTypes.push({
            					typeName: typeName,
            					typeKey: typeKey
            				});
            			}
            		});
            		return userTypes;
            	}
            }
        ]);
});