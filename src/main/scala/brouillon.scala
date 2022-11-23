import java.io.File
import scala.io.Source

object Main extends App {

    def readCSV(path : String): Unit = {
        val file = new File(path)
        val SFC = Source.fromFile(file)
        SFC.getLines().foreach(println)
    }

    val airports = "C:/Users/W-book/Desktop/Devoir Projet/Functional Programing with Scala/Project/src/main/scala/airports.csv"
    val countries = "C:/Users/W-book/Desktop/Devoir Projet/Functional Programing with Scala/Project/src/main/scala/countries.csv"
    val runways = "C:/Users/W-book/Desktop/Devoir Projet/Functional Programing with Scala/Project/src/main/scala/runways.csv"

    readCSV(airports)
    readCSV(countries)
    readCSV(runways)
}