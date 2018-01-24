package com.talkingdata.api.example.client.ssl;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

public class WebClientDevWrapper {
	public static org.apache.http.client.HttpClient wrapClient(org.apache.http.client.HttpClient base) {  
        try {  
            SSLContext ctx = SSLContext.getInstance("TLS");  
            X509TrustManager tm = new X509TrustManager() {  
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {  
                    return null;  
                }  
                public void checkClientTrusted(  
                        java.security.cert.X509Certificate[] chain,  
                        String authType)  
                        throws java.security.cert.CertificateException {  
                    // TODO Auto-generated method stub  
                      
                }  
                public void checkServerTrusted(  
                        java.security.cert.X509Certificate[] chain,  
                        String authType)  
                        throws java.security.cert.CertificateException {  
                    // TODO Auto-generated method stub  
                      
                }  
            };  
            ctx.init(null, new TrustManager[] { tm }, null);  
            SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
            SchemeRegistry registry = new SchemeRegistry();  
            registry.register(new Scheme("https", 443, ssf));  
            ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);  
            return new DefaultHttpClient(mgr, base.getParams());  
        } catch (Exception ex) {  
            ex.printStackTrace();  
            return null;  
        }  
    }  
}  

