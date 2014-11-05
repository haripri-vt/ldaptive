/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.control;

/**
 * Marker interface for ldap response controls.
 *
 * @author  Middleware Services
 * @version  $Revision: 2885 $ $Date: 2014-02-05 16:28:49 -0500 (Wed, 05 Feb 2014) $
 */
public interface ResponseControl extends Control
{


  /**
   * Initializes this response control with the supplied BER encoded data.
   *
   * @param  encoded  BER encoded response control
   */
  void decode(byte[] encoded);
}
