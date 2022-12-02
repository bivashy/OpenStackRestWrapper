package com.ubivashka.openstack.api.model;

import java.util.List;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.core.transport.Config;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Keypair;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.model.network.Network;
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
        return getClient().blockStorage().volumes().list();
    }

    public List<? extends Server> getServers() {
        return getClient().compute().servers().list();
    }

    public List<? extends Network> getNetworks() {
        return getClient().networking().network().list();
    }

    public List<? extends Keypair> getKeypairs() {
        return getClient().compute().keypairs().list();
    }

    public List<? extends Flavor> getFlavors() {
        return getClient().compute().flavors().list();
    }

    public Keypair createKeypair(String name, String publicKey) {
        return getClient().compute().keypairs().create(name, publicKey);
    }

    public Server createServer(ServerCreate create) {
        return getClient().compute().servers().boot(create);
    }

    public ActionResponse serverAction(String serverId, Action action) {
        return getClient().compute().servers().action(serverId, action);
    }

    public OSClientV3 getClient() {
        if (OSClientSession.getCurrent() == null)
            return OSFactory.clientFromToken(client.getToken(), config);
        return client;
    }
}
