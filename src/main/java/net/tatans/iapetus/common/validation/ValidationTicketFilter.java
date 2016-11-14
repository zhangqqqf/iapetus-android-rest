package net.tatans.iapetus.common.validation;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HostnameVerifier;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.proxy.AbstractEncryptedProxyGrantingTicketStorageImpl;
import org.jasig.cas.client.proxy.Cas20ProxyRetriever;
import org.jasig.cas.client.proxy.CleanUpTimerTask;
import org.jasig.cas.client.proxy.ProxyGrantingTicketStorage;
import org.jasig.cas.client.proxy.ProxyGrantingTicketStorageImpl;
import org.jasig.cas.client.util.AbstractConfigurationFilter;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.util.ReflectUtils;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.Cas20ProxyTicketValidator;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;

public class ValidationTicketFilter extends	AbstractConfigurationFilter {
	 private static final String[] RESERVED_INIT_PARAMS = new String[] {"proxyGrantingTicketStorageClass", "proxyReceptorUrl", "acceptAnyProxy", "allowedProxyChains", "casServerUrlPrefix", "proxyCallbackUrl", "renew", "exceptionOnValidationFailure", "redirectAfterValidation", "useSession", "serverName", "service", "artifactParameterName", "serviceParameterName", "encodeServiceUrl", "millisBetweenCleanUps", "hostnameVerifier", "encoding", "config"};

	    private static final int DEFAULT_MILLIS_BETWEEN_CLEANUPS = 60 * 1000;

	    /**
	     * The URL to send to the CAS server as the URL that will process proxying requests on the CAS client. 
	     */
	    private String proxyReceptorUrl;

	    private Timer timer;

	    private TimerTask timerTask;

	    private int millisBetweenCleanUps;
	    /** The TicketValidator we will use to validate tickets. */
	    private TicketValidator ticketValidator;

	    /**
	     * Specify whether the filter should redirect the user agent after a
	     * successful validation to remove the ticket parameter from the query
	     * string.
	     */
	    private boolean redirectAfterValidation = false;

	    /** Determines whether an exception is thrown when there is a ticket validation failure. */
	    private boolean exceptionOnValidationFailure = true;

	    private boolean useSession = true;

	    /** Represents the constant for where the assertion will be located in memory. */
	    public static final String CONST_CAS_ASSERTION = "_const_cas_assertion_";

	    /** Instance of commons logging for logging purposes. */
	    protected final Log log = LogFactory.getLog(getClass());

	    /** Defines the parameter to look for for the artifact. */
	    private String artifactParameterName = "ticket";

	    /** Defines the parameter to look for for the service. */
	    private String serviceParameterName = "service";
	    
	    /** Sets where response.encodeUrl should be called on service urls when constructed. */
	    private boolean encodeServiceUrl = true;

	    /**
	     * The name of the server.  Should be in the following format: {protocol}:{hostName}:{port}.
	     * Standard ports can be excluded. */
	    private String serverName;

	    /** The exact url of the service. */
	    private String service;
	    
	    /**
	     * Storage location of ProxyGrantingTickets and Proxy Ticket IOUs.
	     */
	    private ProxyGrantingTicketStorage proxyGrantingTicketStorage = new ProxyGrantingTicketStorageImpl();

	    protected void initInternal(final FilterConfig filterConfig) throws ServletException {
	        setProxyReceptorUrl(getPropertyFromInitParams(filterConfig, "proxyReceptorUrl", null));

	        final String proxyGrantingTicketStorageClass = getPropertyFromInitParams(filterConfig, "proxyGrantingTicketStorageClass", null);

	        if (proxyGrantingTicketStorageClass != null) {
	            this.proxyGrantingTicketStorage = ReflectUtils.newInstance(proxyGrantingTicketStorageClass);

	            if (this.proxyGrantingTicketStorage instanceof AbstractEncryptedProxyGrantingTicketStorageImpl) {
	                final AbstractEncryptedProxyGrantingTicketStorageImpl p = (AbstractEncryptedProxyGrantingTicketStorageImpl) this.proxyGrantingTicketStorage;
	                final String cipherAlgorithm = getPropertyFromInitParams(filterConfig, "cipherAlgorithm", AbstractEncryptedProxyGrantingTicketStorageImpl.DEFAULT_ENCRYPTION_ALGORITHM);
	                final String secretKey = getPropertyFromInitParams(filterConfig, "secretKey", null);

	                p.setCipherAlgorithm(cipherAlgorithm);

	                try {
	                    if (secretKey != null) {
	                        p.setSecretKey(secretKey);
	                    }
	                } catch (final Exception e) {
	                    throw new RuntimeException(e);
	                }
	            }
	        }

	        log.trace("Setting proxyReceptorUrl parameter: " + this.proxyReceptorUrl);
	        this.millisBetweenCleanUps = Integer.parseInt(getPropertyFromInitParams(filterConfig, "millisBetweenCleanUps", Integer.toString(DEFAULT_MILLIS_BETWEEN_CLEANUPS)));
	        setExceptionOnValidationFailure(parseBoolean(getPropertyFromInitParams(filterConfig, "exceptionOnValidationFailure", "true")));
	        log.trace("Setting exceptionOnValidationFailure parameter: " + this.exceptionOnValidationFailure);
	        setRedirectAfterValidation(parseBoolean(getPropertyFromInitParams(filterConfig, "redirectAfterValidation", "true")));
	        log.trace("Setting redirectAfterValidation parameter: " + this.redirectAfterValidation);
	        setUseSession(parseBoolean(getPropertyFromInitParams(filterConfig, "useSession", "true")));
	        log.trace("Setting useSession parameter: " + this.useSession);
	        setTicketValidator(getTicketValidator(filterConfig));
	    }

	    public void init() {
	    	CommonUtils.assertNotNull(this.artifactParameterName, "artifactParameterName cannot be null.");
	        CommonUtils.assertNotNull(this.serviceParameterName, "serviceParameterName cannot be null.");
	        CommonUtils.assertTrue(CommonUtils.isNotEmpty(this.serverName) || CommonUtils.isNotEmpty(this.service), "serverName or service must be set.");
	        CommonUtils.assertTrue(CommonUtils.isBlank(this.serverName) || CommonUtils.isBlank(this.service), "serverName and service cannot both be set.  You MUST ONLY set one.");
	    	CommonUtils.assertNotNull(this.ticketValidator, "ticketValidator cannot be null.");
	        CommonUtils.assertNotNull(this.proxyGrantingTicketStorage, "proxyGrantingTicketStorage cannot be null.");

	        if (this.timer == null) {
	            this.timer = new Timer(true);
	        }

	        if (this.timerTask == null) {
	            this.timerTask = new CleanUpTimerTask(this.proxyGrantingTicketStorage);
	        }
	        this.timer.schedule(this.timerTask, this.millisBetweenCleanUps, this.millisBetweenCleanUps);
	    }

	    /**
	     * Constructs a Cas20ServiceTicketValidator or a Cas20ProxyTicketValidator based on supplied parameters.
	     *
	     * @param filterConfig the Filter Configuration object.
	     * @return a fully constructed TicketValidator.
	     */
	    protected final TicketValidator getTicketValidator(final FilterConfig filterConfig) {
	        final String allowAnyProxy = getPropertyFromInitParams(filterConfig, "acceptAnyProxy", null);
	        final String allowedProxyChains = getPropertyFromInitParams(filterConfig, "allowedProxyChains", null);
	        final String casServerUrlPrefix = getPropertyFromInitParams(filterConfig, "casServerUrlPrefix", null);
	        final Cas20ServiceTicketValidator validator;

	        if (CommonUtils.isNotBlank(allowAnyProxy) || CommonUtils.isNotBlank(allowedProxyChains)) {
	            final Cas20ProxyTicketValidator v = new Cas20ProxyTicketValidator(casServerUrlPrefix);
	            v.setAcceptAnyProxy(parseBoolean(allowAnyProxy));
	            v.setAllowedProxyChains(CommonUtils.createProxyList(allowedProxyChains));
	            validator = v;
	        } else {
	            validator = new Cas20ServiceTicketValidator(casServerUrlPrefix);
	        }
	        validator.setProxyCallbackUrl(getPropertyFromInitParams(filterConfig, "proxyCallbackUrl", null));
	        validator.setProxyGrantingTicketStorage(this.proxyGrantingTicketStorage);
	        validator.setProxyRetriever(new Cas20ProxyRetriever(casServerUrlPrefix, getPropertyFromInitParams(filterConfig, "encoding", null)));
	        validator.setRenew(parseBoolean(getPropertyFromInitParams(filterConfig, "renew", "false")));
	        validator.setEncoding(getPropertyFromInitParams(filterConfig, "encoding", null));

	        final Map<String,String> additionalParameters = new HashMap<String,String>();
	        final List<String> params = Arrays.asList(RESERVED_INIT_PARAMS);

	        for (final Enumeration<?> e = filterConfig.getInitParameterNames(); e.hasMoreElements();) {
	            final String s = (String) e.nextElement();

	            if (!params.contains(s)) {
	                additionalParameters.put(s, filterConfig.getInitParameter(s));
	            }
	        }

	        validator.setCustomParameters(additionalParameters);
	        validator.setHostnameVerifier(getHostnameVerifier(filterConfig));

	        return validator;
	    }

	    public void destroy() {
	        this.timer.cancel();
	    }
	    /**
	     * Gets the configured {@link HostnameVerifier} to use for HTTPS connections
	     * if one is configured for this filter.
	     * @param filterConfig Servlet filter configuration.
	     * @return Instance of specified host name verifier or null if none specified.
	     */
	    protected HostnameVerifier getHostnameVerifier(final FilterConfig filterConfig) {
	        final String className = getPropertyFromInitParams(filterConfig, "hostnameVerifier", null);
	        log.trace("Using hostnameVerifier parameter: " + className);
	        final String config = getPropertyFromInitParams(filterConfig, "hostnameVerifierConfig", null);
	        log.trace("Using hostnameVerifierConfig parameter: " + config);
	        if (className != null) {
	            if (config != null) {
	                return ReflectUtils.newInstance(className, config);
	            } else {
	                return ReflectUtils.newInstance(className);
	            }
	        }
	        return null;
	    }

	    /**
	     * This processes the ProxyReceptor request before the ticket validation code executes.
	     */
	    protected final boolean preFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
	        final HttpServletRequest request = (HttpServletRequest) servletRequest;
	        final HttpServletResponse response = (HttpServletResponse) servletResponse;
	        final String requestUri = request.getRequestURI();

	        if (CommonUtils.isEmpty(this.proxyReceptorUrl) || !requestUri.endsWith(this.proxyReceptorUrl)) {
	            return true;
	        }

	        try {
	            CommonUtils.readAndRespondToProxyReceptorRequest(request, response, this.proxyGrantingTicketStorage);
	        } catch (final RuntimeException e) {
	            log.error(e.getMessage(), e);
	            throw e;
	        }

	        return false;
	    }

	    public final void setProxyReceptorUrl(final String proxyReceptorUrl) {
	        this.proxyReceptorUrl = proxyReceptorUrl;
	    }

	    public void setProxyGrantingTicketStorage(final ProxyGrantingTicketStorage storage) {
	        this.proxyGrantingTicketStorage = storage;
	    }

	    public void setTimer(final Timer timer) {
	        this.timer = timer;
	    }

	    public void setTimerTask(final TimerTask timerTask) {
	        this.timerTask = timerTask;
	    }

	    public void setMillisBetweenCleanUps(final int millisBetweenCleanUps) {
	        this.millisBetweenCleanUps = millisBetweenCleanUps;
	    }
	    public final void setTicketValidator(final TicketValidator ticketValidator) {
	        this.ticketValidator = ticketValidator;
	    }

        public final void setRedirectAfterValidation(final boolean redirectAfterValidation) {
            this.redirectAfterValidation = redirectAfterValidation;
        }

        public final void setExceptionOnValidationFailure(final boolean exceptionOnValidationFailure) {
            this.exceptionOnValidationFailure = exceptionOnValidationFailure;
        }

        public final void setUseSession(final boolean useSession) {
            this.useSession = useSession;
        }
        /**
         * Note that trailing slashes should not be used in the serverName.  As a convenience for this common misconfiguration, we strip them from the provided
         * value.
         *
         * @param serverName the serverName. If this method is called, this should not be null.  This AND service should not be both configured.
         */
        public final void setServerName(final String serverName) {
            if (serverName != null && serverName.endsWith("/")) {
                this.serverName = serverName.substring(0, serverName.length()-1);
                log.info(String.format("Eliminated extra slash from serverName [%s].  It is now [%s]", serverName, this.serverName));
            } else {
                this.serverName = serverName;
            }
        }

        public final void setService(final String service) {
            this.service = service;
        }

        public final void setArtifactParameterName(final String artifactParameterName) {
            this.artifactParameterName = artifactParameterName;
        }

        public final void setServiceParameterName(final String serviceParameterName) {
            this.serviceParameterName = serviceParameterName;
        }
        
        public final void setEncodeServiceUrl(final boolean encodeServiceUrl) {
        	this.encodeServiceUrl = encodeServiceUrl;
        }

        public final String getArtifactParameterName() {
            return this.artifactParameterName;
        }

        public final String getServiceParameterName() {
            return this.serviceParameterName;
        }

		@Override
		public void init(FilterConfig filterConfig) throws ServletException {
			if (!isIgnoreInitConfiguration()) {
	            setServerName(getPropertyFromInitParams(filterConfig, "serverName", null));
	            log.trace("Loading serverName property: " + this.serverName);
	            setService(getPropertyFromInitParams(filterConfig, "service", null));
	            log.trace("Loading service property: " + this.service);
	            setArtifactParameterName(getPropertyFromInitParams(filterConfig, "artifactParameterName", "ticket"));
	            log.trace("Loading artifact parameter name property: " + this.artifactParameterName);
	            setServiceParameterName(getPropertyFromInitParams(filterConfig, "serviceParameterName", "service"));
	            log.trace("Loading serviceParameterName property: " + this.serviceParameterName);
	            setEncodeServiceUrl(parseBoolean(getPropertyFromInitParams(filterConfig, "encodeServiceUrl", "true")));
	            log.trace("Loading encodeServiceUrl property: " + this.encodeServiceUrl);

	            initInternal(filterConfig);
	        }
	        init();
			
		}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

        if (!preFilter(servletRequest, servletResponse, filterChain)) {
            return;
        }

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String ticket = CommonUtils.safeGetParameter(request, getArtifactParameterName());

        if (CommonUtils.isNotBlank(ticket)) {
            if (log.isDebugEnabled()) {
                log.debug("Attempting to validate ticket: " + ticket);
            }

            try {
                final Assertion assertion = this.ticketValidator.validate(ticket, constructServiceUrl(request, response));

                if (log.isDebugEnabled()) {
                    log.debug("Successfully authenticated user: " + assertion.getPrincipal().getName());
                }

                request.setAttribute(CONST_CAS_ASSERTION, assertion);

                if (this.useSession) {
                    request.getSession().setAttribute(CONST_CAS_ASSERTION, assertion);
                }
                onSuccessfulValidation(request, response, assertion);

                if (this.redirectAfterValidation) {
                    log. debug("Redirecting after successful ticket validation.");
//                   response.sendRedirect(constructServiceUrl(request, response));
//                   周焕 请求成功转发
                    filterChain.doFilter(request, response);
                    return;
                }
            } catch (final TicketValidationException e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                log.warn(e, e);

                onFailedValidation(request, response);

                if (this.exceptionOnValidationFailure) {
                    throw new ServletException(e);
                }

                return;
            }
        }

        filterChain.doFilter(request, response);
		
		}
	   protected final String constructServiceUrl(final HttpServletRequest request, final HttpServletResponse response) {
	        return CommonUtils.constructServiceUrl(request, response, this.service, this.serverName, this.artifactParameterName, this.encodeServiceUrl);
	    }
	   protected void onSuccessfulValidation(final HttpServletRequest request, final HttpServletResponse response, final Assertion assertion) {
	        // nothing to do here.                                                                                            
	    }
	   /**
	     * Template method that gets executed if validation fails.  This method is called right after the exception is caught from the ticket validator
	     * but before any of the processing of the exception occurs.
	     *
	     * @param request the HttpServletRequest.
	     * @param response the HttpServletResponse.
	     */
	    protected void onFailedValidation(final HttpServletRequest request, final HttpServletResponse response) {
	        // nothing to do here.
	    }
}
