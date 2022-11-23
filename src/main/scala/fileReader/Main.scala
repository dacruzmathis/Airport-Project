package fileReader

import fileReader.model.{AirportTest, CountryTest, RunwayTest}
import fileReader.service.{CSV, Query, Reports}

import scala.sys.exit

object Main {

  def main(args: Array[String]): Unit = {

    val airports = CSV.read("src/main/scala/fileReader/CSV/airports.csv", AirportTest.fromCsvLine)
    val countries = CSV.read("src/main/scala/fileReader/CSV/countries.csv", CountryTest.fromCsvLine)
    val runways = CSV.read("src/main/scala/fileReader/CSV/runways.csv", RunwayTest.fromCsvLine)

    println(s"\nChoose an option: \n\t (1) Query \n\t (2) Reports \n\t (0) Exit")
    val selection = scala.io.StdIn.readInt()

    selection match {
      case 0 => exit

      case 1 =>
        println(s"\n ************* Query ************* \n")
        println(s"Enter a Country Name or Country Code:")
        Query.readCountry(airports, countries)

      case 2 =>
        println(s"\n ************* Reports ************* \n")
        println(s"\nChoose an option: \n\t (1) 10 countries with highest/lowest number of airports \n\t (2) Type of runways per country \n\t (3) Top 10 most common runway latitude \n\t (0) Return to Menu")
        Reports.readOption(airports, countries, runways)

      case _ =>
        println("Input error -> Select only a number between 0 and 2 !")
    }

    /*
    val result = countries
    result.lines.foreach(println)
    println(result.nbInvalidLine)
     */
    main(args: Array[String])
  }
}
