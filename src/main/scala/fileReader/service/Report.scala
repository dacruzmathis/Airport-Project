package fileReader.service

import fileReader.model._
import storing.Storage

object Report {

  def readOption(airports: Storage[Airport], countries: Storage[Country], runways: Storage[Runway], reportSelection: Int) = {
    //val reportSelection = scala.io.StdIn.readInt()
    reportSelection match {
      case 1 => topTenCountriesByNumberOfAirport(airports, countries)
      case 2 => runwayTypePerCountry(airports, countries, runways)
      case 3 => topTenMostCommunRunwayLatitude(runways)
      //case 4 => test(airports, countries)
    }
  }

  def topTenCountriesByNumberOfAirport(airports: Storage[Airport], countries: Storage[Country]) = {
    val gb = airports.storage
      .groupBy(a => a.countryCode)
      .map(x => (x._1, x._2.length))
      .toList

    val top10H = gb
      .sortBy(info => info._2)(Ordering[Int].reverse)
      .take(10)
      .map(info => countryFullNameByCountryCode(countries, info._1) + ": " + info._2 + " airports")

    val top10L = gb
      .sortBy(info => info._2)
      .take(10)
      .map(info => countryFullNameByCountryCode(countries, info._1) + ": " + info._2 + " airports")

    val l = "Highest number of airports:" :: ("" :: (top10H ++ ("______________________________" :: ("" :: ("Lowest number of airports:" :: ("" :: top10L))))))
    l
  }

  def countryFullNameByCountryCode(countries: Storage[Country], code: String) = {
    countries.storage.filter(c => c.code == code).map(c => c.name).head
  }

  def runwayTypePerCountry(airports: Storage[Airport], countries: Storage[Country], runways: Storage[Runway]) = {
    println("Type of runways per country:")
    println("")
    airports.storage
      .map(a => (a, Quering.getRunwaysByAirportId(a.id, runways)))
      .groupBy(tuple => tuple._1.countryCode)
      .map(x => (countryFullNameByCountryCode(countries, x._1), x._2.map(x => x._2.map(r => r.surface)).flatten.distinct))
      .toList
      .sortBy(tuple => tuple._1)(Ordering[String])
      .map(x => x._1 + ": " + ppTypeRun(x._2.sortBy(r => r)(Ordering[String])))
  }

  def ppTypeRun(l: List[String]): String = l match {
    case Nil => ""
    case t :: q => t + " | " + ppTypeRun(q)

  }

  def topTenMostCommunRunwayLatitude(runways: Storage[Runway]) = {
    println("1O Most Common Runway by Latitude :")
    println("")
    runways
      .storage
      .groupBy(r => r.leId)
      .map(info => (info._1, info._2.length))
      .toList
      .sortBy(info => info._2)(Ordering[Int].reverse)
      .take(10)
      .map(info => "Latitude: " + info._1 + " , Count of runways : " + info._2)
  }
}