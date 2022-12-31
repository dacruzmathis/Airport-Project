import storing.Storing
import GUI.GUI

object Main {

  def main(args: Array[String]): Unit = {

    val stoCountries = Storing.storingCountries()
    val stoAir = Storing.storingAirports()
    val stoRun = Storing.storingRunways()

    GUI.mainFrame(stoAir, stoCountries, stoRun)

    //main(args: Array[String])
  }
}
