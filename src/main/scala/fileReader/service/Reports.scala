package fileReader.service

import fileReader.model.{AirportTest, CountryTest, RunwayTest}

import java.io.File
import java.nio.file.{Files, Path}
import scala.::
import scala.annotation.tailrec
import scala.collection.immutable.ListMap
import scala.io.Source
import scala.jdk.CollectionConverters.IteratorHasAsScala
import scala.util.Try


final case class ReportsSelection[A](x: String)

object Reports {
  def readOption(airports: ReadResult[AirportTest], countries: ReadResult[CountryTest], runways: ReadResult[RunwayTest]) = {
    val reportSelection = scala.io.StdIn.readInt()
    reportSelection match {
      case 1 => topTenCountriesByNumberOfAirports(airports, countries)
      case 2 => runwayTypePerCountry(airports, runways)
      case 3 => topTenMostCommunRunwayLatitude(runways)
      case 4 => test(airports, countries)
    }
  }

  def test(airports: ReadResult[AirportTest], countries: ReadResult[CountryTest]) = {
    val airportsCountryCode =
      airports.lines
        .map(x => (x.name, x.countryCode))
        .groupBy { case (name, countryCode) => (countryCode) }
        .view.mapValues(row => row.map { case (countryCode, name) => name })
        .toMap
        .map(x => (x._1, x._2.size)
        )
    val tenLowest = ListMap(airportsCountryCode.toSeq.sortBy(_._2): _*).take(10).toList
    val tenHighest = reverse(ListMap(airportsCountryCode.toSeq.sortBy(_._2): _*).takeRight(10).toList)
    println(tenLowest)
    println(tenHighest)
  }

  /*def combineTwoCollections[T](initCollecion: ReadResult[T], joinCollection: ReadResult[T]) = {
    val mb = joinCollection.lines.toMap
    return initCollecion.lines.flatMap{case (ka,va,vva) => mb.get(ka).map(vb => (ka,va,vva,vb))}
  }*/

  def runwayTypePerCountry(airports: ReadResult[AirportTest], runways: ReadResult[RunwayTest])  = {
    val airportsCountryCode =
      airports.lines
        .map(x => (x.id, x.countryCode))
        .groupBy { case (id, countryCode) => (id) }
        .view.mapValues(row => row.map { case (id, countryCode) => countryCode })
        .toMap
        .map(x => (x._1, x._2.head)
        )
    println(airportsCountryCode)
  }

  def topTenMostCommunRunwayLatitude(runways: ReadResult[RunwayTest]) = {
    val runwayLatitude =
      runways.lines
        .map(x => (x.airportRef, x.surface, x.leIdent))
        .groupBy { case (airportRef, surface, leIdent) => (leIdent) }
        .view.mapValues(row => row.map { case (airportRef, surface, leIdent) => airportRef })
        .toMap
        .map(x => (x._1, x._2.size)
        )
    val tenHighest = reverse(ListMap(runwayLatitude.toSeq.sortBy(_._2): _*).takeRight(10).toList)
    println(tenHighest)
  }

  def topTenCountriesByNumberOfAirports(airports: ReadResult[AirportTest], countries: ReadResult[CountryTest]) = {
    val airportsCountryCode =
      airports.lines
        .map(x => (x.name, x.countryCode))
        .groupBy { case (name, countryCode) => (countryCode) }
        .view.mapValues(row => row.map { case (countryCode, name) => name })
        .toMap
        .map(x => (x._1, x._2.size)
        )
    val tenLowest = ListMap(airportsCountryCode.toSeq.sortBy(_._2): _*).take(10).toList
    val tenHighest = reverse(ListMap(airportsCountryCode.toSeq.sortBy(_._2): _*).takeRight(10).toList)
    println(tenLowest)
    println(tenHighest)
    //println(getCountryNameByCountryCode(countries, tenLowest, tenLowest.length))
    //test(tenHighest, countries)
    //println(countries.lines.map(x=>(x.code, x.name)).filter(x=>x._1 == tenHighest.map(x=>x._1)).map(x=>x._2))
    //getCountryNameByCountryCode(countries, tenHighest.map(x=>x._1))
  }
/*
  def getCountryNameByCountryCode(countries: ReadResult[CountryTest], countryCode: List[(String, Int)], x: Int) = countryCode match {
    case countryCode::tail => countries.lines.map(x => (x.code, x.name)).filter(x => x._1 == countryCode(x)._1)).map(x => x._2)
    case _ => countries.lines.map(x => (x.code, x.name)).filter(x => x._1 == countryCode(x)._1)).map(x => x._2)
  }
*/
  def reverse[T](l: List[T]): List[T] = l.foldLeft(List[T]())((acc, i) => i :: acc)

  def test(values: List[String], countries: ReadResult[CountryTest]) = values match {
    case _ if values.isEmpty => None
    case _ =>
    //getCountryNameByCountryCode(countries, values)
  }

  def average(values: Iterator[Double]) = values match {
    case _ if values.isEmpty => None
    case _ =>
      val tuple = values.map(x => (x, 1.0)).reduce((a, b) => (a._1 + b._1, a._2 + b._2))
      Some(tuple._1 / tuple._2)
  }
}