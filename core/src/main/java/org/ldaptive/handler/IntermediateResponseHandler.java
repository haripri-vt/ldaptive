/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.handler;

import org.ldaptive.Connection;
import org.ldaptive.LdapException;
import org.ldaptive.Request;
import org.ldaptive.intermediate.IntermediateResponse;

/**
 * Provides handling of an ldap intermediate response.
 *
 * @author  Middleware Services
 * @version  $Revision: 2885 $ $Date: 2014-02-05 16:28:49 -0500 (Wed, 05 Feb 2014) $
 */
public interface IntermediateResponseHandler
  extends Handler<Request, IntermediateResponse>
{


  /** {@inheritDoc} */
  @Override
  HandlerResult<IntermediateResponse> handle(
    Connection conn,
    Request request,
    IntermediateResponse response)
    throws LdapException;
}
