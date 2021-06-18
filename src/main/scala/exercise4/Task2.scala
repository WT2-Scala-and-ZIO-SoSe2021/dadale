package exercise4
import zio._
import zio.console._

object Task2 extends zio.App {
  // TODO empty trace und Ende falsch

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = (for {
    fib <- (ZIO.interrupt zipPar ZIO
      .fail("Error")).fork
    _ <- fib.join
  } yield ()).exitCode
  
}

// ╥
// ╠══╦══╗
// ║  ║  ║
// ║  ║  ╠─A checked error was not handled.
// ║  ║  ║ Error
// ║  ║  ║
// ║  ║  ║ Fiber:Id(1623345663229,4) was supposed to continue to:
// ║  ║  ║   a future continuation at zio.ZIO.run(ZIO.scala:1691)
// ║  ║  ║   a future continuation at zio.ZIO.bracket_(ZIO.scala:256)
// ║  ║  ║
// ║  ║  ║ Fiber:Id(1623345663229,4) execution trace:
// ║  ║  ║   at zio.ZIO$.effectSuspendTotal(ZIO.scala:2728)
// ║  ║  ║   at zio.ZIO.bracket_(ZIO.scala:256)
// ║  ║  ║   at zio.internal.FiberContext.evaluateNow(FiberContext.scala:627)
// ║  ║  ║
// ║  ║  ║ Fiber:Id(1623345663229,4) was spawned by:
// ║  ║  ║
// ║  ║  ║ Fiber:Id(1623345663069,1) was supposed to continue to:
// ║  ║  ║   a future continuation at ex4.services.StackTraceEx$.run(StackTraceEx.scala:9)
// ║  ║  ║   a future continuation at zio.ZIO.exitCode(ZIO.scala:574)
// ║  ║  ║
// ║  ║  ║ Fiber:Id(1623345663069,1) execution trace:
// ║  ║  ║   at zio.ZIO.zipWithPar(ZIO.scala:2242)
// ║  ║  ║   at ex4.services.StackTraceEx$.run(StackTraceEx.scala:8)
// ║  ║  ║
// ║  ║  ║ Fiber:Id(1623345663069,1) was spawned by:
// ║  ║  ║
// ║  ║  ║ Fiber:Id(1623345662524,0) was supposed to continue to:
// ║  ║  ║   a future continuation at zio.App.main(App.scala:59)
// ║  ║  ║   a future continuation at zio.App.main(App.scala:58)
// ║  ║  ║
// ║  ║  ║ Fiber:Id(1623345662524,0) ZIO Execution trace: <empty trace>
// ║  ║  ║
// ║  ║  ║ Fiber:Id(1623345662524,0) was spawned by: <empty trace>
// ║  ║  ▼
// ║  ║
// ║  ╠─An interrupt was produced by #1.
// ║  ║ No ZIO Trace available.
// ║  ▼
// ▼
