package com.balionis.jose1;

import java.util.UUID;

import com.nimbusds.jose.jwk.*;
import com.nimbusds.jose.jwk.gen.*;

public class MyApp {

    public static void main(String[] args) throws Exception {

        // Generate an EC key pair
        ECKey ecJWK = new ECKeyGenerator(Curve.P_256)
                .keyID(UUID.randomUUID().toString())
                .generate();
        System.out.println(ecJWK.toJSONObject());

        ECKey ecPublicJWK = ecJWK.toPublicJWK();

        System.out.println(ecPublicJWK.toJSONObject());
    }
}

