package CorbaApp;


/**
* CorbaApp/CorbaOperations.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��Corba.idl
* 2017��6��1�� ������ ����03ʱ50��15�� CST
*/

public interface CorbaOperations 
{
  String corbaTest ();
  String test ();
  short regist (String username, String pass);
  short login (String username, String pass);
  short add (String username, String date1, String date2, String label);
  String query (String username, String starttime, String endtime);
  short delete (String username, long id);
  short clear (String username);
  void shutdown ();
} // interface CorbaOperations
