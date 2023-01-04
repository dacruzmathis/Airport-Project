import storing._
import GUI.GUI

object Main {

  def main(args: Array[String]) : Unit = {

    val stoCountries = Storing.storingCountries()
    val stoAir = Storing.storingAirports()
    val stoRun = Storing.storingRunways()
    Database.InitTables(stoCountries , stoAir , stoRun)

    GUI.mainFrame(stoAir, stoCountries, stoRun)
  }
}
