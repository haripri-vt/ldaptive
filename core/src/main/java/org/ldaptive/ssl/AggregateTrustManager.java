/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.ssl;

import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.stream.Stream;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Trust manager that delegates to multiple trust managers.
 *
 * @author  Middleware Services
 */
public class AggregateTrustManager extends X509ExtendedTrustManager
{

  /** Enum to define how trust managers should be processed. */
  public enum Strategy {

    /** all trust managers must succeed. */
    ALL,

    /** any trust manager must succeed. */
    ANY
  }

  /** Logger for this class. */
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  /** Trust managers to invoke. */
  private final X509TrustManager[] trustManagers;

  /** Whether to require all trust managers succeed. */
  private final Strategy trustStrategy;


  /**
   * Creates a new aggregate trust manager with the ALL {@link Strategy}.
   *
   * @param  managers  to aggregate
   */
  public AggregateTrustManager(final X509TrustManager... managers)
  {
    this(Strategy.ALL, managers);
  }


  /**
   * Creates a new aggregate trust manager.
   *
   * @param  strategy  for processing trust managers
   * @param  managers  to aggregate
   */
  public AggregateTrustManager(final Strategy strategy, final X509TrustManager... managers)
  {
    if (strategy == null) {
      throw new NullPointerException("Strategy cannot be null");
    }
    trustStrategy = strategy;
    if (managers == null || managers.length == 0) {
      throw new NullPointerException("Trust managers cannot be empty or null");
    }
    trustManagers = managers;
  }


  /**
   * Returns the trust managers that are aggregated.
   *
   * @return  trust managers
   */
  public X509TrustManager[] getTrustManagers()
  {
    return trustManagers;
  }


  /**
   * Returns the trust strategy.
   *
   * @return  trust strategy
   */
  public Strategy getTrustStrategy()
  {
    return trustStrategy;
  }


  @Override
  public void checkClientTrusted(final X509Certificate[] chain, final String authType, final Socket socket)
    throws CertificateException
  {
    CertificateException certEx = null;
    for (X509TrustManager tm : trustManagers) {
      try {
        if (tm instanceof X509ExtendedTrustManager) {
          ((X509ExtendedTrustManager) tm).checkClientTrusted(chain, authType, socket);
        } else {
          tm.checkClientTrusted(chain, authType);
        }
        logger.debug("checkClientTrusted for {} succeeded", tm);
        if (trustStrategy == Strategy.ANY) {
          return;
        }
      } catch (CertificateException e) {
        logger.debug("checkClientTrusted for {} failed", tm);
        if (trustStrategy == Strategy.ALL) {
          throw e;
        }
        if (certEx == null) {
          certEx = e;
        }
      }
    }
    if (certEx != null) {
      throw certEx;
    }
  }


  @Override
  public void checkClientTrusted(final X509Certificate[] chain, final String authType, final SSLEngine engine)
    throws CertificateException
  {
    CertificateException certEx = null;
    for (X509TrustManager tm : trustManagers) {
      try {
        if (tm instanceof X509ExtendedTrustManager) {
          ((X509ExtendedTrustManager) tm).checkClientTrusted(chain, authType, engine);
        } else {
          tm.checkClientTrusted(chain, authType);
        }
        logger.debug("checkClientTrusted for {} succeeded", tm);
        if (trustStrategy == Strategy.ANY) {
          return;
        }
      } catch (CertificateException e) {
        logger.debug("checkClientTrusted for {} failed", tm);
        if (trustStrategy == Strategy.ALL) {
          throw e;
        }
        if (certEx == null) {
          certEx = e;
        }
      }
    }
    if (certEx != null) {
      throw certEx;
    }
  }


  @Override
  public void checkClientTrusted(final X509Certificate[] chain, final String authType)
    throws CertificateException
  {
    CertificateException certEx = null;
    for (X509TrustManager tm : trustManagers) {
      try {
        tm.checkClientTrusted(chain, authType);
        logger.debug("checkClientTrusted for {} succeeded", tm);
        if (trustStrategy == Strategy.ANY) {
          return;
        }
      } catch (CertificateException e) {
        logger.debug("checkClientTrusted for {} failed", tm);
        if (trustStrategy == Strategy.ALL) {
          throw e;
        }
        if (certEx == null) {
          certEx = e;
        }
      }
    }
    if (certEx != null) {
      throw certEx;
    }
  }


  @Override
  public void checkServerTrusted(final X509Certificate[] chain, final String authType, final Socket socket)
    throws CertificateException
  {
    CertificateException certEx = null;
    for (X509TrustManager tm : trustManagers) {
      try {
        if (tm instanceof X509ExtendedTrustManager) {
          ((X509ExtendedTrustManager) tm).checkServerTrusted(chain, authType, socket);
        } else {
          tm.checkServerTrusted(chain, authType);
        }
        logger.debug("checkServerTrusted for {} succeeded", tm);
        if (trustStrategy == Strategy.ANY) {
          return;
        }
      } catch (CertificateException e) {
        logger.debug("checkServerTrusted for {} failed", tm);
        if (trustStrategy == Strategy.ALL) {
          throw e;
        }
        if (certEx == null) {
          certEx = e;
        }
      }
    }
    if (certEx != null) {
      throw certEx;
    }
  }


  @Override
  public void checkServerTrusted(final X509Certificate[] chain, final String authType, final SSLEngine engine)
    throws CertificateException
  {
    CertificateException certEx = null;
    for (X509TrustManager tm : trustManagers) {
      try {
        if (tm instanceof X509ExtendedTrustManager) {
          ((X509ExtendedTrustManager) tm).checkServerTrusted(chain, authType, engine);
        } else {
          tm.checkServerTrusted(chain, authType);
        }
        logger.debug("checkServerTrusted for {} succeeded", tm);
        if (trustStrategy == Strategy.ANY) {
          return;
        }
      } catch (CertificateException e) {
        logger.debug("checkServerTrusted for {} failed", tm);
        if (trustStrategy == Strategy.ALL) {
          throw e;
        }
        if (certEx == null) {
          certEx = e;
        }
      }
    }
    if (certEx != null) {
      throw certEx;
    }
  }


  @Override
  public void checkServerTrusted(final X509Certificate[] chain, final String authType)
    throws CertificateException
  {
    CertificateException certEx = null;
    for (X509TrustManager tm : trustManagers) {
      try {
        tm.checkServerTrusted(chain, authType);
        logger.debug("checkServerTrusted for {} succeeded", tm);
        if (trustStrategy == Strategy.ANY) {
          return;
        }
      } catch (CertificateException e) {
        logger.debug("checkServerTrusted for {} failed", tm);
        if (trustStrategy == Strategy.ALL) {
          throw e;
        }
        if (certEx == null) {
          certEx = e;
        }
      }
    }
    if (certEx != null) {
      throw certEx;
    }
  }


  @Override
  public X509Certificate[] getAcceptedIssuers()
  {
    return Stream.of(trustManagers).map(X509TrustManager::getAcceptedIssuers).toArray(X509Certificate[]::new);
  }


  @Override
  public String toString()
  {
    return new StringBuilder("[").append(
      getClass().getName()).append("@").append(hashCode()).append("::")
      .append("trustManagers=").append(Arrays.toString(trustManagers)).append(", ")
      .append("trustStrategy=").append(trustStrategy).append("]").toString();
  }
}
