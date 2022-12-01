package com.ubivashka.openstack.api.model;

import java.util.List;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.core.transport.Config;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.openstack.OSFactory;
import org.openstack4j.openstack.internal.OSClientSession;

public class OpenStackAPI {
    private static final Identifier DEFAULT_IDENTIFIER = Identifier.byName("Default");
    private final OSClientV3 client;
    private final Config config;

    public OpenStackAPI(OpenStackCredentials credentials, Config openStackConfiguration) {
        this.client = OSFactory.builderV3()
                .endpoint(credentials.endpoint())
                .credentials(credentials.login(), credentials.password(), DEFAULT_IDENTIFIER)
                .scopeToProject(Identifier.byName(credentials.login()), DEFAULT_IDENTIFIER).withConfig(openStackConfiguration).authenticate();
        this.config = openStackConfiguration;
    }

    public List<? extends Volume> getVolumes() {
        getClient().blockStorage().volumes().create()
        return getClient().blockStorage().volumes().list();
    }

    public OSClientV3 getClient() {
        if (OSClientSession.getCurrent() == null)
            return OSFactory.clientFromToken(client.getToken(), config);
        return client;
    }
}
