/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.ssl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import org.ldaptive.LdapUtils;

/**
 * Provides the properties necessary for creating an SSL context initializer with an X.509 credential reader.
 *
 * @author  Middleware Services
 */
public class X509CredentialConfig implements CredentialConfig
{

  /** hash code seed. */
  private static final int HASH_CODE_SEED = 1009;

  /** Reads X.509 certificates credential. */
  private final X509CertificatesCredentialReader certsReader = new X509CertificatesCredentialReader();

  /** Reads X.509 certificate credential. */
  private final X509CertificateCredentialReader certReader = new X509CertificateCredentialReader();

  /** Reads private key credential. */
  private final PrivateKeyCredentialReader keyReader = new PrivateKeyCredentialReader();

  /** Name of the trust certificates to use for the SSL connection. */
  private String trustCertificates;

  /** Name of the authentication certificate to use for the SSL connection. */
  private String authenticationCertificate;

  /** Name of the key to use for the SSL connection. */
  private String authenticationKey;


  /**
   * Returns the name of the trust certificates to use.
   *
   * @return  trust certificates name
   */
  public String getTrustCertificates()
  {
    return trustCertificates;
  }


  /**
   * Sets the name of the trust certificates to use.
   *
   * @param  name  trust certificates name
   */
  public void setTrustCertificates(final String name)
  {
    trustCertificates = name;
  }


  /**
   * Returns the name of the authentication certificate to use.
   *
   * @return  authentication certificate name
   */
  public String getAuthenticationCertificate()
  {
    return authenticationCertificate;
  }


  /**
   * Sets the name of the authentication certificate to use.
   *
   * @param  name  authentication certificate name
   */
  public void setAuthenticationCertificate(final String name)
  {
    authenticationCertificate = name;
  }


  /**
   * Returns the name of the authentication key to use.
   *
   * @return  authentication key name
   */
  public String getAuthenticationKey()
  {
    return authenticationKey;
  }


  /**
   * Sets the name of the authentication key to use.
   *
   * @param  name  authentication key name
   */
  public void setAuthenticationKey(final String name)
  {
    authenticationKey = name;
  }


  @Override
  public SSLContextInitializer createSSLContextInitializer()
    throws GeneralSecurityException
  {
    final X509SSLContextInitializer sslInit = new X509SSLContextInitializer();
    try {
      if (trustCertificates != null) {
        sslInit.setTrustCertificates(certsReader.read(trustCertificates));
      }
      if (authenticationCertificate != null) {
        sslInit.setAuthenticationCertificate(certReader.read(authenticationCertificate));
      }
      if (authenticationKey != null) {
        sslInit.setAuthenticationKey(keyReader.read(authenticationKey));
      }
    } catch (IOException e) {
      throw new GeneralSecurityException(e);
    }
    return sslInit;
  }


  @Override
  public boolean equals(final Object o)
  {
    if (o == this) {
      return true;
    }
    if (o instanceof X509CredentialConfig) {
      final X509CredentialConfig v = (X509CredentialConfig) o;
      return LdapUtils.areEqual(trustCertificates, v.trustCertificates) &&
             LdapUtils.areEqual(authenticationCertificate, v.authenticationCertificate) &&
             LdapUtils.areEqual(authenticationKey, v.authenticationKey);
    }
    return false;
  }


  @Override
  public int hashCode()
  {
    return LdapUtils.computeHashCode(HASH_CODE_SEED, trustCertificates, authenticationCertificate, authenticationKey);
  }


  @Override
  public String toString()
  {
    return new StringBuilder("[").append(
      getClass().getName()).append("@").append(hashCode()).append("::")
      .append("trustCertificates=").append(trustCertificates).append(", ")
      .append("authenticationCertificate=").append(authenticationCertificate).append(", ")
      .append("authenticationKey=").append(authenticationKey).append("]").toString();
  }


  /**
   * Creates a builder for this class.
   *
   * @return  new builder
   */
  public static Builder builder()
  {
    return new Builder();
  }


  // CheckStyle:OFF
  public static class Builder
  {


    private final X509CredentialConfig object = new X509CredentialConfig();


    protected Builder() {}


    public Builder trustCertificates(final String certificates)
    {
      object.setTrustCertificates(certificates);
      return this;
    }


    public Builder authenticationCertificate(final String certificate)
    {
      object.setAuthenticationCertificate(certificate);
      return this;
    }


    public Builder authenticationKey(final String key)
    {
      object.setAuthenticationKey(key);
      return this;
    }


    public X509CredentialConfig build()
    {
      return object;
    }
  }
  // CheckStyle:ON
}
