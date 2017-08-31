package com.ez.portal.core.rest.manager.impl;

import java.util.ArrayList;
import java.util.List;

import com.ez.portal.core.dao.manager.intf.DAOManager;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.request.UserRequest;
import com.ez.portal.core.response.UserResponse;
import com.ez.portal.core.rest.manager.intf.UserServiceManager;
import com.ez.portal.core.util.EntityUtil;

public class UserServiceManagerImpl implements UserServiceManager {

	private DAOManager daoManager;

	private UserResponse userResponse;

	/**
	 * @return the daoManager
	 */
	public DAOManager getDaoManager() {
		return daoManager;
	}

	/**
	 * @param daoManager
	 *            the daoManager to set
	 */
	public void setDaoManager(DAOManager daoManager) {
		this.daoManager = daoManager;
	}

	/**
	 * @return the userResponse
	 */
	public UserResponse getUserResponse() {
		return userResponse;
	}

	/**
	 * @param userResponse
	 *            the userResponse to set
	 */
	public void setUserResponse(UserResponse userResponse) {
		this.userResponse = userResponse;
	}

	@Override
	public UserResponse getAllusers() {
		List<User> users = null;
		try {
			userResponse.resetResponse();
			users = daoManager.getUserDAO().getAllPossibleUsers();
			if (users != null) {
				userResponse.setUsers(users);
				userResponse.setMessage("Got users successfully");
				userResponse.setStatus(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponse.setUsers(users);
			userResponse.setMessage(e.getMessage());
			userResponse.setStatus(false);
		}
		return userResponse;
	}

	@Override
	public UserResponse addUser(UserRequest userRequest) {
		User user = userRequest.getUser();
		User newUser = null;
		List<User> users = new ArrayList<>();
		userResponse.resetResponse();
		if (user != null) {
			try {
				newUser = daoManager.getUserDAO().createUser(user);
				users.add(newUser);
				userResponse.setUsers(users);
				userResponse.setMessage("User addded successfully");
				userResponse.setStatus(true);
			} catch (Exception e) {
				e.printStackTrace();
				userResponse.setUsers(users);
				userResponse.setMessage(e.getMessage());
				userResponse.setStatus(false);
			}

		}
		return userResponse;
	}

	@Override
	public UserResponse updateUser(UserRequest userRequest) {
		User user = userRequest.getUser();
		User newUser = null;
		List<User> users = new ArrayList<>();
		userResponse.resetResponse();
		if (user != null) {
			try {
				newUser = daoManager.getUserDAO().update(user);
				users.add(newUser);
				userResponse.setUsers(users);
				userResponse.setMessage("User updated successfully");
				userResponse.setStatus(true);
			} catch (Exception e) {
				e.printStackTrace();
				userResponse.setUsers(users);
				userResponse.setMessage(e.getMessage());
				userResponse.setStatus(false);
			}

		}
		return userResponse;
	}

	public UserResponse changeUserEntryStatus(Long userId, Byte entryStatus) {
		User user = null;
		List<User> users = new ArrayList<>();
		userResponse.resetResponse();
		if (userId != null && entryStatus != null) {
			try {
				user = daoManager.getUserDAO().get(userId);
				if (user != null) {
					user.setEntryStatus(entryStatus);
					try {
						user = daoManager.getUserDAO().update(user);
						if (user != null) {
							users.add(user);
							userResponse.setUsers(users);
							userResponse.setMessage("User updated successfully");
							userResponse.setStatus(true);
						}
					} catch (Exception e) {
						e.printStackTrace();
						userResponse.setUsers(users);
						userResponse.setMessage(e.getMessage());
						userResponse.setStatus(false);
					}

				} else {
					userResponse.setUsers(users);
					userResponse.setMessage("User not found");
					userResponse.setStatus(false);
				}
			} catch (Exception e) {
				e.printStackTrace();
				userResponse.setUsers(users);
				userResponse.setMessage(e.getMessage());
				userResponse.setStatus(false);
			}

		}
		return userResponse;
	}

	@Override
	public UserResponse blockUser(Long userId) {
		Boolean result = false;
		List<User> users = new ArrayList<>();
		userResponse.resetResponse();
		if (userId != null) {
			try {
				result = daoManager.getUserDAO().blockUser(userId);
				if (result) {
					userResponse.setUsers(users);
					userResponse.setMessage("User Blocked successfully");
					userResponse.setStatus(true);
				} else {
					userResponse.setUsers(users);
					userResponse.setMessage("Error");
					userResponse.setStatus(false);
				}
			} catch (Exception e) {
				e.printStackTrace();
				userResponse.setUsers(users);
				userResponse.setMessage(e.getMessage());
				userResponse.setStatus(false);
			}

		}
		return userResponse;
	}

	@Override
	public UserResponse unblockUser(Long userId) {
		Boolean result = false;
		List<User> users = new ArrayList<>();
		userResponse.resetResponse();
		if (userId != null) {
			try {
				result = daoManager.getUserDAO().unblockUser(userId);
				if (result) {
					userResponse.setUsers(users);
					userResponse.setMessage("User unblocked successfully");
					userResponse.setStatus(true);
				} else {
					userResponse.setUsers(users);
					userResponse.setMessage("User not found");
					userResponse.setStatus(false);
				}
			} catch (Exception e) {
				e.printStackTrace();
				userResponse.setUsers(users);
				userResponse.setMessage(e.getMessage());
				userResponse.setStatus(false);
			}

		}
		return userResponse;
	}

	@Override
	public UserResponse activateUser(Long userId) {
		return changeUserEntryStatus(userId, EntityUtil.ACTIVE_ENTRY);
	}

	@Override
	public UserResponse removeUser(Long userId) {
		User user = null;
		List<User> users = new ArrayList<>();
		userResponse.resetResponse();
		if (userId != null) {
			try {
				user = daoManager.getUserDAO().get(userId);
				if (user != null) {
					user.setEntryStatus(EntityUtil.DELETED_ENTRY);
					try {
						user = daoManager.getUserDAO().update(user);
						if (user != null) {
							users.add(user);
							userResponse.setUsers(users);
							userResponse.setMessage("User removed successfully");
							userResponse.setStatus(true);
						}
					} catch (Exception e) {
						e.printStackTrace();
						userResponse.setUsers(users);
						userResponse.setMessage(e.getMessage());
						userResponse.setStatus(false);
					}

				} else {
					userResponse.setUsers(users);
					userResponse.setMessage("User not found");
					userResponse.setStatus(false);
				}
			} catch (Exception e) {
				e.printStackTrace();
				userResponse.setUsers(users);
				userResponse.setMessage(e.getMessage());
				userResponse.setStatus(false);
			}

		}
		return userResponse;
	}

}
