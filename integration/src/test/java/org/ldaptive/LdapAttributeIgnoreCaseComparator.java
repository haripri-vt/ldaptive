/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive;

import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import org.ldaptive.transcode.ValueTranscoder;

/**
 * Compares two ldap attributes, ignoring the case of all attribute values.
 *
 * @author  Middleware Services
 */
public class LdapAttributeIgnoreCaseComparator implements Comparator<LdapAttribute>
{

  /** for lower casing values. */
  private static final LowerCaseValueTranscoder TRANSCODER = new LowerCaseValueTranscoder();


  /**
   * Compares two ldap attributes, ignoring the case of their values.
   *
   * @param  a  first attribute for the comparison
   * @param  b  second attribute for the comparison
   *
   * @return  a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater
   *          than the second.
   */
  public int compare(final LdapAttribute a, final LdapAttribute b)
  {
    return lowerCaseAttribute(a).hashCode() - lowerCaseAttribute(b).hashCode();
  }


  /**
   * Returns a new ldap attribute whose values have been lower cased.
   *
   * @param  la  attribute to copy values from
   *
   * @return  ldap attribute with lower cased values
   *
   * @throws  IllegalArgumentException  if a binary attribute is supplied
   */
  public static LdapAttribute lowerCaseAttribute(final LdapAttribute la)
  {
    try {
      final LdapAttribute lowerCase = new LdapAttribute();
      lowerCase.setName(la.getName());
      lowerCase.addStringValues(la.getValues(TRANSCODER.decoder()));
      return lowerCase;
    } catch (UnsupportedOperationException e) {
      throw new IllegalArgumentException("Error lower casing attribute " + la, e);
    }
  }


  /** Decodes and encodes a string by invoking {@link String#toLowerCase()}. */
  private static class LowerCaseValueTranscoder implements ValueTranscoder<String>
  {


    @Override
    public String decodeStringValue(final String value)
    {
      return value.toLowerCase();
    }


    @Override
    public String decodeBinaryValue(final byte[] value)
    {
      return (new String(value, StandardCharsets.UTF_8)).toLowerCase();
    }


    @Override
    public String encodeStringValue(final String value)
    {
      return value.toLowerCase();
    }


    @Override
    public byte[] encodeBinaryValue(final String value)
    {
      return value.toLowerCase().getBytes(StandardCharsets.UTF_8);
    }


    @Override
    public Class<String> getType()
    {
      return String.class;
    }
  }
}
