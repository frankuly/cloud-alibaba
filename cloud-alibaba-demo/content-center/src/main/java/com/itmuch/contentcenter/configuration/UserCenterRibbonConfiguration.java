package com.itmuch.contentcenter.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import ribbonconfiguration.RibbonConfiguration;

@Configurable
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class UserCenterRibbonConfiguration {
}
