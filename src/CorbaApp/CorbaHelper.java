package CorbaApp;


/**
* CorbaApp/CorbaHelper.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��Corba.idl
* 2017��6��1�� ������ ����03ʱ50��15�� CST
*/

abstract public class CorbaHelper
{
  private static String  _id = "IDL:CorbaApp/Corba:1.0";

  public static void insert (org.omg.CORBA.Any a, CorbaApp.Corba that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static CorbaApp.Corba extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (CorbaApp.CorbaHelper.id (), "Corba");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static CorbaApp.Corba read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_CorbaStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, CorbaApp.Corba value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static CorbaApp.Corba narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof CorbaApp.Corba)
      return (CorbaApp.Corba)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      CorbaApp._CorbaStub stub = new CorbaApp._CorbaStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static CorbaApp.Corba unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof CorbaApp.Corba)
      return (CorbaApp.Corba)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      CorbaApp._CorbaStub stub = new CorbaApp._CorbaStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
