/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.control;

import org.ldaptive.LdapUtils;
import org.ldaptive.asn1.DERBuffer;
import org.ldaptive.asn1.DefaultDERBuffer;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Unit test for {@link PagedResultsControl}.
 *
 * @author  Middleware Services
 */
public class PagedResultsControlTest
{


  /**
   * Paged results control test data.
   *
   * @return  response test data
   */
  @DataProvider(name = "request-response")
  public Object[][] createData()
  {
    return
      new Object[][] {
        // request size 0, no cookie
        new Object[] {
          new DefaultDERBuffer(new byte[] {0x30, 0x05, 0x02, 0x01, 0x00, 0x04, 0x00}),
          new PagedResultsControl(0, null, true),
        },
        // request size 0, cookie
        new Object[] {
          new DefaultDERBuffer(
            new byte[] {
              0x30, 0x0D, 0x02, 0x01, 0x00, 0x04, 0x08, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
              (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}),
          new PagedResultsControl(
            0,
            new byte[] {
              (byte) 0xff,
              (byte) 0xff,
              (byte) 0xff,
              (byte) 0xff,
              (byte) 0xff,
              (byte) 0xff,
              (byte) 0xff,
              (byte) 0xff,
            },
            true),
        },
        // request size 1, no cookie
        new Object[] {
          new DefaultDERBuffer(new byte[] {0x30, 0x05, 0x02, 0x01, 0x01, 0x04, 0x00}),
          new PagedResultsControl(1, null, true),
        },
        // request size 1, cookie
        new Object[] {
          new DefaultDERBuffer(
            new byte[] {
              0x30, 0x0D, 0x02, 0x01, 0x01, 0x04, 0x08, (byte) 0xEF, 0x5C, 0x15, 0x00, 0x00, 0x00, 0x00, 0x00}),
          new PagedResultsControl(
            1,
            new byte[] {
              (byte) 0xef,
              (byte) 0x5c,
              (byte) 0x15,
              (byte) 0x00,
              (byte) 0x00,
              (byte) 0x00,
              (byte) 0x00,
              (byte) 0x00,
            },
            true),
        },
        // request size 20, no cookie
        new Object[] {
          new DefaultDERBuffer(new byte[] {0x30, 0x05, 0x02, 0x01, 0x14, 0x04, 0x00}),
          new PagedResultsControl(20, null, true),
        },
        // request size 20, cookie
        new Object[] {
          new DefaultDERBuffer(
            new byte[] {
              0x30, 0x0D, 0x02, 0x01, 0x14, 0x04, 0x08, (byte) 0xA7, (byte) 0xC7, 0x18, 0x00, 0x00, 0x00, 0x00, 0x00}),
          new PagedResultsControl(
            20,
            new byte[] {
              (byte) 0xa7,
              (byte) 0xc7,
              (byte) 0x18,
              (byte) 0x00,
              (byte) 0x00,
              (byte) 0x00,
              (byte) 0x00,
              (byte) 0x00,
            },
            true),
        },
        // request size 0, 388 byte length cookie
        // both the sequence and octet string use long form length
        new Object[] {
          new DefaultDERBuffer(
            LdapUtils.base64Decode(
              "MIQAAAGNAgEABIQAAAGEAQAAAIQBAAD/////7E9EoNm4ztLWBSblnuyp1iZhZ/XM" +
              "5IJk6DcibuopiDZU4AofIdRQT4hfyECUBna7AAAAAAEAAAAAAAAAfQ8AABwAAAAF" +
              "AAAAAgAAAAAAAAAAAAAABAAAAAUAAQCpDgAA/QYAAP0GAAAAAAAAtBxsfUgmpECD" +
              "Z+XSv7zT1gAAAAAEAAAAAQAAAAAAAAAAAAAAAQAAAAQAAAABAAAAAAAAAAAAAAAA" +
              "AAAAAAAAAAAAAAAAAAAAAAAAAAEAAAD/////AeNCb8/LArdUnvG/1PsdLkQipKip" +
              "SVvzz/kAtrc1mcsAAAAAfwIAAAD7BgAACfwGAAD9BgAACakOAAB9DwAACH+AAA99" +
              "AAAAAAAAAAAAAAD/////AAAAAAAAAAD/////DwAAABwAAAAdAAAAQW5jZXN0b3Jz" +
              "X2luZGV4fwIAAAD7BgAACfwGAAD9BgAACakOAAAAAAAABH8CAAAA+wYAAAn8BgAA" +
              "/QYAAAmpDgAA////////AAAAAA==")),
          new PagedResultsControl(
            0,
            new byte[] {
              (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x84,
              (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0xff, (byte) 0xff,
              (byte) 0xff, (byte) 0xff, (byte) 0xec, (byte) 0x4f, (byte) 0x44,
              (byte) 0xa0, (byte) 0xd9, (byte) 0xb8, (byte) 0xce, (byte) 0xd2,
              (byte) 0xd6, (byte) 0x05, (byte) 0x26, (byte) 0xe5, (byte) 0x9e,
              (byte) 0xec, (byte) 0xa9, (byte) 0xd6, (byte) 0x26, (byte) 0x61,
              (byte) 0x67, (byte) 0xf5, (byte) 0xcc, (byte) 0xe4, (byte) 0x82,
              (byte) 0x64, (byte) 0xe8, (byte) 0x37, (byte) 0x22, (byte) 0x6e,
              (byte) 0xea, (byte) 0x29, (byte) 0x88, (byte) 0x36, (byte) 0x54,
              (byte) 0xe0, (byte) 0x0a, (byte) 0x1f, (byte) 0x21, (byte) 0xd4,
              (byte) 0x50, (byte) 0x4f, (byte) 0x88, (byte) 0x5f, (byte) 0xc8,
              (byte) 0x40, (byte) 0x94, (byte) 0x06, (byte) 0x76, (byte) 0xbb,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x00, (byte) 0x00, (byte) 0x7d, (byte) 0x0f, (byte) 0x00,
              (byte) 0x00, (byte) 0x1c, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x05, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x02,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x05, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0xa9,
              (byte) 0x0e, (byte) 0x00, (byte) 0x00, (byte) 0xfd, (byte) 0x06,
              (byte) 0x00, (byte) 0x00, (byte) 0xfd, (byte) 0x06, (byte) 0x00,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0xb4, (byte) 0x1c, (byte) 0x6c, (byte) 0x7d, (byte) 0x48,
              (byte) 0x26, (byte) 0xa4, (byte) 0x40, (byte) 0x83, (byte) 0x67,
              (byte) 0xe5, (byte) 0xd2, (byte) 0xbf, (byte) 0xbc, (byte) 0xd3,
              (byte) 0xd6, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x01,
              (byte) 0xe3, (byte) 0x42, (byte) 0x6f, (byte) 0xcf, (byte) 0xcb,
              (byte) 0x02, (byte) 0xb7, (byte) 0x54, (byte) 0x9e, (byte) 0xf1,
              (byte) 0xbf, (byte) 0xd4, (byte) 0xfb, (byte) 0x1d, (byte) 0x2e,
              (byte) 0x44, (byte) 0x22, (byte) 0xa4, (byte) 0xa8, (byte) 0xa9,
              (byte) 0x49, (byte) 0x5b, (byte) 0xf3, (byte) 0xcf, (byte) 0xf9,
              (byte) 0x00, (byte) 0xb6, (byte) 0xb7, (byte) 0x35, (byte) 0x99,
              (byte) 0xcb, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x7f, (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0xfb, (byte) 0x06, (byte) 0x00, (byte) 0x00, (byte) 0x09,
              (byte) 0xfc, (byte) 0x06, (byte) 0x00, (byte) 0x00, (byte) 0xfd,
              (byte) 0x06, (byte) 0x00, (byte) 0x00, (byte) 0x09, (byte) 0xa9,
              (byte) 0x0e, (byte) 0x00, (byte) 0x00, (byte) 0x7d, (byte) 0x0f,
              (byte) 0x00, (byte) 0x00, (byte) 0x08, (byte) 0x7f, (byte) 0x80,
              (byte) 0x00, (byte) 0x0f, (byte) 0x7d, (byte) 0x00, (byte) 0x00,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff,
              (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
              (byte) 0x0f, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x1c,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x1d, (byte) 0x00,
              (byte) 0x00, (byte) 0x00, (byte) 0x41, (byte) 0x6e, (byte) 0x63,
              (byte) 0x65, (byte) 0x73, (byte) 0x74, (byte) 0x6f, (byte) 0x72,
              (byte) 0x73, (byte) 0x5f, (byte) 0x69, (byte) 0x6e, (byte) 0x64,
              (byte) 0x65, (byte) 0x78, (byte) 0x7f, (byte) 0x02, (byte) 0x00,
              (byte) 0x00, (byte) 0x00, (byte) 0xfb, (byte) 0x06, (byte) 0x00,
              (byte) 0x00, (byte) 0x09, (byte) 0xfc, (byte) 0x06, (byte) 0x00,
              (byte) 0x00, (byte) 0xfd, (byte) 0x06, (byte) 0x00, (byte) 0x00,
              (byte) 0x09, (byte) 0xa9, (byte) 0x0e, (byte) 0x00, (byte) 0x00,
              (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04,
              (byte) 0x7f, (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x00,
              (byte) 0xfb, (byte) 0x06, (byte) 0x00, (byte) 0x00, (byte) 0x09,
              (byte) 0xfc, (byte) 0x06, (byte) 0x00, (byte) 0x00, (byte) 0xfd,
              (byte) 0x06, (byte) 0x00, (byte) 0x00, (byte) 0x09, (byte) 0xa9,
              (byte) 0x0e, (byte) 0x00, (byte) 0x00, (byte) 0xff, (byte) 0xff,
              (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00,
              (byte) 0x00, (byte) 0x00, (byte) 0x00,
            },
            true),
        },
      };
  }


  /**
   * @param  berValue  to encode.
   * @param  expected  paged results control to test.
   *
   * @throws  Exception  On test failure.
   */
  @Test(groups = "control", dataProvider = "request-response")
  public void encode(final DERBuffer berValue, final PagedResultsControl expected)
    throws Exception
  {
    Assert.assertEquals(expected.encode(), berValue.getRemainingBytes());
  }


  /**
   * @param  berValue  to decode.
   * @param  expected  paged results control to test.
   *
   * @throws  Exception  On test failure.
   */
  @Test(groups = "control", dataProvider = "request-response")
  public void decode(final DERBuffer berValue, final PagedResultsControl expected)
    throws Exception
  {
    final PagedResultsControl actual = new PagedResultsControl(expected.getCriticality());
    actual.decode(berValue);
    Assert.assertEquals(actual, expected);
  }
}
