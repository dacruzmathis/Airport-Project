package storingQuering

import fileReader.model._
import fileReader.service.CSV.ReadResult

import java.nio.file.attribute.FileStoreAttributeView
import scala.collection

object Quering {

  def readCountry(airports : Storage[Airport] , countries : Storage[Country] , runways : Storage[Runway]) = {
    val country = scala.io.StdIn.readLine()
    country.length match  {
      case 2 => getAirportsByCountryCode(airports , runways , country)
      case _ => getAirportsByCountryName(airports , runways , countries , country)

    }
  }

  def getAirportsByCountryCode(airports : Storage[Airport] , runways : Storage[Runway] , countryCode : String) = {
    val a = airports.storage
      .map(a => (a.id , a.name , a.countryCode))
      .sortWith((a,b) => a._2 < b._2)
      .filter(a => a._3 == countryCode)
      .map(airport => (airport._2 , getRunwaysByAirportId(airport._1 , runways) , ppRunways(getRunwaysByAirportId(airport._1 , runways))))
      .map(info => info._1 + " (runways : " + info._2.size + ")  [" + info._3 + "]")
      .foreach(println)

  }

  def getAirportsByCountryName(airports : Storage[Airport] , runways : Storage[Runway] , countries : Storage[Country] , countryName : String) = {
    val countryCode = countries.storage.map(c => (c.code , c.name)).filter(c => c._2 == countryName).map(c => c._1).head
    getAirportsByCountryCode(airports , runways , countryCode)
  }

  def getRunwaysByAirportId(airportID : String , runways  : Storage[Runway]) =  {
    runways
      .storage
      .filter(r => r.airportRef == airportID)
  }



  def ppRunways(runways : List[Runway]) : String = runways match {
    //case 0 => ""
    //case 1 => runways.next().airportId + "|"
    //case _ => runways.next().airportId + "|" + ppRunways(runways)
    case Nil => ""
    case t :: q => "Surface: " + t.surface + " , Length : " + t.len + " , Width : " + t.wid + " , Runway latitude : " + t.leId + " | " + ppRunways(q)
  }




}
