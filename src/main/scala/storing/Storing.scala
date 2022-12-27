package storing

import fileReader.service._
import fileReader.model._



final case class Storage[A](storage : List[A])

object Storing {

  def storingCountries[A]() = {
    Storage(CSV.read("src/main/data/countries.csv" , Country.fromCsvLine).lines)
  }

  def storingAirports[A]() = {
    Storage(CSV.read("src/main/data/airports.csv", Airport.fromCsvLine).lines)
  }

  def storingRunways[A]() = {
    Storage(CSV.read("src/main/data/runways.csv", Runway.fromCsvLine).lines)
  }

}