public class UserServiceImpl implements UserService{
    public String getUserName(int id) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "xue"+id;
    }

    public int getNum() throws Exception {
        throw new Exception("afafgags");
    }
}
