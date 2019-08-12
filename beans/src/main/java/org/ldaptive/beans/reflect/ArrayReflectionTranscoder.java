/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.beans.reflect;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Reflection transcoder which expects to operate on collections containing an array of values.
 *
 * @author  Middleware Services
 */
public class ArrayReflectionTranscoder implements ReflectionTranscoder
{

  /** Underlying value transcoder. */
  private final SingleValueReflectionTranscoder<?> valueTranscoder;

  /** Type of array element for this transcoder. */
  private final Class<?> type;


  /**
   * Creates a new array reflection transcoder.
   *
   * @param  transcoder  to operate on individual array elements
   */
  public ArrayReflectionTranscoder(final SingleValueReflectionTranscoder<?> transcoder)
  {
    valueTranscoder = transcoder;
    type = Array.newInstance(valueTranscoder.getType(), 0).getClass();
  }


  @Override
  public Object decodeStringValues(final Collection<String> values)
  {
    final Object decoded = Array.newInstance(valueTranscoder.getType(), values.size());
    final Iterator<String> iter = values.iterator();
    for (int i = 0; i < values.size(); i++) {
      final List<String> l = new ArrayList<>(1);
      l.add(iter.next());
      Array.set(decoded, i, valueTranscoder.decodeStringValues(l));
    }
    return decoded;
  }


  @Override
  public Object decodeBinaryValues(final Collection<byte[]> values)
  {
    final Object decoded = Array.newInstance(valueTranscoder.getType(), values.size());
    final Iterator<byte[]> iter = values.iterator();
    for (int i = 0; i < values.size(); i++) {
      final List<byte[]> l = new ArrayList<>(1);
      l.add(iter.next());
      Array.set(decoded, i, valueTranscoder.decodeBinaryValues(l));
    }
    return decoded;
  }


  @Override
  public Collection<String> encodeStringValues(final Object values)
  {
    final List<String> encoded = new ArrayList<>();
    if (values instanceof Object[]) {
      for (Object o : (Object[]) values) {
        encoded.addAll(valueTranscoder.encodeStringValues(o));
      }
    } else {
      if (values instanceof boolean[]) {
        for (boolean o : (boolean[]) values) {
          encoded.addAll(valueTranscoder.encodeStringValues(o));
        }
      } else if (values instanceof double[]) {
        for (double o : (double[]) values) {
          encoded.addAll(valueTranscoder.encodeStringValues(o));
        }
      } else if (values instanceof float[]) {
        for (float o : (float[]) values) {
          encoded.addAll(valueTranscoder.encodeStringValues(o));
        }
      } else if (values instanceof int[]) {
        for (int o : (int[]) values) {
          encoded.addAll(valueTranscoder.encodeStringValues(o));
        }
      } else if (values instanceof long[]) {
        for (long o : (long[]) values) {
          encoded.addAll(valueTranscoder.encodeStringValues(o));
        }
      } else if (values instanceof short[]) {
        for (short o : (short[]) values) {
          encoded.addAll(valueTranscoder.encodeStringValues(o));
        }
      } else {
        throw new IllegalArgumentException("Unsupported array type: " + values);
      }
    }
    return encoded;
  }


  @Override
  public Collection<byte[]> encodeBinaryValues(final Object values)
  {
    final List<byte[]> encoded = new ArrayList<>();
    if (values instanceof Object[]) {
      for (Object o : (Object[]) values) {
        encoded.addAll(valueTranscoder.encodeBinaryValues(o));
      }
    } else {
      if (values instanceof boolean[]) {
        for (boolean o : (boolean[]) values) {
          encoded.addAll(valueTranscoder.encodeBinaryValues(o));
        }
      } else if (values instanceof double[]) {
        for (double o : (double[]) values) {
          encoded.addAll(valueTranscoder.encodeBinaryValues(o));
        }
      } else if (values instanceof float[]) {
        for (float o : (float[]) values) {
          encoded.addAll(valueTranscoder.encodeBinaryValues(o));
        }
      } else if (values instanceof int[]) {
        for (int o : (int[]) values) {
          encoded.addAll(valueTranscoder.encodeBinaryValues(o));
        }
      } else if (values instanceof long[]) {
        for (long o : (long[]) values) {
          encoded.addAll(valueTranscoder.encodeBinaryValues(o));
        }
      } else if (values instanceof short[]) {
        for (short o : (short[]) values) {
          encoded.addAll(valueTranscoder.encodeBinaryValues(o));
        }
      } else {
        throw new IllegalArgumentException("Unsupported array type: " + values);
      }
    }
    return encoded;
  }


  @Override
  public Class<?> getType()
  {
    return type;
  }


  @Override
  public boolean supports(final Class<?> t)
  {
    return getType().equals(t);
  }


  @Override
  public String toString()
  {
    return new StringBuilder("[").append(
      getClass().getName()).append("@").append(hashCode()).append("::")
      .append("valueTranscoder=").append(valueTranscoder).append("]").toString();
  }
}
