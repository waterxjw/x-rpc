import com.xjw.xrpc.api.XProvider;
import com.xjw.xrpc.api.XProviderConfig;

public class ServerMain {
    public static void main(String[] args){
        XProviderConfig config=new XProviderConfig();
        config.setService(UserService.class);
        config.setImpl(new UserServiceImpl());
        XProvider provider=new XProvider(config);
        provider.publish();
    }
}
