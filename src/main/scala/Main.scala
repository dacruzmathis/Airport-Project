import fileReader.service.{Quering, Report}
import storing.Storing
import GUI.GUI
import scala.sys.exit

object Main {

  def main(args: Array[String]): Unit = {

    val stoCountries = Storing.storingCountries()
    val stoAir = Storing.storingAirports()
    val stoRun = Storing.storingRunways()

    GUI.menu(stoAir, stoCountries, stoRun)

    main(args: Array[String])
  }
}
