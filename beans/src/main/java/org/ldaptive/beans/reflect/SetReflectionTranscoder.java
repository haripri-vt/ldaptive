/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.beans.reflect;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Reflection transcoder for an object that implements {@link Set}.
 *
 * @author  Middleware Services
 * @version  $Revision: 2917 $ $Date: 2014-03-21 16:06:36 -0400 (Fri, 21 Mar 2014) $
 */
public class SetReflectionTranscoder
  extends AbstractCollectionReflectionTranscoder
{


  /**
   * Creates a new set reflection transcoder.
   *
   * @param  c  class that is a set
   * @param  transcoder  to operate on elements of the set
   */
  public SetReflectionTranscoder(
    final Class<?> c,
    final SingleValueReflectionTranscoder<?> transcoder)
  {
    super(c, transcoder);
  }


  /**
   * Creates a new set reflection transcoder.
   *
   * @param  c  class that is a set
   * @param  transcoder  to operate on elements of the set
   */
  public SetReflectionTranscoder(
    final Class<?> c,
    final ArrayReflectionTranscoder transcoder)
  {
    super(c, transcoder);
  }


  /** {@inheritDoc} */
  @Override
  protected <T> Collection<T> createCollection(final Class<T> clazz)
  {
    final Class<?> type = getType();
    Set<T> s;
    if (LinkedHashSet.class.isAssignableFrom(type)) {
      s = new LinkedHashSet<>();
    } else if (TreeSet.class.isAssignableFrom(type)) {
      s = new TreeSet<>();
    } else {
      s = new HashSet<>();
    }
    return s;
  }
}
