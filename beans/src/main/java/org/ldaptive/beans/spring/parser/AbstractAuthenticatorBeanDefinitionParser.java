/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.beans.spring.parser;

import org.ldaptive.auth.Authenticator;
import org.ldaptive.auth.SimpleBindAuthenticationHandler;
import org.ldaptive.auth.ext.ActiveDirectoryAuthenticationResponseHandler;
import org.ldaptive.auth.ext.EDirectoryAuthenticationResponseHandler;
import org.ldaptive.auth.ext.FreeIPAAuthenticationResponseHandler;
import org.ldaptive.auth.ext.PasswordExpirationAuthenticationResponseHandler;
import org.ldaptive.auth.ext.PasswordPolicyAuthenticationResponseHandler;
import org.ldaptive.control.PasswordPolicyControl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.w3c.dom.Element;

/**
 * Common implementation for all authenticators.
 *
 * @author Middleware Services
 */
public abstract class AbstractAuthenticatorBeanDefinitionParser extends AbstractConnectionFactoryBeanDefinitionParser
{


  @Override
  protected Class<?> getBeanClass(final Element element)
  {
    return Authenticator.class;
  }


  /**
   * Creates a pooled authentication handler for performing binds.
   *
   * @param  element  containing configuration
   *
   * @return  pooled bind authentication handler bean definition builder
   */
  protected BeanDefinitionBuilder parseAuthHandler(final Element element)
  {
    final BeanDefinitionBuilder authHandler;
    if (element.getAttribute("disablePooling") != null && Boolean.valueOf(element.getAttribute("disablePooling"))) {
      authHandler = BeanDefinitionBuilder.genericBeanDefinition(SimpleBindAuthenticationHandler.class);
      authHandler.addPropertyValue(
        "connectionFactory",
        parseDefaultConnectionFactory(null, element, false).getBeanDefinition());
    } else {
      String name = "bind-pool";
      if (element.hasAttribute("id")) {
        name = element.getAttribute("id") + "-bind-pool";
      }
      authHandler = BeanDefinitionBuilder.genericBeanDefinition(SimpleBindAuthenticationHandler.class);
      authHandler.addPropertyValue(
        "connectionFactory",
        parsePooledConnectionFactory(null, name, element, false).getBeanDefinition());
    }
    return authHandler;
  }


  /**
   * Creates an authentication response handler bean definition builder. The supplied authentication handler will be
   * updated with any necessary request controls that the response handler may require.
   *
   * @param  authenticator  bean definition builder for the authenticator
   * @param  authHandler  bean definition builder for the authentication handler
   * @param  element  containing authentication response handler
   *
   * @return  authentication response handler bean definition builder
   */
  protected BeanDefinitionBuilder parseAuthResponseHandler(
    final BeanDefinitionBuilder authenticator,
    final BeanDefinitionBuilder authHandler,
    final Element element)
  {
    BeanDefinitionBuilder responseHandler = null;
    BeanDefinitionBuilder requestControls = null;

    final Element handlerElement = getDirectChild(
      getDirectChild(element, "authentication-response-handler"),
      "password-policy-handler",
      "password-expiration-handler",
      "e-directory-handler",
      "free-ipa-handler",
      "active-directory-handler");
    if (handlerElement != null) {
      switch (handlerElement.getLocalName()) {

      case "password-policy-handler":
        responseHandler = BeanDefinitionBuilder.genericBeanDefinition(
          PasswordPolicyAuthenticationResponseHandler.class);
        requestControls = BeanDefinitionBuilder.genericBeanDefinition(PasswordPolicyControl.class);
        break;

      case "password-expiration-handler":
        responseHandler = BeanDefinitionBuilder.genericBeanDefinition(
          PasswordExpirationAuthenticationResponseHandler.class);
        break;

      case "e-directory-handler":
        responseHandler = BeanDefinitionBuilder.genericBeanDefinition(
          EDirectoryAuthenticationResponseHandler.class);
        if (handlerElement.hasAttribute("warningPeriod")) {
          final BeanDefinitionBuilder period =  BeanDefinitionBuilder.rootBeanDefinition(
            AbstractAuthenticatorBeanDefinitionParser.class,
            "parsePeriod");
          period.addConstructorArgValue(handlerElement.getAttribute("warningPeriod"));
          responseHandler.addPropertyValue("warningPeriod", period.getBeanDefinition());
        }
        authenticator.addPropertyValue("returnAttributes", EDirectoryAuthenticationResponseHandler.ATTRIBUTES);
        break;

      case "free-ipa-handler":
        responseHandler = BeanDefinitionBuilder.genericBeanDefinition(FreeIPAAuthenticationResponseHandler.class);
        if (handlerElement.hasAttribute("expirationPeriod")) {
          final BeanDefinitionBuilder period =  BeanDefinitionBuilder.rootBeanDefinition(
            AbstractAuthenticatorBeanDefinitionParser.class,
            "parsePeriod");
          period.addConstructorArgValue(handlerElement.getAttribute("expirationPeriod"));
          responseHandler.addPropertyValue("expirationPeriod", period.getBeanDefinition());
        }
        if (handlerElement.hasAttribute("warningPeriod")) {
          final BeanDefinitionBuilder period =  BeanDefinitionBuilder.rootBeanDefinition(
            AbstractAuthenticatorBeanDefinitionParser.class,
            "parsePeriod");
          period.addConstructorArgValue(handlerElement.getAttribute("warningPeriod"));
          responseHandler.addPropertyValue("warningPeriod", period.getBeanDefinition());
        }
        if (handlerElement.hasAttribute("maxLoginFailures")) {
          responseHandler.addPropertyValue("maxLoginFailures", handlerElement.getAttribute("maxLoginFailures"));
        }
        authenticator.addPropertyValue("returnAttributes", FreeIPAAuthenticationResponseHandler.ATTRIBUTES);
        break;

      case "active-directory-handler":
        responseHandler = BeanDefinitionBuilder.genericBeanDefinition(
          ActiveDirectoryAuthenticationResponseHandler.class);
        if (handlerElement.hasAttribute("expirationPeriod")) {
          final BeanDefinitionBuilder period =  BeanDefinitionBuilder.rootBeanDefinition(
            AbstractAuthenticatorBeanDefinitionParser.class,
            "parsePeriod");
          period.addConstructorArgValue(handlerElement.getAttribute("expirationPeriod"));
          responseHandler.addPropertyValue("expirationPeriod", period.getBeanDefinition());
        }
        if (handlerElement.hasAttribute("warningPeriod")) {
          final BeanDefinitionBuilder period =  BeanDefinitionBuilder.rootBeanDefinition(
            AbstractAuthenticatorBeanDefinitionParser.class,
            "parsePeriod");
          period.addConstructorArgValue(handlerElement.getAttribute("warningPeriod"));
          responseHandler.addPropertyValue("warningPeriod", period.getBeanDefinition());
        }
        authenticator.addPropertyValue("returnAttributes", ActiveDirectoryAuthenticationResponseHandler.ATTRIBUTES);
        break;

      default:
        throw new IllegalArgumentException("Unknown authentication response handler: " + handlerElement.getLocalName());
      }
    }

    if (requestControls != null) {
      authHandler.addPropertyValue("authenticationControls", requestControls.getBeanDefinition());
    }
    return responseHandler;
  }
}
