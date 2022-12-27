package storingQuering

import fileReader.model._
import fileReader.service._
import storingQuering._
import scala.sys.exit
import scala.util.Try

object Main {

  def main(args: Array[String]) : Unit = {


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
        Quering.readCountry(stoAir, stoCountries , stoRun)

      case 2 =>
        println(s"\n ************* Reports ************* \n")
        println(s"\nChoose an option: \n\t (1) 10 countries with highest/lowest number of airports \n\t (2) Type of runways per country \n\t (3) Top 10 most common runway latitude \n\t2")
        Report.readOption(stoAir, stoCountries, stoRun)

      case _ =>
        println("Input error -> Select only a number between 0 and 2 !")
    }

     1
11








    /*
    //val runTest = Quering.getRunwaysByAirportId("6537" , stoRun)
    //runTest.foreach(println)
    //println(runTest.size)
    val a1 = Airport("6537","grass","Total Rf Heliport","US")
    val a2 = Airport("6524","grass","Lowell Field","US")
    val a3 = Airport("6524","grass","Lowell Field","US")

    val r1 = Runway("6536","00HI","80","80","ASPH-G","N")
    val r2 = Runway("6535","00GE","80","80","ASPH-G","N")
    val r3 = Runway("6532","00FD","80","80","ASPH-G","N")

    val lRun = List(r1,r2,r3)
    val lair = List(a1)

    val iteRun = Iterator(r1,r2,r3)
    val iteAir = Iterator(a2,a1,a3)


    val getRunFromListAir : List[List[Runway]] = iteAir.toList.map(a => Quering.getRunwaysByAirportId(a.id , stoRun))


    //stoAir.storage.groupBy(a => a.countryCode).map(x => (x._1 , x._2.length)).toList.sortBy(info => info._2).take(10).foreach(println)
    //stoAir.storage.filter(a => a.countryCode == "BL").foreach(println)


    //stoAir.storage.map(a => (a , Quering.getRunwaysByAirportId(a.id , stoRun))).groupBy(tuple => tuple._1.countryCode)
      //.map(x => '"' + x._1 + '"' + ',').foreach(print)
      //.map(x => (x._1, x._2.map(x => x._2.map(r => r.surface)).flatten.distinct)


    //stoAir.storage.map(a => (a , Quering.getRunwaysByAirportId(a.id , stoRun))).groupBy(tuple => tuple._1.countryCode)
    //.map(x => (Report.countryFullNameByCountryCode(stoCountries,x._1), x._2.map(x => x._2.map(r => r.surface)).flatten.distinct))




    /*
    val cc1 = List("CV","MA","AO","VN","IN","KW","ML","ID","JE","EG","BG","SG","SV","BD") //ok
    val cc1_2 = List("TC","TH","AT","GQ","TR")//ok
    val cc1_3 = List("HT","UM","MH","MY","RU","NI")//ok
    val cc1_4 = List("BZ","KP","VE","IL","GD","GI","TN","DM")//ok
    val cc1_5 = List("MO","PR","NF","TW","KN","PH","WF","JO","ME","ES","AZ","MR","SM","ZZ","BL","PK","NZ","GP","NA","JM","CM","US","GU","SB","MV","SI","CW","BH","VG","HK","SD","AD","RO","LU","VC","FO","GL","BW","CF","CI","KY","KG","LY","MM","MZ","IR","EH","IQ","BB","SZ","IE")//ok
    val cc2 = List("FK","NP","BE","AU","TZ","UY","SA","ZW","MD","HU","PG","AF","MU","SL","GT","BO","TM","NE","CL","FI","MN","NO","GG","EE","KM","LT","KS","ER","SH","SY","LC","CC","PL","CH","ST","NG","TF","KI","LV","UG","CY","MW","CG","MF","PM","IS","BI","SE","AE","KZ","LB","AR","BF","DJ","BA","FR","GM","HR","BS","RS","WS","GB","LS","UZ")//ok
    val cc2_2 = List("CD","MG","AI","YE","HN","IT","RE","DO","IO","GR","AS","ZA","GY","BY","LK","BT","OM","CK","KE","CZ","GH","MX","SK","MK","DZ","QA","CU","BJ","LA","TL","DK","VI","NL","CA","BM","JP","AW","TO","CN","VU","AL","ET","IM","SN","PE","BQ","NC","MP","GE","CR","VA","PS","EC","TV","LR","MS","TD","SC","DE","SR") //ok
        val cc2_3 = List("PF","AG","GW","FJ","CO","ZM","AQ","GF","NU","BN","RW","PT","SO","MT","PW","KH","SX","TJ","KR","SS","PY")//ok
        val cc2_4 = List("AM","CX","TT","UA","LI","BR")//ok
        val cc2_5 = List("NR","GA","TG","FM","GN","YT")//ok
        val cc2_6 = List("PA","MQ")//ok
        val cc2_7 = List("EU","MC")
     */

     */






  }

}
