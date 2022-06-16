package custom.properties.in.reqres;

import org.aeonbits.owner.ConfigFactory;

public class TestData {
    public static PropsRequest propsRequest = ConfigFactory.create(PropsRequest.class);
    public static PropsField propsField = ConfigFactory.create(PropsField.class);
    public static PropsFileFormat propsFileFormat = ConfigFactory.create(PropsFileFormat.class);
}
