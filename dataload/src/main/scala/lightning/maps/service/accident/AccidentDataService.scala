package lightning.maps.service.accident

import lightning.maps.logging.Loggable
import lightning.maps.db.Mongo
import lightning.maps.utils.CollectionProperties
import lightning.maps.service.CsvFileService
import lightning.maps.model.accident.Accident
import lightning.maps.model.accident.AccidentKeys
import lightning.maps.service.DataService

class AccidentDataService extends Loggable with DataService {

  def clearAll: Unit = {
    Mongo.drop(CollectionProperties.ACCIDENT_COLLECTION_NAME)
  }

  def persist(value: Accident): Unit = {
    Mongo.insert(CollectionProperties.ACCIDENT_COLLECTION_NAME, value.vals)
  }

  def transform(value: Map[String, String]): Accident = {
    new Accident(easting = value(AccidentKeys.EASTING),
      northing = value(AccidentKeys.NORTHING),
      latitude = value(AccidentKeys.LATITUDE),
      longitude = value(AccidentKeys.LONGITUDE),
      policeForce = value(AccidentKeys.POLICE_FORCE),
      severity = value(AccidentKeys.SEVERITY),
      numOfVehicles = value(AccidentKeys.NUM_OF_VEHICLES),
      numOfCasualties = value(AccidentKeys.NUM_OF_CASUALTIES),
      date = value(AccidentKeys.DATE),
      dayOfWeek = value(AccidentKeys.DAY_OF_WEEK),
      time = value(AccidentKeys.TIME),
      roadType = value(AccidentKeys.ROAD_TYPE),
      speedLimit = value(AccidentKeys.SPEED_LIMIT),
      lightConditions = value(AccidentKeys.LIGHT_CONDITIONS),
      weatherConditions = value(AccidentKeys.WEATHER_CONDITIONS),
      roadSurfaceConditions = value(AccidentKeys.ROAD_SURFACE_CONDITIONS),
      officersAttended = value(AccidentKeys.OFFICERS_ATTENDED))
  }

  def deserialise(filename: String): List[Map[String, String]] = {
    CsvFileService deserialise filename
  }

  def process(filename: String) {
    clearAll
    deserialise(filename).iterator map transform foreach persist
  }
}