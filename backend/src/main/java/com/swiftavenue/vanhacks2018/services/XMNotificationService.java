package com.swiftavenue.vanhacks2018.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class XMNotificationService {
    @Value("${xmatters.notification.uri}")
    private String xmattersUri;

    @Value("${xmatters.notification.username}")
    private String xmUser;

    @Value("${xmatters.notification.password}")
    private String xmPassword;

    /**
     * Send new referral notification via xmatters
     * @param payload
     * @param targetNames
     * @throws IOException
     * @throws AuthenticationException
     */
    public void sendNotification(XMNotificationProperties payload, String targetNames) throws IOException, AuthenticationException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(xmattersUri);

        String jsonPayload = jsonify(payload, targetNames);
        StringEntity entity = new StringEntity(jsonPayload);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(xmUser, xmPassword);
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

    public String jsonify(XMNotificationProperties properties, String targetNames) throws JsonProcessingException {
        XMPayload payload = new XMPayload();
        payload.setProperties(properties);

        List<Recipient> recipList = new ArrayList<>();
        Arrays.stream(targetNames.split(",")).forEach(tn -> {
            Recipient recip = new Recipient();
            recip.setTargetName(tn);
            recipList.add(recip);
        });
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
