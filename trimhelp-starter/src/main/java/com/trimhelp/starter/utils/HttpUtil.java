package com.trimhelp.starter.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * @author hrimhelp
 */
public class HttpUtil {

    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);



    public static JSONObject post(String url, JSONObject json) {
        CloseableHttpClient client = HttpClients.createDefault();
        //建立HttpPost对象
        log.info("grilleSign start:");
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-type", "application/json; charset=utf-8");
        post.setEntity(new StringEntity(json.toString(), Charset.forName("utf-8")));
        HttpResponse httpResponse = null;
        HttpEntity entity = null;
        String s = "";
        try {
            httpResponse = client.execute(post);
            entity = httpResponse.getEntity();
            s = EntityUtils.toString(entity);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(s);
    }


    /**
     * 异步发送post请求
     *
     * @param param 请求参数：以key=&value= 形式传递参数
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void httpSyncPost(String url, String param) throws InterruptedException, ExecutionException {

        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        final HttpPost request = new HttpPost(url);

        StringEntity stringEntity = new StringEntity(param, "utf-8");
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/x-www-form-urlencoded");
        request.setEntity(stringEntity);

        httpclient.start();

        final CountDownLatch latch = new CountDownLatch(1);

        httpclient.execute(request, new FutureCallback<HttpResponse>() {

            @Override
            public void completed(final HttpResponse response) {
                latch.countDown();
                log.info(" callback thread id is : " + Thread.currentThread().getId());
                log.info(request.getRequestLine() + "->" + response.getStatusLine());
                try {
                    String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                    log.info(" response content is : " + content);
                } catch (IOException e) {
                    log.error("cn.stylefeng.guns.core.util.HttpUtil.httpSyncPost", e.getMessage());
                    e.printStackTrace();
                }
            }
            @Override
            public void failed(final Exception ex) {
                latch.countDown();
                log.info(request.getRequestLine() + "->" + ex);
                log.info(" callback thread id is : " + Thread.currentThread().getId());
                ex.printStackTrace();
            }
            @Override
            public void cancelled() {
                latch.countDown();
                log.info(request.getRequestLine() + " cancelled");
                log.info(" callback thread id is : " + Thread.currentThread().getId());
            }

        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            log.error("cn.stylefeng.guns.core.util.HttpUtil.httpSyncPost", e.getMessage());
            e.printStackTrace();
        }

        try {
            httpclient.close();
        } catch (IOException e) {
            log.error("cn.stylefeng.guns.core.util.HttpUtil.httpSyncPost", e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.error("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                log.error("cn.stylefeng.guns.core.util.HttpUtil.sendPost", e.getMessage());
                e.printStackTrace();
            }
        }

        log.info("result = " + result);
        return result;
    }

    public static final int CONNECTION_TIMEOUT = 5000;
    public static final int SOCKETCOOECTION_TIMEOUT = 900000000;

    public static final int NORMAL = 200;
    private static CloseableHttpClient httpClient = createSSLClientDefault();

    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (Exception e) {
            log.error("cn.stylefeng.guns.core.util.HttpUtil.createSSLClientDefault",e.getMessage());
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    public static String get(String url, Map<String, String> paramsMap) throws Exception {

        if (null == httpClient){
            httpClient = createSSLClientDefault();
        }

        CloseableHttpClient client = httpClient;
        String responseText = null;
        HttpEntity entity = null;

        CloseableHttpResponse response = null;
        try {
            StringBuilder sb = new StringBuilder();
            if (paramsMap != null) {
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    sb.append("&" + param.getKey() + "=" + param.getValue());
                }
                url = url + "?" + sb.toString().substring(1);
            }

            HttpGet method = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECTION_TIMEOUT)
                    .setSocketTimeout(SOCKETCOOECTION_TIMEOUT).build();// 设置请求超时时间
            method.setConfig(requestConfig);
            response = client.execute(method);
            entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
            if (response.getStatusLine().getStatusCode() != NORMAL){
                throw new Exception(responseText);
            }
            if (response != null) {
                response.close();
            }
        } catch (Exception e) {
            log.error("cn.stylefeng.guns.core.util.HttpUtil.get",e.getMessage());
            throw new Exception(e);
        }
        return responseText;
    }

    public static String get(String url, NameValuePair[] nameValuePair) throws Exception {
        Map<String, String> paramsMap = new HashMap<String, String>();
        for (NameValuePair t : nameValuePair) {
            paramsMap.put(t.getName(), t.getValue());
        }
        return get(url, paramsMap);
    }

    public static String post(String url, NameValuePair[] nameValuePair) throws Exception {
        Map<String, String> paramsMap = new HashMap<String, String>();
        for (NameValuePair t : nameValuePair) {
            paramsMap.put(t.getName(), t.getValue());
        }
        return post(url, paramsMap);
    }

    public static String post(String url, Map<String, String> paramsMap) throws Exception {
        // reuse httpclient to keepalive to the server
        // keepalive in https will save time on tcp handshaking.
        if (null == httpClient){

            httpClient = createSSLClientDefault();
        }


        CloseableHttpClient client = httpClient;
        String responseText = "";
        HttpPost method = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECTION_TIMEOUT)
                .setSocketTimeout(SOCKETCOOECTION_TIMEOUT).build();// 设置请求超时时间
        method.setConfig(requestConfig);
        HttpEntity entity = null;
        CloseableHttpResponse response = null;
        try {
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    org.apache.http.NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
            }
            response = client.execute(method);
            entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }

            if (response != null) {
                response.close();
            }
            if (response.getStatusLine().getStatusCode() != NORMAL){
                throw new Exception(responseText);
            }


        } catch (Exception e) {
            log.error("cn.stylefeng.guns.core.util.HttpUtil.post",e.getMessage());
            e.printStackTrace();
            throw new Exception(e);
        }
        return responseText;
    }

    public static String httpGet(String url) {
        // TODO Auto-generated method stub
        String dis = "";
        HttpGet httpRequest = null;
        try {
            httpRequest = new HttpGet(url);
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse httpResponse = httpclient.execute(httpRequest);

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String objectStr = EntityUtils.toString(httpResponse.getEntity()).trim();
                dis = objectStr;
            }

        } catch (Exception e) {
            log.error("cn.stylefeng.guns.core.util.HttpUtil.httpGet",e.getMessage());
            e.printStackTrace();
        } finally {
            httpRequest.releaseConnection();
        }
        return dis;
    }

    public static void main(String[] args) {

        String date = "5E07DED35CCBAEBB329B7C51B2C9607A";
        String sign = "NUUwN0RFRDM1Q0NCQUVCQjMyOUI3QzUxQjJDOTYwN0E=";
        String param = "date=" + date + "&sign=" + sign;

        sendPost("http://localhost:8081/street/revoke", param);
    }


}
