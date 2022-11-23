package fileReader.model

import scala.util.Try

sealed trait Airport
final case class AirportTest(id: String, name: String, countryCode : String) extends Airport

object AirportTest {
  def fromCsvLine(line: Array[String]): Option[AirportTest] = {
    line.size match {
      case _ => parseAirport(line)
      //case _ => None
    }
  }

  //pattern matching is forbose so you can implement detailed error msg if you feel like it
  def parseAirport(line: Array[String]) = {
    (Try(line(0)).toOption, Try(line(3)).toOption, Try(line(8)).toOption) match {
      case (Some(id), Some(name), Some(countryCode)) => Some(AirportTest(id, name, countryCode))
      case _ => None
      //case (None, Some(countryCode)) => None
      //case (None, None) => None
    }
  }
}