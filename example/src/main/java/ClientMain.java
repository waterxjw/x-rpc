import com.xjw.xrpc.api.XConsumer;
import com.xjw.xrpc.api.XConsumerConfig;

public class ClientMain {
    public static void main(String [] args){
        XConsumerConfig config=new XConsumerConfig();
        config.setService(UserService.class);
        XConsumer<UserService> consumer=new XConsumer<UserService>(config);
        UserService userService=consumer.getProxy();
        System.out.println(userService.getUserName(45));
    }
}
