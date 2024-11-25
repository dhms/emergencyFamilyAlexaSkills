package com.familyhelp.adapter.sns;

import software.amazon.awssdk.services.sns.SnsClient;

public class SnsClientSingleton {

    private static SnsClient snsClient;

    public static SnsClient getInstance() {
        if (snsClient == null) {
            snsClient = SnsClient.create();
        }

        return snsClient;
    }
}
