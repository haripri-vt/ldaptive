/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.concurrent;

import java.util.concurrent.ExecutorService;
import org.ldaptive.ModifyOperation;
import org.ldaptive.ModifyRequest;
import org.ldaptive.ModifyResponse;

/**
 * Executes an ldap modify operation on a separate thread.
 *
 * @author  Middleware Services
 */
public class ModifyOperationWorker extends AbstractOperationWorker<ModifyRequest, ModifyResponse>
{


  /**
   * Creates a new modify operation worker.
   *
   * @param  op  modify operation to execute
   */
  public ModifyOperationWorker(final ModifyOperation op)
  {
    super(op);
  }


  /**
   * Creates a new modify operation worker.
   *
   * @param  op  modify operation to execute
   * @param  es  executor service
   */
  public ModifyOperationWorker(final ModifyOperation op, final ExecutorService es)
  {
    super(op, es);
  }
}
