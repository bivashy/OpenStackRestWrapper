package com.ubivashka.openstack.api.configuration;

import java.util.Arrays;
import java.util.List;

import org.openstack4j.core.transport.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ubivashka.openstack.api.immerse.ImmerseAPIProvider;
import com.ubivashka.openstack.api.immerse.ImmerseEndpointURLResolver;
import com.ubivashka.openstack.api.model.OpenStackAPI;
import com.ubivashka.openstack.api.model.OpenStackCredentials;

@Configuration
public class AuthConfiguration {
    public static final String IMMERSE_CLOUD_SUPPORT = "immerse-cloud-support";
    public static final String ACCESS_KEYS = "access-keys";

    @Bean
    public OpenStackCredentials openStackCredentials(@Value("${openstack-login}") String login, @Value("${openstack-password}") String password,
            @Value("${openstack-endpoint}") String endpoint) {
        return new OpenStackCredentials(login, password, endpoint);
    }

    @Bean(name = IMMERSE_CLOUD_SUPPORT)
    public boolean isImmerseCloudSupported(@Value("${immerse-cloud-support}") boolean immerseCloudSupported) {
        if (immerseCloudSupported)
            ImmerseAPIProvider.init();
        return immerseCloudSupported;
    }

    @Bean
    public Config openStackConfiguration(@Autowired @Qualifier(IMMERSE_CLOUD_SUPPORT) boolean immerseCloudSupport) {
        if (!immerseCloudSupport)
            return Config.DEFAULT;
        return Config.newConfig().withEndpointURLResolver(new ImmerseEndpointURLResolver());
    }

    @Bean
    public OpenStackAPI openStackAPI(@Autowired OpenStackCredentials credentials, @Autowired Config config) {
        return new OpenStackAPI(credentials, config);

    }

    @Bean(name = ACCESS_KEYS)
    public List<String> accessKeys(@Value("${access-keys}") String accessKey) {
        return Arrays.stream(accessKey.split(";")).toList();
    }
}
