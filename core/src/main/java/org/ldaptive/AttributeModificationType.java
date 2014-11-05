/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive;

/**
 * Enum to define the type of attribute modification.
 *
 * @author  Middleware Services
 * @version  $Revision: 2885 $ $Date: 2014-02-05 16:28:49 -0500 (Wed, 05 Feb 2014) $
 */
public enum AttributeModificationType {

  /** add an attribute. */
  ADD,

  /** replace an attribute. */
  REPLACE,

  /** remove an attribute. */
  REMOVE
}
