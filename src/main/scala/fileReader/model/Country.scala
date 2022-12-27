package fileReader.model

import scala.util.Try



final case class Country(code : String , name : String , continent : String , link : String)


object Country {

  def fromCsvLine(line : Array[String]) : Option[Country] = {
    line.size match {
      case _ => parseCountry1((line))
    }
  }

  private def parseCountry1(line : Array[String]) = {
    (Try(line(1)).toOption, Try(line(2)).toOption, Try(line(3)).toOption, Try(line(4)).toOption) match {
      case (Some (a), Some (b), Some (c), Some (d) ) => Some (Country (a.substring (1, a.length - 1), b.substring (1, b.length - 1), c.substring (1, c.length - 1), d.substring (1, d.length - 1) ) )
      case _ => None
    }
  }
}
