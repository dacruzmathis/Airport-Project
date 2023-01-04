package fileReader.model

import scala.util.Try

final case class Runway(airportRef : String , airportId : String , surface : String , len : String , wid : String , leId : String)


object Runway {

  def fromCsvLine(line: Array[String]): Option[Runway] = {
    line.size match {
      case _ => parseRunway(line)
    }
  }


  def parseRunway(line: Array[String]) = {
    line(5).length match {
      case 1 => None
      case 0 => None
      case _ => line(4).length match {
        case 0 => None
        case _ => line(3).length match {
          case 0 => None
          case _ => (Try(line(1)).toOption, Try(line(2)).toOption, Try(line(5)).toOption, Try(line(3)).toOption, Try(line(4)).toOption , Try(line(8)).toOption) match {
            case (Some(airRef) , Some(airId) , Some(surface) , Some(len) , Some(wid) , Some(leId)) => (leId.length) match {
              case 0 => None
              case 1 => None
              case 2 => None
              case 3 => Some(Runway(airRef , airId.substring(1 , airId.length - 1) , surface.substring(1 , surface.length - 1) , len , wid , leId.charAt(1).toString))
              case _ => Some(Runway(airRef , airId.substring(1 , airId.length - 1) , surface.substring(1 , surface.length - 1) , len , wid , leId.substring(1 , leId.length - 1)))
            }
            case _ => None
          }
        }
      }
    }
  }

}
