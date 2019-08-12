/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.schema;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Unit tests for {@link MatchingRuleUse}.
 *
 * @author  Middleware Services
 */
public class MatchingRuleUseTest
{


  /**
   * Test data for matching rule use.
   *
   * @return  matching rule use and string definition
   */
  @DataProvider(name = "definitions")
  public Object[][] createDefinitions()
  {
    return
      new Object[][] {
        new Object[] {
          new MatchingRuleUse("1.2.840.113556.1.4.804", null, null, false, null, null),
          "( 1.2.840.113556.1.4.804 )",
        },
        new Object[] {
          new MatchingRuleUse("1.2.840.113556.1.4.804", new String[] {"integerBitOrMatch"}, null, false, null, null),
          "( 1.2.840.113556.1.4.804 NAME 'integerBitOrMatch' )",
        },
        new Object[] {
          new MatchingRuleUse(
            "1.2.840.113556.1.4.804",
            new String[] {"integerBitOrMatch"},
            null,
            false,
            new String[] {
              "supportedLDAPVersion",
              "entryTtl",
              "uidNumber",
              "gidNumber",
              "olcConcurrency",
              "olcConnMaxPending",
              "olcConnMaxPendingAuth",
              "olcIdleTimeout",
              "olcIndexSubstrIfMinLen",
              "olcIndexSubstrIfMaxLen",
              "olcIndexSubstrAnyLen",
              "olcIndexSubstrAnyStep",
              "olcIndexIntLen",
              "olcListenerThreads",
              "olcLocalSSF",
              "olcMaxDerefDepth",
              "olcReplicationInterval",
              "olcSockbufMaxIncoming",
              "olcSockbufMaxIncomingAuth",
              "olcThreads",
              "olcToolThreads",
              "olcWriteTimeout",
              "olcDbCacheFree",
              "olcDbCacheSize",
              "olcDbDNcacheSize",
              "olcDbIDLcacheSize",
              "olcDbSearchStack",
              "olcDbShmKey",
              "olcDbMaxReaders",
              "olcDbMaxSize",
              "mailPreferenceOption",
            },
            null),
          "( 1.2.840.113556.1.4.804 NAME 'integerBitOrMatch' APPLIES ( supportedLDAPVersion $ entryTtl $ uidNumber $ " +
            "gidNumber $ olcConcurrency $ olcConnMaxPending $ olcConnMaxPendingAuth $ olcIdleTimeout $ " +
            "olcIndexSubstrIfMinLen $ olcIndexSubstrIfMaxLen $ olcIndexSubstrAnyLen $ olcIndexSubstrAnyStep $ " +
            "olcIndexIntLen $ olcListenerThreads $ olcLocalSSF $ olcMaxDerefDepth $ olcReplicationInterval $ " +
            "olcSockbufMaxIncoming $ olcSockbufMaxIncomingAuth $ olcThreads $ olcToolThreads $ olcWriteTimeout $ " +
            "olcDbCacheFree $ olcDbCacheSize $ olcDbDNcacheSize $ olcDbIDLcacheSize $ olcDbSearchStack $ " +
            "olcDbShmKey $ olcDbMaxReaders $ olcDbMaxSize $ mailPreferenceOption ) )",
        },
      };
  }


  /**
   * @param  matchingRule  to compare
   * @param  definition  to parse
   *
   * @throws  Exception  On test failure.
   */
  @Test(groups = "schema", dataProvider = "definitions")
  public void parse(final MatchingRuleUse matchingRule, final String definition)
    throws Exception
  {
    final MatchingRuleUse parsed = MatchingRuleUse.parse(definition);
    Assert.assertEquals(matchingRule, parsed);
    Assert.assertEquals(definition, parsed.format());
    Assert.assertEquals(matchingRule.format(), parsed.format());
  }
}
