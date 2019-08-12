/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.control;

import org.ldaptive.ResultCode;
import org.ldaptive.asn1.DERBuffer;
import org.ldaptive.asn1.DefaultDERBuffer;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Unit test for {@link SortResponseControl}.
 *
 * @author  Middleware Services
 */
public class SortResponseControlTest
{


  /**
   * Sort response control test data.
   *
   * @return  response test data
   */
  @DataProvider(name = "response")
  public Object[][] createData()
  {
    return
      new Object[][] {
        // result code success
        new Object[] {
          new DefaultDERBuffer(new byte[] {0x30, 0x03, 0x0A, 0x01, 0x00}),
          new SortResponseControl(ResultCode.SUCCESS, true),
        },
      };
  }


  /**
   * @param  berValue  to encode.
   * @param  expected  sort response control to test.
   *
   * @throws  Exception  On test failure.
   */
  @Test(groups = "control", dataProvider = "response")
  public void decode(final DERBuffer berValue, final SortResponseControl expected)
    throws Exception
  {
    final SortResponseControl actual = new SortResponseControl(expected.getCriticality());
    actual.decode(berValue);
    Assert.assertEquals(actual, expected);
  }
}
