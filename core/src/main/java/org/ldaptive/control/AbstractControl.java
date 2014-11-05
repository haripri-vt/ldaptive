/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.control;

import org.ldaptive.LdapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for ldap controls.
 *
 * @author  Middleware Services
 * @version  $Revision: 2885 $ $Date: 2014-02-05 16:28:49 -0500 (Wed, 05 Feb 2014) $
 */
public abstract class AbstractControl implements Control
{

  /** Logger for this class. */
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  /** control oid. */
  private final String oid;

  /** is control critical. */
  private final boolean criticality;


  /**
   * Creates a new abstract control.
   *
   * @param  id  OID of this control
   */
  public AbstractControl(final String id)
  {
    oid = id;
    criticality = false;
  }


  /**
   * Creates a new abstract control.
   *
   * @param  id  OID of this control
   * @param  b  whether this control is critical
   */
  public AbstractControl(final String id, final boolean b)
  {
    oid = id;
    criticality = b;
  }


  /** {@inheritDoc} */
  @Override
  public String getOID()
  {
    return oid;
  }


  /** {@inheritDoc} */
  @Override
  public boolean getCriticality()
  {
    return criticality;
  }


  /** {@inheritDoc} */
  @Override
  public boolean equals(final Object o)
  {
    return LdapUtils.areEqual(this, o);
  }


  /**
   * Returns the hash code for this object.
   *
   * @return  hash code
   */
  @Override
  public abstract int hashCode();
}
