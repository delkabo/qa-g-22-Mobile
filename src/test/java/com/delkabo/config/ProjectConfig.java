package com.delkabo.config;

import org.aeonbits.owner.Config;

public interface ProjectConfig extends Config {

    String deviceHost();

    // DdeviceHost=browserstack / emulation / real / selenoid

}
