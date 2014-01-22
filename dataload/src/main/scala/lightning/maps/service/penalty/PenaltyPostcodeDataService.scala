package lightning.maps.service.penalty

import lightning.maps.db.Mongo
import lightning.maps.logging.Loggable
import lightning.maps.model.penalty.PenaltyPostcode
import lightning.maps.model.penalty.PenaltyPostcodeKeys
import lightning.maps.service.CsvFileService
import lightning.maps.utils.CollectionProperties
import lightning.maps.service.DataService

class PenaltyPostcodeDataService extends Loggable with DataService {

    def clearAll: Unit = {
    Mongo.drop(CollectionProperties.PENALTY_POSTCODE_COLLECTION_NAME)
  }

  def persist(value: PenaltyPostcode): Unit = {
    Mongo.insert(CollectionProperties.PENALTY_POSTCODE_COLLECTION_NAME, value.vals)
  }
  
  /**
   * Remove District field from Map and convert to Map of Integers 
   */
  def transformPoints(value: Map[String, String]): Map[Int, Int] = {
    for ((k, v) <- value.-(PenaltyPostcodeKeys.DISTRICT) if !v.equals("-")) yield (k.toInt, v.toInt)
  }
  
  def transform(value: Map[String, String]): PenaltyPostcode = {
    new PenaltyPostcode(district = value(PenaltyPostcodeKeys.DISTRICT),
      points = transformPoints(value))
  }

  def deserialise(filename: String): List[Map[String, String]] = {
    CsvFileService deserialise filename
  }

  def process(filename: String) {
    clearAll
    deserialise(filename).iterator map transform foreach persist
  }
  
}