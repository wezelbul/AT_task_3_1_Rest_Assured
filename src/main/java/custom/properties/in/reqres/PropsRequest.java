package custom.properties.in.reqres;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:properties/in/reqres/requests.properties"
})

public interface PropsRequest extends Config{

    @Key("api.users.page.request")
    String apiUsersPageRequest();

    @Key("api.unknown.request")
    String apiUnknownRequest();

    @Key("api.login.request")
    String apiLoginRequest();

}