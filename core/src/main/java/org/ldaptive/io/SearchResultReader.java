/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.io;

import java.io.IOException;
import org.ldaptive.SearchResponse;

/**
 * Interface for reading ldap search results.
 *
 * @author  Middleware Services
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
  SearchResponse read()
    throws IOException;
}
