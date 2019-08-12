/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.beans.spring;

import org.ldaptive.beans.spring.parser.ADAuthenticatorBeanDefinitionParser;
import org.ldaptive.beans.spring.parser.AggregateAuthenticatorBeanDefinitionParser;
import org.ldaptive.beans.spring.parser.AggregateSearchOperationBeanDefinitionParser;
import org.ldaptive.beans.spring.parser.AnonSearchAuthenticatorBeanDefinitionParser;
import org.ldaptive.beans.spring.parser.BindSearchAuthenticatorBeanDefinitionParser;
import org.ldaptive.beans.spring.parser.ConnectionConfigBeanDefinitionParser;
import org.ldaptive.beans.spring.parser.ConnectionFactoryBeanDefinitionParser;
import org.ldaptive.beans.spring.parser.DirectAuthenticatorBeanDefinitionParser;
import org.ldaptive.beans.spring.parser.ParallelSearchOperationBeanDefinitionParser;
import org.ldaptive.beans.spring.parser.PooledConnectionFactoryBeanDefinitionParser;
import org.ldaptive.beans.spring.parser.SaslBindSearchAuthenticatorBeanDefinitionParser;
import org.ldaptive.beans.spring.parser.SearchOperationBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Spring namespace handler for ldaptive.
 *
 * @author Middleware Services
 */
public class NamespaceHandler extends NamespaceHandlerSupport
{


  @Override
  public void init()
  {
    registerBeanDefinitionParser("anonymous-search-authenticator", new AnonSearchAuthenticatorBeanDefinitionParser());
    registerBeanDefinitionParser("bind-search-authenticator", new BindSearchAuthenticatorBeanDefinitionParser());
    registerBeanDefinitionParser(
      "sasl-bind-search-authenticator",
      new SaslBindSearchAuthenticatorBeanDefinitionParser());
    registerBeanDefinitionParser("direct-authenticator", new DirectAuthenticatorBeanDefinitionParser());
    registerBeanDefinitionParser("ad-authenticator", new ADAuthenticatorBeanDefinitionParser());
    registerBeanDefinitionParser("aggregate-authenticator", new AggregateAuthenticatorBeanDefinitionParser());
    registerBeanDefinitionParser("pooled-connection-factory", new PooledConnectionFactoryBeanDefinitionParser());
    registerBeanDefinitionParser("connection-factory", new ConnectionFactoryBeanDefinitionParser());
    registerBeanDefinitionParser("connection-config", new ConnectionConfigBeanDefinitionParser());
    registerBeanDefinitionParser("search-operation", new SearchOperationBeanDefinitionParser());
    registerBeanDefinitionParser("parallel-search-operation", new ParallelSearchOperationBeanDefinitionParser());
    registerBeanDefinitionParser("aggregate-search-operation", new AggregateSearchOperationBeanDefinitionParser());
  }
}
