/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.io;

import java.io.IOException;
import org.ldaptive.SearchResult;

/**
 * Interface for writing ldap search results.
 *
 * @author  Middleware Services
 * @version  $Revision: 2885 $ $Date: 2014-02-05 16:28:49 -0500 (Wed, 05 Feb 2014) $
 */
public interface SearchResultWriter
{


  /**
   * Writes the supplied ldap result.
   *
   * @param  result  ldap result to write
   *
   * @throws  IOException  if an error occurs using the writer
   */
  void write(SearchResult result)
    throws IOException;
}
