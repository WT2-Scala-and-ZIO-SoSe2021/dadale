import org.scalatest.funspec.AnyFunSpec;
import exercise2.Ior;

class IorSpec extends AnyFunSpec {
  describe("right") {
    it("should...") {
      val a = Ior.right(2) // Right(2)
      val b = a.map(x => x * 4) // Right(8)

      val c = b.flatMap(_ => Ior.right("a string")) //Right("a string")

      val d = c.flatMap(_ =>
        Ior.left[String](new RuntimeException("a grave error"))
      ) //Left(java.lang.RuntimeException: a grave error)

      val e = d.map(x =>
        x + "something"
      ) //Left(java.lang.RuntimeException: a grave error)

      val both = Ior.both(
        new RuntimeException("not fatal"),
        21
      ) //Both(java.lang.RuntimeException: not fatal,21)
      val both1 =
        both.map((x) => x * 2) //Both(java.lang.RuntimeException: not fatal,42)
      val both2 = both.flatMap(_ =>
        Ior.left[Int](new RuntimeException("fatal error"))
      ) //Left(java.lang.RuntimeException: fatal error)
      val both3 = both.flatMap(_ =>
        Ior.right(480)
      ) //Both(java.lang.RuntimeException: not fatal,480)
      val both4 = both.flatMap(x =>
        Ior.both(new RuntimeException("another not fatal"), x * 3)
      )
    }
  }
}
