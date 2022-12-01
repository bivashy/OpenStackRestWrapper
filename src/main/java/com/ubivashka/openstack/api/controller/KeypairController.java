package com.ubivashka.openstack.api.controller;

import java.util.List;

import org.openstack4j.model.compute.Keypair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ubivashka.openstack.api.model.OpenStackAPI;

@Controller
@RequestMapping("keypairs")
public class KeypairController {
    @Autowired
    private OpenStackAPI openStackAPI;

    @RequestMapping
    private @ResponseBody List<? extends Keypair> getKeypairs() {
        return openStackAPI.getKeypairs();
    }
}
