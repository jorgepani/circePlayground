package com.jorgepani.techtalks.monocle

import monocle.Iso

case class Kilometer(whole: Int, fraction: Int)

case class Meter(whole: Int, fraction: Int)
case class Centimeter(whole: Int)

object IsoTry extends App {

  //Para tipos de datos que son esencialmente lo mismo pero los representamos diferente

  // Get y reverseGet
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

  println(kiloToCentIso.get(Kilometer(20, 3)))

  println(isoCentToMeter.modify(m => m.copy(m.whole + 3))(Centimeter(155)))

}
