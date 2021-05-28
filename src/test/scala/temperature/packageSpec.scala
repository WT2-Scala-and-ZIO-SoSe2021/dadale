package temperature

import org.scalatest.funspec.AnyFunSpec
import temperature._
import exercise3.ScientificTemp

class packageSpec extends AnyFunSpec {
  describe("The Temperature package") {
    it("should convert") {
      val temp: Temperature = 273.15
        
      assert(temp.kelvin == 0)
      assert(temp.fahrenheit == 133.97222222222223)
      assert(temp.celsius == 273.15)
    }

    it("should calculate the average between two temperatures") {
      val avg = 1.celsius avg 10.fahrenheit
      assert(avg == -5.611111111111111)
      
    }

    it("displays the temperature in the locale string") {
      val temp: Temperature = 273.15

      assert(display(temp) == "273.15 °C")
    }
  }

  describe("ScientificTemp") {
      it("uses scientific locale") {
          val scientificTemp = new ScientificTemp()

          assert(scientificTemp.displayedZero == "0.0 °K")
          assert(scientificTemp.convertedToCelcius == - 273.15)
      }
  }
}