package exercise3

import temperature.temperature._

class ScientificTemp {
    implicit val locale: Locale.Locale = Locale.SCI

    val displayedZero = display(0.0)
    val convertedToCelcius = 0.0.kelvin
}