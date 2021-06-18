package exercise3

package object temperature {
  type Temperature = Double

  object Locale extends Enumeration {
    type Locale = String
    val US = "US"
    val SCI = "SCI"
    val Other = "Other"
  }

  implicit val locale: Locale.Locale = Locale.Other

  def display(temp: Temperature)(implicit locale: Locale.Locale) = locale match {
    case Locale.US => s"${temp} °F"
    case Locale.SCI => s"${temp} °K"
    case Locale.Other => s"${temp} °C"
    case _ => "Locale can only be one of [US | SCI | Other]"
  }

  implicit class TemperatureClass(temp: Temperature) {
    val freezingPoint = 0.0
    val absoluteZero = -273.15

    def celsius: Temperature = temp
    def kelvin: Temperature = temp - 273.15 
    def fahrenheit: Temperature = (temp - 32) * 5/9

    def avg(other: Temperature): Temperature = (temp + other) / 2
  }
}