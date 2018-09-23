package com.swiftavenue.vanhacks2018.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiftavenue.vanhacks2018.domain.XMNotificationProperties;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Component
public class XMNotificationService {
    // TODO-Dwi: externalize later
    private static final String XMATTERS_URI = "https://cocobuns.xmatters.com/reapi/2015-04-01/forms/c4ef8900-7997-40a8-948d-4aefe90d8aab/triggers";
    private static final String XM_USER = "team4cocobuns@gmail.com";
    private static final String XM_PASSWORD = "coco#buns";


    public void sendNotification(XMNotificationProperties payload, String targetName) throws IOException, AuthenticationException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(XMATTERS_URI);

        String jsonPayload = jsonify(payload, targetName);
        StringEntity entity = new StringEntity(jsonPayload);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(XM_USER, XM_PASSWORD);
        httpPost.addHeader(new BasicScheme().authenticate(creds, httpPost, null));

        CloseableHttpResponse response = httpclient.execute(httpPost);

        try {
            HttpEntity responseEntity = response.getEntity();

            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(responseEntity);
        } finally {
            response.close();
        }
    }

    public String jsonify(XMNotificationProperties properties, String targetName) throws JsonProcessingException {
        XMPayload payload = new XMPayload();
        payload.setProperties(properties);

        Recipient recip = new Recipient();
        recip.setTargetName(targetName);

        List<Recipient> recipList = new ArrayList<>();
        recipList.add(recip);
        payload.setRecipients(recipList);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(payload);
        return jsonStr;
    }

    class XMPayload {
        private XMNotificationProperties properties;
        private List<Recipient> recipients;

        public XMNotificationProperties getProperties() {
            return properties;
        }

        public void setProperties(XMNotificationProperties properties) {
            this.properties = properties;
        }

        public List<Recipient> getRecipients() {
            return recipients;
        }

        public void setRecipients(List<Recipient> recipients) {
            this.recipients = recipients;
        }
    }

    class Recipient {
        private String targetName;

        public String getTargetName() {
            return targetName;
        }

        public void setTargetName(String targetName) {
            this.targetName = targetName;
        }
    }
}
