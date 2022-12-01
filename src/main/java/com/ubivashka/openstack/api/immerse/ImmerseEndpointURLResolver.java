package com.ubivashka.openstack.api.immerse;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.model.identity.URLResolverParams;
import org.openstack4j.openstack.identity.internal.DefaultEndpointURLResolver;

public class ImmerseEndpointURLResolver extends DefaultEndpointURLResolver {
    @Override
    public String findURLV3(URLResolverParams p) {
        String result = super.findURLV3(p);
        if (p.type == ServiceType.BLOCK_STORAGE)
            return result.replace("v2", "v3");
        return result;
    }
}
