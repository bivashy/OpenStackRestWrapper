package com.ubivashka.openstack.api.controller;

import java.util.List;

import org.openstack4j.model.network.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ubivashka.openstack.api.model.OpenStackAPI;

@Controller
public class NetworkController {
    @Autowired
    private OpenStackAPI openStackAPI;

    @RequestMapping("/networks")
    private @ResponseBody List<? extends Network> getNetworks() {
        return openStackAPI.getNetworks();
    }
}
