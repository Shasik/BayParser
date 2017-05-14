package mainpackage;

import java.io.IOException;

public class ConsoleHelper {
  public ConsoleHelper() {}
  
  private static java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
  
  public static void WriteMessage(String message) {
    System.out.println(String.format("System: %s", new Object[] { message }));
  }
  
  public static int getIntInput() {
    try {
      return Integer.parseInt(reader.readLine());
    } catch (IOException e) {
      System.out.println("�������� ���� �����");
    }
    return -1;
  }
  
  public static String getStringInput() {
    try {
      return reader.readLine();
    } catch (IOException e) {
      System.out.println("�������� ���� ������");
    }
    return null;
  }
}