/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.io;

import java.io.IOException;
import org.ldaptive.SearchResult;

/**
 * Interface for reading ldap search results.
 *
 * @author  Middleware Services
 * @version  $Revision: 2885 $ $Date: 2014-02-05 16:28:49 -0500 (Wed, 05 Feb 2014) $
 */
public interface SearchResultReader
{


  /**
   * Reads an ldap result.
   *
   * @return  ldap result
   *
   * @throws  IOException  if an error occurs using the reader
   */
  SearchResult read()
    throws IOException;
}
