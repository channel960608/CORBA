package corba.util;

/**
 * Created by channel on 2017/5/31.
 */
public class User {
    private String username;
    private String pass;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    boolean verify(String pass) {
        return pass.equals(getPass());
    }

}
