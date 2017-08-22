package com.ez.portal.core.util.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import com.ez.portal.core.rest.manager.PortalSessionServiceManager;
import com.ez.portal.shard.util.EZShardUtil;

/**
 * @author azaz.akhtar
 *
 */
public class AuthValidatorInterceptor extends AbstractPhaseInterceptor<Message> {

	/**
	 * 
	 */
	Map<String, Object> headers = null;

	/**
	 * 
	 */
	private EZShardUtil ezShardUtil;

	private PortalSessionServiceManager portalSessionServiceManager;

	public AuthValidatorInterceptor() {
		super(Phase.RECEIVE);
	}

	/**
	 * @return
	 */
	public EZShardUtil getEzShardUtil() {
		return ezShardUtil;
	}

	/**
	 * @param ezShardUtil
	 */
	public void setEzShardUtil(EZShardUtil ezShardUtil) {
		this.ezShardUtil = ezShardUtil;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handleMessage(Message message) throws Fault {
		List<String> authTokens = null;
		headers = (Map<String, Object>) message.get(Message.PROTOCOL_HEADERS);
		Boolean isValidPortalSession = false;
		String portalSessionToken = null;
		if (headers != null) {
			try {
				authTokens = (List<String>) headers.get("AUTH-TOKEN");
				if (authTokens != null) {
					portalSessionToken = authTokens.get(0);
					isValidPortalSession = portalSessionServiceManager.checkPortalSession(portalSessionToken);
					if (!isValidPortalSession) {
						abortRequest(message);
					}
				} else {
					ezShardUtil.initSessionFactory();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param message
	 */
	private void abortRequest(Message message) {
		HttpServletResponse response = (HttpServletResponse) message.getExchange().getInMessage()
				.get(AbstractHTTPDestination.HTTP_RESPONSE);
		response.setStatus(201);
		try {
			response.getOutputStream().write("{\"status\": false, \"message\": \"Invalid session\"}".getBytes());
			response.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		message.getInterceptorChain().abort();
	}

	/**
	 * @return the portalSessionServiceManager
	 */
	public PortalSessionServiceManager getPortalSessionServiceManager() {
		return portalSessionServiceManager;
	}

	/**
	 * @param portalSessionServiceManager
	 */
	public void setPortalSessionServiceManager(PortalSessionServiceManager portalSessionServiceManager) {
		this.portalSessionServiceManager = portalSessionServiceManager;
	}

}