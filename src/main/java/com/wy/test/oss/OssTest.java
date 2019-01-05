package com.wy.test.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.crypto.Data;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OssTest {

    private String url;
    private String ossAccessKeyId;
    private String accessKeySecret;

    public OssTest(String url, String ossAccessKeyId, String accessKeySecret) {
        this.url = url;
        this.ossAccessKeyId = ossAccessKeyId;
        this.accessKeySecret = accessKeySecret;
    }

    public static void main(String[] args) {
        String url = "https://oss-cn-shanghai.aliyuncs.com";
        String ossAccessKeyId = "LTAI79uarpn3qaeH";
        String accessKeySecret = "WLvndcZmRYoOv5EdaaWwg5ySZq5wWt";
        OssTest oss = new OssTest(url, ossAccessKeyId, accessKeySecret);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2019);
        Date now = new Date();
//        System.out.println(getUrl(url, ossAccessKeyId, calendar.getTimeInMillis(), getEncoding(accessKeySecret)));
        String data = "GET" + "\n\n\n" + calendar.getTimeInMillis() + "\n" + "/";
        String sha1 = getHMAC(accessKeySecret, data);
        System.out.println(getUrl(url, ossAccessKeyId, calendar.getTimeInMillis(), sha1));
//        oss.test("vipclass");
    }

    private static String md5(String a){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(a.getBytes());
            String b = new BigInteger(1, md.digest()).toString(16);
            return new sun.misc.BASE64Encoder().encode(b.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getHMAC(String key, String data){
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKeySpec);
            byte[] raw = mac.doFinal(data.getBytes());
            byte[] result = Base64.encodeBase64(raw);
            return new String(result, "UTF-8");
        } catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getUrl(String url,String keyId,long expires, String signature){
        StringBuilder builder = new StringBuilder();
        builder .append(url)
                .append("?")
                .append("OSSAccessKeyId=")
                .append(keyId)
                .append("&Expires=")
                .append(expires)
                .append("&Signature=")
                .append(signature);
        return builder.toString();
    }

    private void test(String bucketName){
        OSSClient client = new OSSClient(url, ossAccessKeyId, accessKeySecret);
        ObjectListing listing = client.listObjects(bucketName);
        List<OSSObjectSummary> summaries = listing.getObjectSummaries();
        for (OSSObjectSummary summary : summaries){
            System.out.println(summary.getKey());
        }
        client.shutdown();
    }

}
