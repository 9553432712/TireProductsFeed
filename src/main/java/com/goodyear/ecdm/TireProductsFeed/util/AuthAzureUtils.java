package com.goodyear.ecdm.TireProductsFeed.util;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.goodyear.ecdm.TireProductsFeed.service.impl.ProductServiceImpl;
import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;

public class AuthAzureUtils {
	
	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Value("${authority}")
    private String authority;

    @Value("${resource}")
    private String resource;

    public AuthenticationResult getAccessTokenFromUserCredentials(String user, String password) {
        AuthenticationContext context = null;
        AuthenticationResult result = null;
        ExecutorService service = null;
        
        try {

            service = Executors.newFixedThreadPool(1);
            context = new AuthenticationContext(authority, false, service);

            Future<AuthenticationResult> future = context.acquireToken(
                    resource, new ClientCredential(user, password), null);
            result = future.get();

        } catch (InterruptedException | ExecutionException | MalformedURLException e) {
            logger.error(e.getMessage());
        } finally {
            service.shutdown();
        }

        if (result == null) {
            throw new RuntimeException(
                    "authentication result was null");
        }

        return result;
    }
}
