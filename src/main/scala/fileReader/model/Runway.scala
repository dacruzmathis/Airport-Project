package fileReader.model

import scala.util.Try

sealed trait Runway
final case class RunwayTest(airportRef: String, surface : String, leIdent : String) extends Runway
//final case class Point3D(x: Long, y: Long, z: Long) extends Point


object RunwayTest {
  def fromCsvLine(line: Array[String]): Option[RunwayTest] = {
    line.size match {
      case _ => parseRunway(line)
      //case _ => None
    }
  }

  //pattern matching is forbose so you can implement detailed error msg if you feel like it
  def parseRunway(line: Array[String]) = {
    (Try(line(1)).toOption, Try(line(5)).toOption, Try(line(8)).toOption) match {
      case (Some(airportRef), Some(surface), Some(leIdent)) => Some(RunwayTest(airportRef, surface, leIdent))
      case _ => None
    }
  }
}