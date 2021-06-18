package exercise4
import zio._

object Task1 extends zio.App {

  trait Config
  trait Logging
  trait Parsing
  trait Database
  trait Serialization
  trait UserService

  val configLive: ULayer[Has[Config]] = ZLayer.succeed(new Config {})
  val userServiceLive: URLayer[Has[Database] with Has[Logging] with Has[
    Serialization
  ], Has[UserService]] =
    ((configLive >>> databaseLive) ++ loggingLive ++ (parsingLive >>> serializationLive)) >>> ZLayer
      .succeed(
        new UserService {}
      )

  val parsingLive: ULayer[Has[Parsing]] = ZLayer.succeed(new Parsing {})
  val serializationLive: URLayer[Has[Parsing], Has[Serialization]] =
    parsingLive ++ ZLayer.succeed(new Serialization {})
  val databaseLive: URLayer[Has[Config], Has[Database]] =
    configLive ++ ZLayer.succeed(new Database {})
  val loggingLive: ULayer[Has[Logging]] = ZLayer.succeed(new Logging {})

  type MyEnv = Has[Database]
    with Has[Logging]
    with Has[Serialization]
    with Has[UserService]
    with Has[Parsing]
    with Has[Config]

  def f(): URIO[MyEnv, Unit] = ???

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = f()
    .provideCustomLayer(
      configLive >+> databaseLive ++ loggingLive ++ parsingLive >+> serializationLive >+> userServiceLive
    )
    .exitCode
}
