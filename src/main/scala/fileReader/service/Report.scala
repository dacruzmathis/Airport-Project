package fileReader.service

import fileReader.model._
import storingQuering.{Storage , Quering}

import java.io.File
import java.nio.file.{Files, Path}
import scala.::
import scala.annotation.tailrec
import scala.collection.immutable.ListMap
import scala.io.Source
import scala.jdk.CollectionConverters.IteratorHasAsScala
import scala.util.Try

//final case class ReportSelection[A](x : String)

object Report {

  def readOption(airports: Storage[Airport], countries: Storage[Country], runways: Storage[Runway]) = {
    val reportSelection = scala.io.StdIn.readInt()
    reportSelection match {
      case 1 => topTenCountriesByNumberOfAirport(airports, countries)
      case 2 => runwayTypePerCountry(airports,countries,runways)
      case 3 => topTenMostCommunRunwayLatitude(runways)
      //case 4 => test(airports, countries)
    }
  }

  def topTenCountriesByNumberOfAirport(airports : Storage[Airport] , countries : Storage[Country]) = {
    val gb = airports.storage
      .groupBy(a => a.countryCode)
      .map(x => (x._1 , x._2.length))
      .toList

    val top10H = gb
      .sortBy(info => info._2)(Ordering[Int].reverse)
      .take(10)
      .map(info => countryFullNameByCountryCode(countries , info._1) + ": " + info._2 + " airports")

    val top10L = gb
      .sortBy(info => info._2)
      .take(10)
      .map(info => countryFullNameByCountryCode(countries, info._1) + ": " + info._2 + " airports")

    println("Highest number of airports:")
    println("")
    top10H.foreach(println)
    println("")
    println("______________________________")
    println("")
    println("Lowest number of airports:")
    println("")
    top10L.foreach(println)
  }

  def countryFullNameByCountryCode(countries : Storage[Country] , code : String) = {
    countries.storage.filter(c => c.code == code).map(c => c.name).head
  }

  def runwayTypePerCountry(airports: Storage[Airport], countries: Storage[Country] , runways : Storage[Runway]) = {
    println("Type of runways per country:")
    println("")
    airports.storage
      .map(a => (a , Quering.getRunwaysByAirportId(a.id ,runways)))
      .groupBy(tuple => tuple._1.countryCode)
      .map(x => (countryFullNameByCountryCode(countries ,x._1), x._2.map(x => x._2.map(r => r.surface)).flatten.distinct))
      .toList
      .sortBy(tuple => tuple._1)(Ordering[String])
      .map(x => x._1 + ": " + ppTypeRun(x._2.sortBy(r => r)(Ordering[String])))
      .foreach(println)
  }

  def ppTypeRun(l : List[String]) : String = l match {
    case Nil => ""
    case t :: q => t + " | " + ppTypeRun(q)

  }

  def topTenMostCommunRunwayLatitude(runways : Storage[Runway]) = {
    println("1O Most Common Runway by Latitude :")
    println("")
    runways
      .storage
      .groupBy(r => r.leId)
      .map(info => (info._1 , info._2.length))
      .toList
      .sortBy(info => info._2)(Ordering[Int].reverse)
      .take(10)
      .map(info => "Latitude: " + info._1 + " , Count of runways : " + info._2)
      .foreach(println)
  }
/*
  def test(airports: Storage[Airport], countries: Storage[Country]) = {
    val a = airports.storage.map(a => (a.name, a.countryCode , 1))

    val b = a.reduce((x, y) => (x , y) match {
      case (x._2 == y._2) && (x._1 != y._1) => (x._1 , x._2, x._3 + y._3)
    }
    )


  }*/

    //((a , b) => if((a._2 == b._2) && (a._1 != a._2))  Some(a._2 , a._3 + b._3) else (1,2))




}
