package fileReader.service

import fileReader.model.{Airport, Country, Runway}
import storing.Storage

object Quering {

  def readCountry(airports: Storage[Airport], countries: Storage[Country], runways: Storage[Runway]) = {
    val country = scala.io.StdIn.readLine().toUpperCase()
    country.length match {
      case x if x <= 1 => countryFormatErrorMessage(1)
      case 2 => getAirportsByCountryCode(airports, runways, country)
      case _ => getAirportsByCountryName(airports, runways, countries, country)
    }
  }

  def getAirportsByCountryCode(airports: Storage[Airport], runways: Storage[Runway], countryCode: String) = {
    val a = airports.storage
      .map(a => (a.id, a.name, a.countryCode))
      .sortWith((a, b) => a._2 < b._2)
      .filter(a => a._3 == countryCode)
      .map(airport => (airport._2, getRunwaysByAirportId(airport._1, runways), ppRunways(getRunwaysByAirportId(airport._1, runways))))
      .map(info => info._1 + " (runways : " + info._2.size + ")  [" + info._3 + "]")
      .foreach(println)
  }

  def getAirportsByCountryName(airports: Storage[Airport], runways: Storage[Runway], countries: Storage[Country], partialCountryName: String) = {
    val integralCountryName = nameMatching(countries, partialCountryName)
    if (integralCountryName.isEmpty) {
      countryFormatErrorMessage(2)
    }
    else{
      val integralCountryNameNotNil = integralCountryName.head.name
      val countryCode = countries.storage.map(c => (c.code, c.name)).filter(c => c._2 == integralCountryNameNotNil).map(c => c._1).head
      getAirportsByCountryCode(airports, runways, countryCode)
    }
  }

  def nameMatching(countries: Storage[Country], countryMatch: String)={
    countries.storage.filter(c => c.name.toUpperCase().contains(countryMatch))
  }

  def getRunwaysByAirportId(airportID: String, runways: Storage[Runway]) = {
    runways
      .storage
      .filter(r => r.airportRef == airportID)
  }

  def ppRunways(runways: List[Runway]): String = runways match {
    //case 0 => ""
    //case 1 => runways.next().airportId + "|"
    //case _ => runways.next().airportId + "|" + ppRunways(runways)
    case Nil => ""
    case t :: q => "Surface: " + t.surface + " , Length : " + t.len + " , Width : " + t.wid + " , Runway latitude : " + t.leId + " | " + ppRunways(q)
  }

  def countryFormatErrorMessage(codeError : Int): Unit ={
    println("<countryFormatErrorMessage>")
    codeError match {
      case 1 => println("Input too short")
      case 2 => println("Input not found")
    }
  }
}
