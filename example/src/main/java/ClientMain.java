import com.xjw.xrpc.api.XConsumer;
import com.xjw.xrpc.api.XConsumerConfig;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientMain {
    public static void main(String [] args)throws Exception{
        XConsumerConfig config=new XConsumerConfig();
        config.setService(UserService.class);
        XConsumer<UserService> consumer=new XConsumer<UserService>(config);
        final UserService userService=consumer.getProxy();
        ExecutorService executorService=Executors.newCachedThreadPool();
        for (int i=0;i<20;i++){
            executorService.execute(new Runnable() {
                public void run() {
                    for(int i=0;i<1000;i++){
                        try {
                            System.out.println(userService.getUserName(i));
                        }catch (Exception e){
                            System.out.println(i+"   "  +e.getMessage());
                        }

                    }
                }
            });
        }

    }
}
