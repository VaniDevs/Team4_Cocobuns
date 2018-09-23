package com.swiftavenue.vanhacks2018;

import java.io.IOException;
import com.swiftavenue.vanhacks2018.domain.XMNotificationProperties;
import com.swiftavenue.vanhacks2018.services.XMNotificationService;
import org.apache.http.auth.AuthenticationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationServiceTest {
    @Test
    public void testNotification() {
        XMNotificationProperties payload = XMNotificationProperties.newBuilder()
            .setClientContactPhone("666-666-6666")
            .setClientEmail("client@email.com")
            .setClientName("client name")
            .setDemographics("client demographics")
            .setReferringAgency("referring agency")
            .build();

        XMNotificationService service = new XMNotificationService();

        try {
            String targetName = "dwi2004|Work Email";
            service.jsonify(payload, targetName);
            service.sendNotification(payload, "dwi2004|Work Email");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

    }
}
