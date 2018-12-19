/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.asn1;

import java.math.BigInteger;
import java.nio.ByteBuffer;

/**
 * Converts arbitrary-precision integers to and from their DER encoded format.
 *
 * @author  Middleware Services
 */
public class IntegerType extends AbstractDERType implements DEREncoder
{

  /** Integer to encode. */
  private final byte[] derItem;


  /**
   * Creates a new integer type.
   *
   * @param  item  to DER encode
   */
  public IntegerType(final BigInteger item)
  {
    super(UniversalDERTag.INT);
    derItem = item.toByteArray();
  }


  /**
   * Creates a new integer type.
   *
   * @param  item  to DER encode
   */
  public IntegerType(final int item)
  {
    super(UniversalDERTag.INT);
    derItem = BigInteger.valueOf(item).toByteArray();
  }


  /**
   * Creates a new integer type.
   *
   * @param  tag  der tag associated with this type
   * @param  item  to DER encode
   *
   * @throws  IllegalArgumentException  if the der tag is constructed
   */
  public IntegerType(final DERTag tag, final BigInteger item)
  {
    super(tag);
    if (tag.isConstructed()) {
      throw new IllegalArgumentException("DER tag must not be constructed");
    }
    derItem = item.toByteArray();
  }


  /**
   * Creates a new integer type.
   *
   * @param  tag  der tag associated with this type
   * @param  item  to DER encode
   *
   * @throws  IllegalArgumentException  if the der tag is constructed
   */
  public IntegerType(final DERTag tag, final int item)
  {
    super(tag);
    if (tag.isConstructed()) {
      throw new IllegalArgumentException("DER tag must not be constructed");
    }
    derItem = BigInteger.valueOf(item).toByteArray();
  }


  @Override
  public byte[] encode()
  {
    return encode(derItem);
  }


  /**
   * Converts bytes in the buffer to an integer by reading from the current position to the limit, which assumes the
   * bytes of the integer are in big-endian order.
   *
   * @param  encoded  buffer containing DER-encoded data where the buffer is positioned at the start of integer bytes
   *                  and the limit is set beyond the last byte of integer data.
   *
   * @return  decoded bytes as an integer of arbitrary size.
   */
  public static BigInteger decode(final ByteBuffer encoded)
  {
    return new BigInteger(readBuffer(encoded));
  }


  /**
   * Converts bytes in the buffer to an unsigned integer by reading from the current position to the limit, which
   * assumes the bytes of the integer are in big-endian order.
   *
   * @param  encoded  buffer containing DER-encoded data where the buffer is positioned at the start of integer bytes
   *                  and the limit is set beyond the last byte of integer data.
   *
   * @return  decoded bytes as an unsigned integer of arbitrary size.
   */
  public static BigInteger decodeUnsigned(final ByteBuffer encoded)
  {
    return new BigInteger(1, readBuffer(encoded));
  }


  /**
   * Converts bytes in the buffer to an unsigned primitive integer by reading from the current position to the limit,
   * which assumes the bytes of the integer are in big-endian order. This method reads up to 4 bytes from the buffer.
   *
   * @param  encoded  buffer containing DER-encoded data where the buffer is positioned at the start of integer bytes
   *                  and the limit is set beyond the last byte of integer data.
   *
   * @return  decoded bytes as an unsigned integer.
   *
   * @throws  IllegalArgumentException  if the buffer contains more than 4 bytes
   */
  public static int decodeUnsignedPrimitive(final ByteBuffer encoded)
  {
    // CheckStyle:MagicNumber OFF
    final byte[] bytes = readBuffer(encoded);
    if (bytes.length > 4) {
      throw new IllegalArgumentException("Buffer length must be <= 4 bytes");
    }
    int i = 0;
    for (byte b : bytes) {
      i <<= 8;
      i |= b & 0xFF;
    }
    return i;
    // CheckStyle:MagicNumber ON
  }


  /**
   * Converts the supplied big integer to a byte array.
   *
   * @param  i  to convert
   *
   * @return  byte array
   */
  public static byte[] toBytes(final BigInteger i)
  {
    return i.toByteArray();
  }
}
