package corba.server;

import CorbaApp.Corba;
import CorbaApp.CorbaHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Properties;

/**
 * Created by channel on 2017/5/31.
 */
public class CorbaServer {


    public void run(String[] args) throws Exception {
        //生成一个对象请求代理（ORB），并初始化
        Properties props = new Properties();
        //初始化端口，默认的端口是900
        props.put("org.omg.CORBA.ORBInitialPort", "1050");
        //绑定到服务器
        props.put("org.omg.CORBA.ORBInitialHost", "localhost");
        //初始化ORB
        ORB orb = ORB.init(args, props);
        // get reference to rootpoa & activate the POAManager
        //获取一个根POA引用，并激活POA管理器
        POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        poa.the_POAManager().activate();
        //实例化一个HelloImpl对象
        CorbaImpl corba = new CorbaImpl();
        // 从servant获得一个对象引用
        org.omg.CORBA.Object ref = poa.servant_to_reference(corba);
        Corba href = CorbaHelper.narrow(ref);
        // 获得命名上下文
        org.omg.CORBA.Object objref = orb.resolve_initial_references("NameService");
        // 使用NamingContextExt 它是INS（Interoperable Naming Service，协同命名规范）的一部分
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objref);
        //绑定一个对象引用，以便客户端可以调用
        String name = "Corba";
        NameComponent[] nc = ncRef.to_name(name);
        ncRef.rebind(nc, href);
        System.out.println("CorbaServer ready and waiting......");

        //运行ORB
        orb.run();
    }

    public static void main(String[] args) {
        CorbaServer server = new CorbaServer();
        try {
            server.run(args);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


}
