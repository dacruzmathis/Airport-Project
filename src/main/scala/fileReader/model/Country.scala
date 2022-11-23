package fileReader.model

import scala.util.Try

sealed trait Country
final case class CountryTest(code: String, name : String, continent: String) extends Country

object CountryTest {
  def fromCsvLine(line : Array[String]): Option[CountryTest] = {
    line.size match {
      case _ => parseCountry(line)
      //case _ => None
    }
  }

  def parseCountry(line: Array[String]) = {
    (Try(line(1)).toOption, Try(line(2)).toOption, Try(line(3)).toOption) match {
      case (Some(code), Some(name), Some(continent)) => Some(CountryTest(code, name, continent))
      case _ => None
    }
  }
}