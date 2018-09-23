package com.swiftavenue.vanhacks2018.domain;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

public class XMNotificationProperties {

    @JsonProperty("Client Contact Phone")
    private String clientContactPhone;

    @JsonProperty("Client Email")
    private String clientEmail;

    @JsonProperty("Referring Agency")
    private String referringAgency;

    @JsonProperty("Demographics")
    private String demographics;

    @JsonProperty("Client Name")
    private String clientName;

    private XMNotificationProperties(@NotNull Builder builder) {
        this.clientContactPhone = builder.clientContactPhone;
        this.clientEmail = builder.clientEmail;
        this.clientName = builder.clientName;
        this.demographics = builder.demographics;
        this.referringAgency = builder.referringAgency;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getClientContactPhone() {
        return clientContactPhone;
    }

    public void setClientContactPhone(String clientContactPhone) {
        this.clientContactPhone = clientContactPhone;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getReferringAgency() {
        return referringAgency;
    }

    public void setReferringAgency(String referringAgency) {
        this.referringAgency = referringAgency;
    }

    public String getDemographics() {
        return demographics;
    }

    public void setDemographics(String demographics) {
        this.demographics = demographics;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public static class Builder {
        private String clientContactPhone;
        private String clientEmail;
        private String referringAgency;
        private String demographics;
        private String clientName;

        private Builder() {
        }

        public Builder setClientContactPhone(String clientContactPhone) {
            this.clientContactPhone = clientContactPhone;
            return this;
        }

        public Builder setClientEmail(String clientEmail) {
            this.clientEmail = clientEmail;
            return this;
        }

        public Builder setReferringAgency(String referringAgency) {
            this.referringAgency = referringAgency;
            return this;
        }

        public Builder setDemographics(String demographics) {
            this.demographics = demographics;
            return this;
        }

        public Builder setClientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public XMNotificationProperties build() {
            return new XMNotificationProperties((this));
        }
    }
}
