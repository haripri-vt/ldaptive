/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.schema.transcode;

import java.text.ParseException;
import org.ldaptive.schema.NameForm;

/**
 * Decodes and encodes a name form for use in an ldap attribute value.
 *
 * @author  Middleware Services
 */
public class NameFormValueTranscoder extends AbstractSchemaElementValueTranscoder<NameForm>
{


  @Override
  public NameForm decodeStringValue(final String value)
  {
    try {
      return NameForm.parse(value);
    } catch (ParseException e) {
      throw new IllegalArgumentException("Could not transcode name form", e);
    }
  }


  @Override
  public Class<NameForm> getType()
  {
    return NameForm.class;
  }
}
