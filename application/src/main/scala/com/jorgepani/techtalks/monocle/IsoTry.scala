package com.jorgepani.techtalks.monocle

import monocle.Iso
import org.slf4j.LoggerFactory

case class Kilometer(whole: Int, fraction: Int)
case class Meter(whole: Int, fraction: Int)
case class Centimeter(whole: Int)

//For all those kinds of data being the same but represented differently
//Size units, money... Isomorphism
object IsoTry extends App {

  private val log = LoggerFactory.getLogger(this.getClass)

  // Get and reverseGet
  val isoCentToMeter = Iso[Centimeter, Meter] { cm =>
    Meter(cm.whole / 100, cm.whole % 100)
  } { m =>
    Centimeter(m.whole * 100 + m.fraction)
  }

  val isoMeterToKilometer = Iso[Meter, Kilometer] { meter =>
    Kilometer(meter.whole / 1000, meter.whole % 1000)
  } { kiloM =>
    Meter(kiloM.whole * 1000 + kiloM.fraction * 100, 0)
  }

  val isoCentToKilo = isoCentToMeter.composeIso(isoMeterToKilometer)
  val kiloToCentIso = isoCentToKilo.reverse

  val centimeter = kiloToCentIso.get(Kilometer(20, 3))

  log.info(s"Centimeters = $centimeter")

  //Centimetro convertido a metro y además modificado para añadirle lo que quiero
  //Centimeter to meter also having a bit of modification to add what I want
  val meterToCent = isoCentToMeter.modify(m => m.copy(m.whole + 3))(Centimeter(155))

  log.info(s"Centimeters = $meterToCent")

}
