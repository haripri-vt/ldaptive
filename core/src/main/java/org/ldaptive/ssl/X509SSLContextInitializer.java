/* See LICENSE for licensing and NOTICE for copyright. */
package org.ldaptive.ssl;

import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * Provides an SSL context initializer which can use X.509 certificates to create key and trust managers.
 *
 * @author  Middleware Services
 */
public class X509SSLContextInitializer extends AbstractSSLContextInitializer
{

  /** Certificates used to create trust managers. */
  private X509Certificate[] trustCerts;

  /** Certificate used to create key managers. */
  private X509Certificate authenticationCert;

  /** Private key used to create key managers. */
  private PrivateKey authenticationKey;


  /**
   * Returns the certificates to use for creating the trust managers.
   *
   * @return  X.509 certificates
   */
  public X509Certificate[] getTrustCertificates()
  {
    return trustCerts;
  }


  /**
   * Sets the certificates to use for creating the trust managers.
   *
   * @param  certs  X.509 certificates
   */
  public void setTrustCertificates(final X509Certificate... certs)
  {
    trustCerts = certs;
  }


  /**
   * Returns the certificate to use for creating the key managers.
   *
   * @return  X.509 certificate
   */
  public X509Certificate getAuthenticationCertificate()
  {
    return authenticationCert;
  }


  /**
   * Sets the certificate to use for creating the key managers.
   *
   * @param  cert  X.509 certificate
   */
  public void setAuthenticationCertificate(final X509Certificate cert)
  {
    authenticationCert = cert;
  }


  /**
   * Returns the private key associated with the authentication certificate.
   *
   * @return  private key
   */
  public PrivateKey getAuthenticationKey()
  {
    return authenticationKey;
  }


  /**
   * Sets the private key associated with the authentication certificate.
   *
   * @param  key  private key
   */
  public void setAuthenticationKey(final PrivateKey key)
  {
    authenticationKey = key;
  }


  @Override
  protected TrustManager[] createTrustManagers()
    throws GeneralSecurityException
  {
    TrustManager[] tm = null;
    if (trustCerts != null && trustCerts.length > 0) {
      final TrustManagerFactory tmf = getTrustManagerFactory(trustCerts);
      tm = tmf.getTrustManagers();
    }
    return tm;
  }


  /**
   * Creates a new trust manager factory.
   *
   * @param  certs  to add as trusted material
   *
   * @return  trust manager factory
   *
   * @throws  GeneralSecurityException  if the trust manager factory cannot be initialized
   */
  protected TrustManagerFactory getTrustManagerFactory(final X509Certificate[] certs)
    throws GeneralSecurityException
  {
    final KeyStore ks = KeyStoreUtils.newInstance();
    KeyStoreUtils.setCertificateEntry("ldap_trust_", ks, certs);

    final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    tmf.init(ks);
    return tmf;
  }


  @Override
  public KeyManager[] getKeyManagers()
    throws GeneralSecurityException
  {
    KeyManager[] km = null;
    if (authenticationCert != null && authenticationKey != null) {
      final KeyManagerFactory kmf = getKeyManagerFactory(authenticationCert, authenticationKey);
      km = kmf.getKeyManagers();
    }
    return km;
  }


  /**
   * Creates a new key manager factory.
   *
   * @param  cert  to initialize the key manager factory
   * @param  key  to initialize the key manager factory
   *
   * @return  key manager factory
   *
   * @throws  GeneralSecurityException  if the key manager factory cannot be initialized
   */
  protected KeyManagerFactory getKeyManagerFactory(final X509Certificate cert, final PrivateKey key)
    throws GeneralSecurityException
  {
    final KeyStore ks = KeyStoreUtils.newInstance();
    KeyStoreUtils.setKeyEntry("ldap_client_auth", ks, "changeit".toCharArray(), key, cert);

    final KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    kmf.init(ks, "changeit".toCharArray());
    return kmf;
  }


  @Override
  public String toString()
  {
    return new StringBuilder("[").append(
      getClass().getName()).append("@").append(hashCode()).append("::")
      .append("trustManagers=").append(Arrays.toString(trustManagers)).append(", ")
      .append("trustCerts=").append(Arrays.toString(trustCerts)).append(", ")
      .append("authenticationCert=").append(authenticationCert).append("]").toString();
  }
}
