package com.ubivashka.openstack.api.controller;

import java.util.List;

import org.openstack4j.model.compute.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ubivashka.openstack.api.model.OpenStackAPI;

@Controller
public class ServerController {
    @Autowired
    private OpenStackAPI openStackAPI;

    @RequestMapping("/servers")
    private @ResponseBody List<? extends Server> getServers() {
        return openStackAPI.getServers();
    }
}
