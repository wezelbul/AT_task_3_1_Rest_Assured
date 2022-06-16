package custom.properties.com.autodns.gateway;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:properties/com/autodns/gateway/data.properties"
})

public interface PropsData extends Config {
    @Key("count.tags.com.autodns.gateway")
    int countTags();

}
