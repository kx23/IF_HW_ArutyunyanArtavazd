package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface TestDataConfig extends Config {

    @Key("user.login")
    String userLogin();

    @Key("user.password")
    String userPassword();

    @Key("project.name")
    String projectName();

    @Key("test.task.name")
    String testTaskName();

    @Key("test.task.version")
    String testTaskVersion();

    @Key("issue.summary")
    String issueSummary();

    @Key("issue.description")
    String issueDescription();

    @Key("issue.environment")
    String issueEnvironment();

}