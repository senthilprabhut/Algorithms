/*
 * Copyright (c) 2018 VMware, Inc. All Rights Reserved.
 */

package adt;

import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;

public class GetCertSha {
    private static final char[] HEXDIGITS = "0123456789abcdef".toCharArray();

    public static void main(String[] args) {
        GetCertSha tester = new GetCertSha();
        try {
            tester.testConnectionTo("https://marketplace-stg.vmware.com");
//            tester.testConnectionTo("https://mail.google.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GetCertSha() {
        super();
    }

    public void testConnectionTo(String aURL) throws Exception {
        URL destinationURL = new URL(aURL);
        HttpURLConnection.setFollowRedirects(false);
        HttpsURLConnection c = (HttpsURLConnection)destinationURL.openConnection();
        c.setRequestMethod("HEAD"); // GET
        c.setDoOutput(false); c.setAllowUserInteraction(false);
        c.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0 URLInspect/1.0");
        c.setRequestProperty("Connection", "close");
        c.connect(); // throws SSL handshake exception

        // retrieve TLS info before reading response (which closes connection?)
        Certificate[] chain = c.getServerCertificates();
        X509Certificate server = (X509Certificate)chain[0];
        byte[] encoded = server.getEncoded();
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        sha1.update(encoded);
        System.out.println("sha1: " + toHexString(sha1.digest()));
    }

    private String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 3);
        for (int i=0; i<bytes.length; i++) {
            int b = bytes[i];
            b &= 0xff;
            sb.append(HEXDIGITS[b >> 4]);
            sb.append(HEXDIGITS[b & 15]);

            if (i < bytes.length-1)
                sb.append(':');
        }
        return sb.toString();
    }
}
