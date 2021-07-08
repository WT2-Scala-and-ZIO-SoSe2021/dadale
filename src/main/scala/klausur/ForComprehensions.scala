package klausur

import scala.util.Try

object ForComprehensions {
  def main(args: Array[String]): Unit = {
      for  {
        user <- fetchFromDB("","")
        address <- fetchAddress(user)
        shipTo <- shipTo(address)
      } yield shipTo  
  }

  def fetchFromDB(user: String, password: String): Try[Int] = ???
  def fetchAddress(user: Int): Try[Int] = ???
  def shipTo(address: Int): Try[Int] = ???
}
