package fileReader

import fileReader.model.{Airport, Country, Runway}
import fileReader.service._
import storingQuering.{Storage, Storing}

import scala.util.Try

object Main {

  def main(args: Array[String]) = {
    val airport = CSV.read("src/main/data/airports.csv", Airport.fromCsvLine)
    val country = CSV.read("src/main/data/countries.csv", Country.fromCsvLine)
    val runway = CSV.read("src/main/data/runways.csv", Runway.fromCsvLine)
    val test = runway
    //test.lines.foreach(println)




  }

}
