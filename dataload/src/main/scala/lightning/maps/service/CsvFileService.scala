package lightning.maps.service

import java.io.InputStreamReader

import com.github.tototoshi.csv.CSVReader
import com.github.tototoshi.csv.defaultCSVFormat

import lightning.maps.logging.Loggable

class CsvFileService extends Loggable {

  def reader(filename: String): CSVReader = {
    CSVReader.open(new InputStreamReader(getClass().getResourceAsStream("/"+filename)))
  }
  
  def withHeaders(reader: CSVReader): List[Map[String, String]] = {
    reader.allWithHeaders
  }
  
  def deserialise(filename: String): List[Map[String, String]] = {
    withHeaders(reader(filename))
  }
}

object CsvFileService {
   private val service = new CsvFileService
  
   def deserialise(filename: String): List[Map[String, String]] = {
     service.deserialise(filename)
   }
}