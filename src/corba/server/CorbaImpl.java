package corba.server;

import CorbaApp.CorbaPOA;
import corba.util.Database;
import corba.util.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by channel on 2017/5/31.
 */
public class CorbaImpl extends CorbaPOA{

    Database data = new Database();
    List<Item> list = new LinkedList();

    @Override
    public String corbaTest() {
        System.out.println("corbaTest");
        return "Test";
    }

    @Override
    public String test() {
        System.out.println("test");
        return "test";
    }



    @Override
   public short regist(String username, String pass) {
        return (short)data.addUser(username, pass);
    }

    @Override
   public short login(String username, String pass) {
        return (short)data.login(username, pass);
    }

    @Override
   public short add(String username, String date1, String date2, String label) {
        Item it = null;
        try {
            it = new Item(date1, date2, label);
            return (short) data.addItem(username, it);
        } catch (ParseException e) {
            return -2;
        }

    }

    @Override
  public String query(String username, String starttime, String endtime) {
        list = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        try {
            Date date1 = sdf.parse(starttime);
            Date date2 = sdf.parse(endtime);
            list = data.query(username, date1, date2);

            if (null == list) {
                return null;
            }
            else {
                String out="";
                for(Item it : list) {
                    out += "Id: " + it.getId()+ " ";
                    out += "" +
                            "";
                    out += "Start time: " + it.getStart().toString() + " ";
                    out += "" +
                            "";
                    out += "End time: " + it.getEnd() + " ";
                    out += "" +
                            "";
                    out += "Label: " + it.getLabel() + " ";
                    out += "" +
                            "";
                }
                return out;
            }
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public short delete(String username, long id) {
        return (short) data.deleteItem(username, id);
    }



    @Override
    public short clear(String username) {
        return (short) data.clear(username);
    }



    @Override
    public void shutdown() {

    }

    public Database getData() {
        return data;
    }



}

