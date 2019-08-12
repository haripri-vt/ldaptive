/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.ad.transcode;

import java.nio.charset.StandardCharsets;
import org.ldaptive.transcode.AbstractBinaryValueTranscoder;

/**
 * Decodes and encodes an active directory unicodePwd value for use in an ldap attribute value.
 *
 * @author  Middleware Services
 */
public class UnicodePwdValueTranscoder extends AbstractBinaryValueTranscoder<String>
{


  @Override
  public String decodeBinaryValue(final byte[] value)
  {
    final String pwd = new String(value, StandardCharsets.UTF_16LE);
    if (pwd.length() < 2) {
      throw new IllegalArgumentException("unicodePwd must be at least 2 characters long");
    }
    return pwd.substring(1, pwd.length() - 1);
  }


  @Override
  public byte[] encodeBinaryValue(final String value)
  {
    if (value == null) {
      throw new IllegalArgumentException("Cannot encode null value");
    }

    final String pwd = String.format("\"%s\"", value);
    return pwd.getBytes(StandardCharsets.UTF_16LE);
  }


  @Override
  public Class<String> getType()
  {
    return String.class;
  }
}
