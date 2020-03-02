public class UserServiceImpl implements UserService{
    public String getUserName(int id) {
        return "xue"+id;
    }

    public int getNum() throws Exception {
        throw new Exception("afafgags");
    }
}
