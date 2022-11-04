package web_api.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:credential.properties")
public interface CredentialsConfig extends Config{
    String loginSelenoid();

    String passwordSelenoid();

    String remoteUrlSelenoid();
}
