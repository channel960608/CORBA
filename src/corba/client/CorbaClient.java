package corba.client;

import CorbaApp.Corba;
import CorbaApp.CorbaHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by channel on 2017/5/31.
 */
public class CorbaClient {
    public void run(String[] args) throws Exception {
        Properties props = new Properties();
        // 生成一个ORB，并初始化，这个和Server端一样
        props .put("org.omg.CORBA.ORBInitialPort", "1050");
        props.put("org.omg.CORBA.ORBInitialHost", "localhost");
        ORB orb = ORB.init(args, props);
        // 获得根命名上下文
        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        // 用NamingContextExt代替NamingContext.
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

        // 通过名称获取服务器端的对象引用
        String name = "Corba";
        Corba corba = CorbaHelper.narrow(ncRef.resolve_str(name));
        System.out.println("You can input help for help");
        //调用远程对象
        run(corba);



    }

    public static void main(String[] args) {
        CorbaClient client = new CorbaClient();
        try {
            client.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void run(Corba corba){
        boolean stop = false;
        Scanner scan = new Scanner(System.in);
        String username = "";

        while (!stop) {
            String input = scan.nextLine();
            String[] split = input.split(" ");
            if (split.length <= 0) {
                System.out.println("Input error!");
            } else {
                switch (split[0]) {
                    case "register":
                        System.out.println("username:");
                        String m_username = scan.nextLine();

                        System.out.println("password:");
                        String m_pass = scan.nextLine();
                        int i = corba.regist(m_username, m_pass);
                        switch (i) {
                            case 0:
                                System.out.println("Success!");
                                break;
                            case -1:
                                System.out.println("The username has been used!");
                                break;
                            case -2:
                                System.out.println("Unknown error!");
                                break;
                        }

                        break;
                    case "login":
                        System.out.println("username:");
                        String n_username = scan.nextLine();

                        System.out.println("password:");
                        String n_pass = scan.nextLine();
                        int k = corba.login(n_username, n_pass);
                        switch (k) {
                            case 0:
                                System.out.println("User " + n_username + " has logged in");
                                username = n_username;
                                break;
                            case -1:
                                System.out.println("Wrong password!");
                                break;
                            case -2:
                                System.out.println("The username " + n_username + " has not been registered!");
                                break;
                        }
                        break;
                    case "add":
                        if ("" == username) {
                            System.out.println("You have not logged in!");
                        } else {
                            System.out.println("Input start time in this type yyyy/MM/dd HH:mm:ss.SSS");
                            String starttime = scan.nextLine();
                            System.out.println("Input end time in this type yyyy/MM/dd HH:mm:ss.SSS");
                            String endtime = scan.nextLine();
                            System.out.println("Input the label information");
                            String label = scan.nextLine();
                            int p = corba.add(username, starttime, endtime, label);
                            switch (p) {
                                case 0:
                                    System.out.println("Success!");
                                    break;
                                case -2:
                                    System.out.println("Unknow error!");
                                    break;
                                case -9:
                                    System.out.println("Unable to initilize the item!");
                                    break;
                            }
                     }
                        break;
                    case "query":
                        if ("" == username) {
                            System.out.println("You have not logged in!");
                        } else {

                            System.out.println("Input start time in this type yyyy/MM/dd HH:mm:ss.SSS");
                            String starttime = scan.nextLine();
                            System.out.println("Input end time in this type yyyy/MM/dd HH:mm:ss.SSS");
                            String endtime = scan.nextLine();
                            Date date1 = null;
                            String p = corba.query(username, starttime, endtime);
                            if(p == null) {
                                System.out.println("Unknown error!");
                            } else {
                                System.out.println(p);
                                System.out.println("Query finished");
                            }
                        }
                        break;
                    case "delete":
                        if ("" == username) {
                            System.out.println("You have not logged in!");
                        } else {
                            System.out.println("Input the id of the item you want to delete:");
                            Long id = scan.nextLong();
                            int m = corba.delete(username, id);
                            switch (m) {
                                case 0:
                                    System.out.println("Success!");
                                    break;
                                case -1:
                                    System.out.println("The item is not found");
                                    break;
                                case -2:
                                    System.out.println("Unknown error!");
                                    break;
                            }
                        }
                        break;
                    case "clear":
                        if ("" == username) {
                            System.out.println("You have not logged in!");
                        } else {
                            System.out.println("Are you going to clear the items list? Y/N");
                            String pandaun = scan.nextLine();
                            switch (pandaun) {
                                case "N":
                                case "n":
                                    System.out.println("Canceled");
                                    break;
                                case "Y":
                                case "y":
                                    int l = corba.clear(username);
                                    if(l == -1) {
                                        System.out.println("Unknown username!");
                                        break;
                                    }
                                    System.out.println("Success!");
                                    break;
                            }
                        }
                        break;
                    case "logout":
                        if ("" == username) {
                            System.out.println("You have not logged in!");
                        } else {
                            System.out.println("Are you ready to log out? Y/N");
                            String p = scan.nextLine();
                            switch (p) {
                                case "N":
                                case "n":
                                    System.out.println("Canceled");
                                    break;
                                case "Y":
                                case "y":
                                    username = null;
                                    System.out.println("Logged out");
                                    break;
                            }
                        }
                        break;
                    case "quit":
                        System.out.println("Are you ready to quit? Y/N");
                        String q = scan.nextLine();
                        switch (q) {
                            case "N":
                            case "n":
                                System.out.println("Canceled");
                                break;
                            case "Y":
                            case "y":
                                stop = true;
                                System.out.println("quit!");
                                break;
                        }
                        break;

                    case "help":
                        System.out.println("Input register for User Registration");
                        System.out.println("Input login for User Login");
                        System.out.println("Input logout for User Logout");
                        System.out.println("Input add for Adding Items");
                        System.out.println("Input query for Querying Items");
                        System.out.println("Input delete for Deleting Items");
                        System.out.println("Input clear for Clearing Items");
                        System.out.println("Input quit to stop");
                        System.out.println("Input help for Help");


                        break;
                    default:
                        System.out.println("Error input");
                        break;


                }
            }
        }
    }
}
