/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.schema;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Unit tests for {@link DITContentRule}.
 *
 * @author  Middleware Services
 */
public class DITContentRuleTest
{


  /**
   * Test data for DIT content rule.
   *
   * @return  DIT content rule and string definition
   */
  @DataProvider(name = "definitions")
  public Object[][] createDefinitions()
  {
    return
      new Object[][] {
        new Object[] {
          new DITContentRule("12.16.840.1.113730.3.2.2", null, null, false, null, null, null, null, null),
          "( 12.16.840.1.113730.3.2.2 )",
        },
        new Object[] {
          new DITContentRule(
            "12.16.840.1.113730.3.2.2",
            new String[] {"dcrPerson"},
            null,
            false,
            null,
            null,
            null,
            null,
            null),
          "( 12.16.840.1.113730.3.2.2 NAME 'dcrPerson' )",
        },
        new Object[] {
          new DITContentRule(
            "12.16.840.1.113730.3.2.2",
            new String[] {"dcrPerson"},
            "inetOrgPerson entries may only be members of the uidObject aux class",
            false,
            null,
            null,
            null,
            null,
            null),
          "( 12.16.840.1.113730.3.2.2 NAME 'dcrPerson' DESC 'inetOrgPerson entries may only be members of the " +
            "uidObject aux class' )",
        },
        new Object[] {
          new DITContentRule(
            "12.16.840.1.113730.3.2.2",
            new String[] {"dcrPerson"},
            "inetOrgPerson entries may only be members of the uidObject aux class",
            false,
            new String[] {"1.3.6.1.1.3.1"},
            null,
            null,
            null,
            null),
          "( 12.16.840.1.113730.3.2.2 NAME 'dcrPerson' DESC 'inetOrgPerson entries may only be members of the " +
            "uidObject aux class' AUX 1.3.6.1.1.3.1 )",
        },
      };
  }


  /**
   * @param  contentRule  to compare
   * @param  definition  to parse
   *
   * @throws  Exception  On test failure.
   */
  @Test(groups = "schema", dataProvider = "definitions")
  public void parse(final DITContentRule contentRule, final String definition)
    throws Exception
  {
    final DITContentRule parsed = DITContentRule.parse(definition);
    Assert.assertEquals(contentRule, parsed);
    Assert.assertEquals(definition, parsed.format());
    Assert.assertEquals(contentRule.format(), parsed.format());
  }
}
