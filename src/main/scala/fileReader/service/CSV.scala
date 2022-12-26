package fileReader.service

import java.nio.file.{Files, Path}
import scala.jdk.CollectionConverters.IteratorHasAsScala
import scala.io.Source


object CSV {

  final case class ReadResult[A](lines : List[A] , nbInvalidLine : Int)

  def read[A](fileName : String , parseLine : Array[String] => Option[A] , regex : String = ",") = {
    val file = Source.fromFile(fileName)
    val scalaFileContents : Iterator[String] = file.getLines().drop(1)
    val lineParsed = scalaFileContents.map(data => parseLine(data.split(regex).map(_.trim))).toList
    //val invalidLine : Int = lineParsed.count(x => x.isEmpty)
    ReadResult(lineParsed.flatten , 0)
  }

}
