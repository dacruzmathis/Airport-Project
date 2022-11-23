package fileReader.service

import java.io.File
import java.nio.file.{Files, Path}
import scala.annotation.tailrec
import scala.io.Source
import scala.jdk.CollectionConverters.IteratorHasAsScala


final case class ReadResult[A](lines: Iterable[A], nbInvalidLine: Int)

object CSV {

  //fonction non recursive
  def read[A](fileName: String, parseLine: Array[String] => Option[A], regex: String = ","): ReadResult[A] = {

    val SourceCsv = Source.fromFile(fileName)
    val linesWithoutHeader: Iterator[String] = SourceCsv.getLines().drop(1)
    val parsedLine: Iterable[Option[A]] = linesWithoutHeader.map { x => parseLine(x.split(regex).map(_.trim)) }.to(Iterable)
    val invalidLine = parsedLine.count { x => x.isEmpty }

    ReadResult(parsedLine.flatten, invalidLine)
  }
}