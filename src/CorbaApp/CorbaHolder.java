package CorbaApp;

/**
* CorbaApp/CorbaHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��Corba.idl
* 2017��6��1�� ������ ����03ʱ50��15�� CST
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
