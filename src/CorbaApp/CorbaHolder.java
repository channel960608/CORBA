package CorbaApp;

/**
* CorbaApp/CorbaHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从Corba.idl
* 2017年6月1日 星期四 下午03时50分15秒 CST
*/

public final class CorbaHolder implements org.omg.CORBA.portable.Streamable
{
  public CorbaApp.Corba value = null;

  public CorbaHolder ()
  {
  }

  public CorbaHolder (CorbaApp.Corba initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = CorbaApp.CorbaHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    CorbaApp.CorbaHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return CorbaApp.CorbaHelper.type ();
  }

}
