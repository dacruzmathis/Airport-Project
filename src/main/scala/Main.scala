import fileReader.service.{Quering, Report}
import storing.Storing

import scala.sys.exit

object Main {

  def main(args: Array[String]): Unit = {


    val stoCountries = Storing.storingCountries()
    val stoAir = Storing.storingAirports()
    val stoRun = Storing.storingRunways()


    println(s"\nChoose an option: \n\t (1) Query \n\t (2) Reports \n\t (0) Exit")
    val selection = scala.io.StdIn.readInt()

    selection match {
      case 0 => exit

      case 1 =>
        println(s"\n ************* Query ************* \n")
        println(s"Enter a Country Name or Country Code:")
        Quering.readCountry(stoAir, stoCountries, stoRun)

      case 2 =>
        println(s"\n ************* Reports ************* \n")
        println(s"\nChoose an option: \n\t (1) 10 countries with highest/lowest number of airports \n\t (2) Type of runways per country \n\t (3) Top 10 most common runway latitude \n\t2")
        Report.readOption(stoAir, stoCountries, stoRun)

      case _ =>
        println("Input error -> Select only a number between 0 and 2 !")
    }
    main(args: Array[String])
  }
}
