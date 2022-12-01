package com.ubivashka.openstack.api.immerse;

import org.openstack4j.model.identity.URLResolverParams;
import org.openstack4j.openstack.identity.internal.DefaultEndpointURLResolver;

public class ImmerseEndpointURLResolver extends DefaultEndpointURLResolver {
    @Override
    public String findURLV3(URLResolverParams p) {
        return super.findURLV3(p).replace("v2", "v3");
    }
}
