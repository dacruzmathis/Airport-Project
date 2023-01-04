package fileReader.model

import scala.util.Try

final case class Airport(id : String , typeOf : String , name : String , countryCode : String)


object Airport {

  def fromCsvLine(line : Array[String]) : Option[Airport] = {
    line.size match {
      case _ => parseAirport((line))
    }
  }

  def parseAirport(line : Array[String]) = {
    line(8).length match {
      case 1 => None
      case 0 => None
      case _ => (Try(line(0)).toOption, Try(line(2)).toOption , Try(line(3)).toOption, Try(line(8)).toOption) match {
        case (Some(id),Some(typeOf), Some(name), Some(countryCode)) => if (name.contains("[Duplicate]")) None else Some(Airport(id, typeOf.substring(1, typeOf.length - 1), name.substring(1, name.length - 1), countryCode.substring(1, countryCode.length - 1)))
        case _ => None
      }
    }
  }

}
