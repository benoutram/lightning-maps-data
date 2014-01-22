package lightning.maps.service

import scala.annotation.implicitNotFound
import scala.xml.XML

import lightning.maps.logging.Loggable
import lightning.maps.scalaxb.CarParkDataImport
import lightning.maps.scalaxb.ScalaxbCarParkDataImportFormat

class XmlFileService extends Loggable {

  def deserialise(filename: String): CarParkDataImport = {
    debug("loading XML from file: " + filename)
    scalaxb.fromXML[CarParkDataImport](XML.load(getClass().getResourceAsStream("/"+filename)))
  }
}

object XmlFileService {
   private val service = new XmlFileService
  
   def deserialise(filename: String): CarParkDataImport = {
     service.deserialise(filename)
   }
}