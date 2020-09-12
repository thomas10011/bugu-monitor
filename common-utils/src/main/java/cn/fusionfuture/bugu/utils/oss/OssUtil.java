package cn.fusionfuture.bugu.utils.oss;

import cn.fusionfuture.bugu.pojo.api.ResultCode;
import cn.fusionfuture.bugu.pojo.constants.OssConstants;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author thomas
 * @version 1.0
 * @class OssUtil
 * @description 上传文件至oss的工具类
 * @date 2020/8/19 12:56 下午
 */
@Component
public class OssUtil {

    OssUtilProperties ossUtilProperties;

    private static String endpoint;

    private static String bucket;

    private static String callbackUrl;

    @Autowired
    public void setOssConfig(OssUtilProperties ossUtilProperties) {
        this.ossUtilProperties = ossUtilProperties;
    }

    @PostConstruct
    public void init() {

        endpoint = ossUtilProperties.getEndpoint();
        bucket = ossUtilProperties.getBucket();
        callbackUrl = ossUtilProperties.getCallbackUrl();

    }

    /**
     * @author thomas
     * @description 生成阿里云服务签名产生的policy
     * @create 2020/8/19 7:51 下午
     * @update 2020/8/19 7:51 下午
     * @param dir oss的文件夹名称
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    public static Map<String, String> getPolicy(String dir) throws ServletException, IOException {
        // host的格式为 bucketname.endpoint
        String host = "https://" + bucket + "." + endpoint;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, OssConstants.ACCESS_ID, OssConstants.ACCESS_KEY);

        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", OssConstants.ACCESS_ID);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            // respMap.put("expire", formatISO8601Date(expiration));

            JSONObject jasonCallback = new JSONObject();
            jasonCallback.put("callbackUrl", callbackUrl);
            jasonCallback.put("callbackBody",
                    "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
            jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
            String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
//            respMap.put("callback", base64CallbackBody);

            return respMap;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return null;

        } finally {
            ossClient.shutdown();
        }
    }
}
