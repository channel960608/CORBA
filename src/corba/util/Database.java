package corba.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Date;

/**
 * Created by channel on 2017/5/31.
 */
public class Database {
    HashMap<String,List<Item>> itemMap;
    HashMap<String,User> userMap;
    int nameMapSize;

    public Database(){
        itemMap = new HashMap<>();
        userMap = new HashMap<>();
        nameMapSize = 0;
    }

    public boolean duplicate(String m_name){
        Set<String> nameSet = userMap.keySet();

        for(String name:nameSet){
            if(m_name.equals(name)){
                return true;
            }
        }

        return false;
    }

    public Item getItem(String username, int id){
        try {
            List<Item> list = getItemList(username);
            for(Item it:list){
                if(id == it.getId())
                    return it;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * @param username
     * @param pass
     * @return  0 正常返回
     *          -1 用户名已存在
     *          -2 未知错误
     */
    public int addUser(String username,String pass) {
        if(duplicate(username)) {
            return -1;
        }
        try {
            User user = new User();
            user.setUsername(username);
            user.setPass(pass);
            userMap.put(username, user);
            itemMap.put(username, new LinkedList<>());
            return 0;
        } catch (Exception e) {
            return -2;
        }
    }

    /**
     *
     * @param username
     * @return User if it exists.
     *          Otherwise, it returns null
     */
    public User getUser(String username){
        return userMap.get(username);
    }

    public boolean userExist(String username) {
        return userMap.containsKey(username);
    }

    /**
     *
     * @param username
     * @param pass
     * @return 0 登录成功
     *          -2 不存在该用户
     *          -1 账号密码不正确
     */
    public int login(String username, String pass) {
        User m_user = getUser(username);
        if(m_user.equals(null)) {
            return -2;
        }
        if(!m_user.verify(pass)) {
            return -1;
        }
        return 0;
    }

    public List<Item> getItemList(String username) {
        try {
            List<Item> list=itemMap.get(username);
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Item> query(String username, Date start, Date end) {
        List<Item> list=getItemList(username);
        List<Item> list2 = new LinkedList<Item>();
        if(list==null)
            return null;
        for(Item it:list) {
            if(1 == it.compare(start, end)) {
                list2.add(it);
            }
        }
        return list2;

    }

    /**
     *
     * @param username
     * @param it
     * @return 0 正常
     *          -2 未知错误
     */
    public int addItem(String username, Item it) {
        try {
            List list = getItemList(username);
            list.add(it);
            return 0;
        } catch (Exception e) {
            return -2;
        }
    }

    /**
     *
     * @param username
     * @param id
     * @return 0 正常
     *          -1 没找到这个Item
     *          -2 未知错误
     */
    public int deleteItem(String username,Long id) {
        try {
            List<Item> list = getItemList(username);
            for(Item it : list) {
                if (id.equals(it.getId())) {
                    list.remove(it);
                    itemMap.replace(username, list);
                    return 0;
                }
            }

            return -1;
        } catch (Exception e) {
            return -2;
        }

    }

    /**
     *
     * @param username
     * @return 0 成功
     *          -1 用户不存在
     *
     */
    public int clear(String username) {

        if(!userMap.containsKey(username))
            return -1;

        itemMap.replace(username, new LinkedList<Item>());
        return 0;
    }

}
