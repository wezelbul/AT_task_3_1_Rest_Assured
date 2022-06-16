package custom.properties.in.reqres;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:properties/in/reqres/file_formats.properties"
})
public interface PropsFileFormat extends Config{
    @Key("avatar.file.format")
    String avatarFileFormat();
}
