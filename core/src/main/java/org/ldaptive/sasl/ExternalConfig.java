/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.sasl;

/**
 * Contains all the configuration data for SASL EXTERNAL authentication.
 *
 * @author  Middleware Services
 */
public class ExternalConfig extends SaslConfig
{


  /** Default constructor. */
  public ExternalConfig()
  {
    setMechanism(Mechanism.EXTERNAL);
  }
}
