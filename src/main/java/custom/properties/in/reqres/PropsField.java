package custom.properties.in.reqres;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:properties/in/reqres/fields.properties"
})
public interface PropsField extends Config{
    @Key("field.data")
    String fieldData();
}
