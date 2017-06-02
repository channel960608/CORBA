package CorbaApp;


/**
* CorbaApp/CorbaOperations.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从Corba.idl
* 2017年6月1日 星期四 下午03时50分15秒 CST
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
