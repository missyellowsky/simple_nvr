package de.onvif.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 *
 */
public class HttpClient {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);
    
    //单例获取RESTClient类 
    private static class restClientHolder {
        
        private static final HttpClient restClient = new HttpClient();
    }
    
    private HttpClient() {
    }
    
    public static HttpClient getInstance() {
        return restClientHolder.restClient;
    }
    
    //单例获取RestTemplate类      
    private static class restTemplateHolder {
        
        
        private static HttpComponentsClientHttpRequestFactory httpsFactory = HttpClientUtils.getFactory();
        
        private static final RestTemplate restTemplate = new RestTemplate(httpsFactory);
    }
    
    private static RestTemplate getRestTemplateInstance() {
        return restTemplateHolder.restTemplate;
    }
    
    public String postMethodReuqest(String modelBaseUrl, String postDataJsonStr) {
        return postMethodReuqest(modelBaseUrl, postDataJsonStr, null);
    }
    
    /**
     * post请求
     * @param modelBaseUrl 参数
     * @param postDataJsonStr 参数
     * @return String
     */
    public String postMethodReuqest(String modelBaseUrl, String postDataJsonStr, String token) {
        RestTemplate restTemplate = HttpClient.getRestTemplateInstance();
        HttpEntity<String> entity = new HttpEntity<>(postDataJsonStr, getHttpHeaders(token));
        String returnStr;
        try {
            trustAllHttpsCertificates();
            returnStr = restTemplate.postForObject(modelBaseUrl, entity, String.class);
            return returnStr == null ? StringUtils.EMPTY : returnStr;
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
    
    /**
     * post请求
     * @param modelBaseUrl 参数
     * @param postDataJsonStr 参数
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> postMethodReuqestForEntity(String modelBaseUrl, String postDataJsonStr, String token) {
        RestTemplate restTemplate = HttpClient.getRestTemplateInstance();
        HttpEntity<String> entity = new HttpEntity<>(postDataJsonStr, getHttpHeaders(token));
        try {
            trustAllHttpsCertificates();
            ResponseEntity<String> response = restTemplate.postForEntity(modelBaseUrl, entity, String.class);
            return response;
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
    
    public String getMethodReuqest(String modelBaseUrl, String getDataJsonStr) {
        return getMethodReuqest(modelBaseUrl, getDataJsonStr, null);
    }
    
    /**
     * get请求
     * @param modelBaseUrl 参数
     * @param getDataJsonStr 参数
     * @param token 参数
     * @return String
     */
    public String getMethodReuqest(String modelBaseUrl, String getDataJsonStr, String token) {
        RestTemplate restTemplate = HttpClient.getRestTemplateInstance();
        HttpEntity<String> entity = new HttpEntity<>(getDataJsonStr, getHttpHeaders(token));
        String returnStr;
        try {
            trustAllHttpsCertificates();
            ResponseEntity<String> response = restTemplate.exchange(modelBaseUrl, HttpMethod.GET, entity, String.class);
            returnStr = response.getBody();
            return returnStr == null ? StringUtils.EMPTY : returnStr;
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
    
    /**
     * get请求 获取byte数组
     * @param modelBaseUrl 参数
     * @param getDataJsonStr 参数
     * @param token 参数
     * @return String
     */
    public byte[] getMethodReuqestBytes(String modelBaseUrl, String getDataJsonStr, String token) {
        RestTemplate restTemplate = HttpClient.getRestTemplateInstance();
        HttpEntity<String> entity = new HttpEntity<>(getDataJsonStr, getHttpHeaders(token));
        byte[] returnStr;
        try {
            trustAllHttpsCertificates();
            ResponseEntity<byte[]> response = restTemplate.exchange(modelBaseUrl, HttpMethod.GET, entity, byte[].class);
            returnStr = response.getBody();
            return returnStr;
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
    
    /**
     * delete请求
     * @param modelBaseUrl 参数
     * @return String
     */
    public String deleteMethodReuqest(String modelBaseUrl) {
        RestTemplate restTemplate = HttpClient.getRestTemplateInstance();
        String returnStr;
        try {
            trustAllHttpsCertificates();
            ResponseEntity<String> response = restTemplate.exchange(modelBaseUrl, HttpMethod.DELETE, null, String.class);
            returnStr = response.getBody();
            return returnStr == null ? StringUtils.EMPTY : returnStr;
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
    
    /**
     * 生成接口请求头
     * @return HttpHeaders 头
     */
    private HttpHeaders getHttpHeaders(String headToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        if(StringUtils.isNotEmpty(headToken)) {
            headers.add("x-auth-token", headToken);
        }
        return headers;
    }
    
    /**
     * 信任所有SSL连接
     * @throws NoSuchAlgorithmException 异常
     * @throws KeyManagementException  异常
     */
    private void trustAllHttpsCertificates() throws NoSuchAlgorithmException, KeyManagementException {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
    
    static class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {
        
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }
        
        public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }
        
        public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }
        
        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
            throws java.security.cert.CertificateException {
            return;
        }
        
        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            return;
        }
    }
}
