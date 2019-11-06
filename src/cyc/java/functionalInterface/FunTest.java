package cyc.java.functionalInterface;

@FunctionalInterface
public interface FunTest
{
  void get();
  //public void set();
  default String getName1() {
      return "kevin";
  }
  default String getName2() {
      return "kevin";
  }
  static String getName3() {
      return "kevin";
  }
  static String getName4() {
      return "kevin";
  }
}
