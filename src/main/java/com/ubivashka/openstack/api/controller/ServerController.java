package com.ubivashka.openstack.api.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ubivashka.openstack.api.model.OpenStackAPI;
import com.ubivashka.openstack.api.model.request.BlockDeviceRequestParam;

@Controller
@RequestMapping("servers")
public class ServerController {
    @Autowired
    private OpenStackAPI openStackAPI;

    @RequestMapping
    private @ResponseBody List<? extends Server> getServers() {
        return openStackAPI.getServers();
    }

    @RequestMapping("/stop")
    private @ResponseBody ActionResponse stopServer(@RequestParam(name = "server-id") String serverId) {
        return openStackAPI.serverAction(serverId, Action.STOP);
    }

    @RequestMapping("/delete")
    private @ResponseBody ActionResponse deleteServer(@RequestParam(name = "server-id") String serverId) {
        return openStackAPI.serverAction(serverId, Action.FORCEDELETE);
    }

    @RequestMapping("/action")
    private @ResponseBody ActionResponse actionServer(@RequestParam(name = "server-id") String serverId, @RequestParam(name = "action") Action action) {
        return openStackAPI.serverAction(serverId, action);
    }

    @PostMapping("/create")
    private @ResponseBody ResponseEntity<Server> createServer(@RequestParam(name = "name") String name, @RequestParam(name = "flavor-id") String flavorId,
            @RequestParam(name = "image-id", required = false) String imageId, @RequestParam(name = "networks") String[] networks,
            @RequestParam(name = "keypair", required = false) String keypair, @Valid BlockDeviceRequestParam blockDevice) {
        return ResponseEntity.ok(openStackAPI.createServer(
                Builders.server()
                        .name(name)
                        .flavor(flavorId)
                        .image(imageId)
                        .blockDevice(blockDevice.asBuilder().build())
                        .keypairName(keypair)
                        .networks(
                                Arrays.asList(networks)).build()));
    }
}
