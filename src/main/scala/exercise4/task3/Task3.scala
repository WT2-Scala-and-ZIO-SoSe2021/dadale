package exercise4.task3

import zio._

object Task3 extends zio.App {

  def f(): URIO[MyEnv, Unit] = {
    val elder1 = new Elder("Elder1")
    val elder2 = new Elder("Elder2")
    
    val worker1 = new Worker("Worker1")
    val worker2 = new Worker("Worker2")
  }

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = f()
    .provideCustomLayer()
    .exitCode
}
