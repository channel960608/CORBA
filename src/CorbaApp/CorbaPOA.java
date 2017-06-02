package CorbaApp;


/**
* CorbaApp/CorbaPOA.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从Corba.idl
* 2017年6月1日 星期四 下午03时50分15秒 CST
*/

public abstract class CorbaPOA extends org.omg.PortableServer.Servant
 implements CorbaApp.CorbaOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("corbaTest", new java.lang.Integer (0));
    _methods.put ("test", new java.lang.Integer (1));
    _methods.put ("regist", new java.lang.Integer (2));
    _methods.put ("login", new java.lang.Integer (3));
    _methods.put ("add", new java.lang.Integer (4));
    _methods.put ("query", new java.lang.Integer (5));
    _methods.put ("delete", new java.lang.Integer (6));
    _methods.put ("clear", new java.lang.Integer (7));
    _methods.put ("shutdown", new java.lang.Integer (8));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // CorbaApp/Corba/corbaTest
       {
         String $result = null;
         $result = this.corbaTest ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // CorbaApp/Corba/test
       {
         String $result = null;
         $result = this.test ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 2:  // CorbaApp/Corba/regist
       {
         String username = in.read_wstring ();
         String pass = in.read_wstring ();
         short $result = (short)0;
         $result = this.regist (username, pass);
         out = $rh.createReply();
         out.write_short ($result);
         break;
       }

       case 3:  // CorbaApp/Corba/login
       {
         String username = in.read_wstring ();
         String pass = in.read_wstring ();
         short $result = (short)0;
         $result = this.login (username, pass);
         out = $rh.createReply();
         out.write_short ($result);
         break;
       }

       case 4:  // CorbaApp/Corba/add
       {
         String username = in.read_wstring ();
         String date1 = in.read_wstring ();
         String date2 = in.read_wstring ();
         String label = in.read_wstring ();
         short $result = (short)0;
         $result = this.add (username, date1, date2, label);
         out = $rh.createReply();
         out.write_short ($result);
         break;
       }

       case 5:  // CorbaApp/Corba/query
       {
         String username = in.read_wstring ();
         String starttime = in.read_wstring ();
         String endtime = in.read_wstring ();
         String $result = null;
         $result = this.query (username, starttime, endtime);
         out = $rh.createReply();
         out.write_wstring ($result);
         break;
       }

       case 6:  // CorbaApp/Corba/delete
       {
         String username = in.read_wstring ();
         long id = in.read_longlong ();
         short $result = (short)0;
         $result = this.delete (username, id);
         out = $rh.createReply();
         out.write_short ($result);
         break;
       }

       case 7:  // CorbaApp/Corba/clear
       {
         String username = in.read_wstring ();
         short $result = (short)0;
         $result = this.clear (username);
         out = $rh.createReply();
         out.write_short ($result);
         break;
       }

       case 8:  // CorbaApp/Corba/shutdown
       {
         this.shutdown ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:CorbaApp/Corba:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Corba _this() 
  {
    return CorbaHelper.narrow(
    super._this_object());
  }

  public Corba _this(org.omg.CORBA.ORB orb) 
  {
    return CorbaHelper.narrow(
    super._this_object(orb));
  }


} // class CorbaPOA
