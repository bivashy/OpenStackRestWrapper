package com.ubivashka.openstack.api.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.openstack4j.api.Builders;
import org.openstack4j.model.compute.builder.BlockDeviceMappingBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlockDeviceRequestParam {
    private static final String INVALID_UUID = "Invalid UUID";
    private static final String INVALID_DEVICE_NAME = "Invalid device name";
    private static final String INVALID_ORDER = "Invalid order";
    @NotBlank(message = INVALID_UUID)
    @NotNull(message = INVALID_UUID)
    @JsonProperty("uuid")
    private String uuid;
    @NotBlank(message = INVALID_DEVICE_NAME)
    @NotNull(message = INVALID_DEVICE_NAME)
    @JsonProperty("device-name")
    private String deviceName;
    @Range(message = INVALID_ORDER)
    @JsonProperty("order")
    private int order = 0;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public BlockDeviceMappingBuilder asBuilder() {
        return Builders.blockDeviceMapping().uuid(uuid).deviceName(deviceName).bootIndex(order);
    }
}
