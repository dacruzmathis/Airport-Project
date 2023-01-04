package storing

import fileReader.model._
import scalikejdbc._
import scalikejdbc.StringSQLRunner._
object Database {


  Class.forName("org.h2.Driver")
  ConnectionPool.singleton("jdbc:h2:mem:hello", "user", "pass")
  implicit val session = AutoSession

  def readOptionDataBase(query2Selection : Int) = {
    query2Selection match {
      case 1 => tableDescription()
      case 2 => queryExamples()
      case _ => List("Please make a correct choice")
    }
  }

  def InitTables(countries : Storage[Country] , airports : Storage[Airport] , runways : Storage[Runway]) = {
    createTableRunway()
    createTableCountry()
    createTableAirport()

    insertInCountry(countries)
    insertInAirport(airports)
    insertInRunways(runways)
  }

  def createTableCountry() = {
    sql"""
    create table country (
    countryCode varchar(5) not null primary key,
    countryName varchar(100) not null,
    continent varchar(20) not null,
    countryLink varchar(1000)
  )
  """.execute.apply()
  }

  def createTableAirport() = {
    sql"""
    create table airport(
    airId varchar(10) not null primary key,
    airTypeOf varchar(50) not null,
    airName varchar(100) not null,
    countryCode varchar(5) not null
  )
  """.execute.apply()
  }

  def createTableRunway() = {
    sql"""
    create table runway(
    airportRef varchar(10) not null,
    airportId  varchar(10) not null,
    runSurface varchar(100) not null,
    runLen varchar(30) not null,
    runWid varchar(30) not null,
    runLeId varchar(100) not null
  )
  """.execute.apply()
  }

  def insertInCountry(countries : Storage[Country]) = {
    countries.storage
      .map(c => sql"insert into country (countryCode,countryName, continent,countryLink) values (${c.code} , ${c.name} , ${c.continent} , ${c.link})".update.apply())
  }

  def insertInAirport(airports : Storage[Airport]) = {
    airports.storage
      .map(a => sql"insert into airport (airId ,airTypeOf, airName ,countryCode) values (${a.id} , ${a.typeOf} , ${a.name} , ${a.countryCode})".update.apply())
  }

  def insertInRunways(runways : Storage[Runway]) = {
    runways.storage
      .map(r => sql"insert into runway (airportRef , airportId , runSurface , runLen , runWid , runLeId) values (${r.airportRef} , ${r.airportId} , ${r.surface} , ${r.len} , ${r.wid} , ${r.leId})".update.apply())
  }


  def tableDescription() = {
    val l = "\nTABLES DESCRIPTION\n" :: ("\nTABLE COUNTRY:\n" :: (tableDescriptionAux("country") ++ ("\nTABLE AIRPORT:\n" :: (tableDescriptionAux("airport") ++ ("\nTABLE RUNWAY:\n" :: (tableDescriptionAux("runway") ++ Nil))))))
    l
  }

  def tableDescriptionAux(tableName : String) = {
    println("")
    SQL(s"SHOW COLUMNS FROM $tableName").map(rs => rs.string(1) + ',' + rs.string(2)).list.apply()
  }
  def queryExamples() = { //french air + run 6 5 3 1
    val l = "Example of query\n" ::
      ("--------------\n" ::
        ("(1)\n" :: (queryExample1() ::
            ("\n--------------\n" ::
              ("(2)\n" :: (queryExample2() ::
                  ("\n--------------\n" ::
                    ("(3)\n" :: (queryExample3() ::
                        ("\n--------------\n" ::
                          ("(4)\n" :: (queryExample4() ::
                            Nil))))))))))))

    l
  }

  def queryExampleExec(query2Selection : String) = {
    query2Selection match {
      case "1" => execQuery(queryExample1())
      case "2" => execQuery(queryExample2())
      case "3" => execQuery(queryExample3())
      case "4" => execQuery(queryExample4())
      case _ => List("Please make a correct choice")
    }
  }

  def queryExample1() = {
      """SELECT c.countryName , a.airId , a.airName , a.airTypeOf , r.runSurface , r.runLen , r.runWid
        FROM airport a
        LEFT JOIN runway r
        ON a.airId = r.airportRef
        LEFT JOIN country c
        ON c.countryCode = a.countryCode
        WHERE a.countryCode = 'FR'
        ORDER BY a.airId
        """
  }

  def queryExample2() = { //type run per country
    """SELECT DISTINCT c.countryName , r.runSurface
            FROM airport a
            LEFT JOIN runway r
            ON a.airId = r.airportRef
            LEFT JOIN country c
            ON c.countryCode = a.countryCode
            WHERE r.runSurface IS NOT NULL
            ORDER BY c.countryName
            """
  }

  def queryExample3() = { //top 10 country air desc
    """SELECT TOP(10) c.countryName , COUNT(a.airId) as airportNumber
            FROM airport a
            LEFT JOIN country c
            ON c.countryCode = a.countryCode
            GROUP BY a.countryCode
            ORDER BY airportNumber DESC , c.countryName
            """
  }

  def queryExample4() = {  //common run lat
    """SELECT TOP(10) r.runLeId , COUNT(r.runLeId) as runLatCount
            FROM airport a
            LEFT JOIN country c
            ON c.countryCode = a.countryCode
            LEFT JOIN runway r
            ON a.airId = r.airportRef
            WHERE r.runSurface IS NOT NULL
            GROUP BY r.r.runLeId
            ORDER BY runLatCount DESC
            """
  }


  def prettyPrinterQueryResult(queryResult : List[String]) : String = queryResult match {
    case Nil => ""
    case t :: q => t + " | " + prettyPrinterQueryResult(q)
  }

  def execQuery(query : String) = {
    query.run
      .map(info => info.map(s => (s._1 + ": " + s._2)).toList)
      .map(l => prettyPrinterQueryResult(l))
  }

}
