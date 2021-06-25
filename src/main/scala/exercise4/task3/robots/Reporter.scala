package exercise4.task3

import zio.ZIO

class Reporter(val name: String) extends Robot {

  /** Whenever there are news, proclaims them (outputs the string to the console).
    * For simplicity, runs synchronously in the main fiber.
    */
  override def work(): ZIO[MyEnv, Any, Unit] = ???
}
