package com.ubivashka.openstack.api.controller;

import java.util.List;

import org.openstack4j.model.storage.block.Volume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ubivashka.openstack.api.model.OpenStackAPI;

@Controller
public class VolumeController {
    @Autowired
    private OpenStackAPI openStackAPI;

    @RequestMapping("/volumes")
    private @ResponseBody List<? extends Volume> getVolumes() {
        return openStackAPI.getVolumes();
    }
}
