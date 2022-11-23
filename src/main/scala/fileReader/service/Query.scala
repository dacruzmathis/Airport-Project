package fileReader.service

import fileReader.model.{CountryTest, AirportTest}

import java.io.File
import java.nio.file.{Files, Path}
import scala.::
import scala.annotation.tailrec
import scala.io.Source
import scala.jdk.CollectionConverters.IteratorHasAsScala
import scala.util.Try


final case class QuerySelection[A](x: String)

object Query {
  def readCountry(airports: ReadResult[AirportTest], countries: ReadResult[CountryTest])= {
    val country = scala.io.StdIn.readLine()
    if (country.length == 2){
      getAirportsByCountryCode(airports, s"\"${country}\"")
    }
    else{
      getAirportsByCountryName(airports, countries, s"\"${country}\"")
    }
  }

  def getAirportsByCountryCode(airports: ReadResult[AirportTest], countryCode: String) = {
    //val countryCodeAirports: Iterable[(String, Double)] =
    //TODO we remove lines with no departement
      //airports.lines
        //then we map the creation of the tuple, just uncomment
        //.map((airports.lines._1,e._2) => (e._1,e._2))
        //.map(airport => (name, code))
        //.map(fields =>
          //(fields(0), Try { fields(0) }.getOrElse(0.0))
        //)

    //countryCodeAirports
      //.groupBy { case (departement, rating) => departement }
      //.view.mapValues(row => row.map { case (departement, rating) => rating }).toMap
    //airports.lines.foreach(x=>println(x.n))
    /*val bufferedSource = io.Source.fromFile(airports)
    for (line <- bufferedSource.getLines) {
      val cols = line.split(",").map(_.trim)
      val nbCols = line.count(_ == ',')
      if(cols(8) == countryCode){
        println(s"${cols(3)}")
      }
    }*/
    println(airports.lines.map(x=>(x.name, x.countryCode)).filter(x=>x._2 == countryCode).map(x=>x._1))
  }

  def getAirportsByCountryName(airports: ReadResult[AirportTest], countries: ReadResult[CountryTest], countryName: String) = {
    val country = countries.lines.map(x=>(x.code, x.name)).filter(x=>x._2 == countryName).map(x=>x._1).head
    getAirportsByCountryCode(airports, s"${country}")
    /*val bufferedSource = io.Source.fromFile(countries);
    for (line <- bufferedSource.getLines) {
      val cols = line.split(",").map(_.trim)
      val nbCols = line.count(_ == ',')
      if(cols(2) == countryName){
        getAirportsByCountryCode(cols(1))
      }
    }*/
  }
}