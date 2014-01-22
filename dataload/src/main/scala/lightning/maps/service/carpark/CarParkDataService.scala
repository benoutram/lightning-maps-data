package lightning.maps.service.carpark

import lightning.maps.db.Mongo
import lightning.maps.logging.Loggable
import lightning.maps.model.carpark.CarParkSimple
import lightning.maps.scalaxb.CarPark
import lightning.maps.scalaxb.CarParkDataImport
import lightning.maps.service.XmlFileService
import lightning.maps.utils.CollectionProperties
import scalaxb.DataRecord
import lightning.maps.service.DataService

class CarParkDataService extends Loggable with DataService {

  def clearAll: Unit = {
    Mongo.drop(CollectionProperties.CAR_PARKS_COLLECTION_NAME)
  }

  def persist(value: CarParkSimple): Unit = {
    Mongo.insert(CollectionProperties.CAR_PARKS_COLLECTION_NAME, value.vals)
  }

  def transform(value: DataRecord[CarPark]): CarParkSimple = {
    new CarParkSimple(value.value)
  }

  def deserialise(filename: String): CarParkDataImport = {
    XmlFileService deserialise filename
  }

  def process(filename: String) {
    clearAll
    deserialise(filename).carparkdataimportoption.iterator map transform foreach persist
  }
}