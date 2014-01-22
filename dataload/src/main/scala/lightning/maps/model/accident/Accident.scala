package lightning.maps.model.accident

import lightning.maps.model.CanPersist

class Accident(easting: String,
  northing: String,
  policeForce: String,
  severity: String,
  numOfVehicles: String,
  numOfCasualties: String,
  date: String,
  dayOfWeek: String,
  time: String,
  roadType: String,
  speedLimit: String,
  lightConditions: String,
  weatherConditions: String,
  roadSurfaceConditions: String,
  officersAttended: String) extends CanPersist {

  def vals: List[(String, Any)] = {
    List((AccidentKeys.EASTING, easting),
      (AccidentKeys.NORTHING, northing),
      (AccidentKeys.POLICE_FORCE, policeForce),
      (AccidentKeys.SEVERITY, severity),
      (AccidentKeys.NUM_OF_VEHICLES, numOfVehicles),
      (AccidentKeys.NUM_OF_CASUALTIES, numOfCasualties),
      (AccidentKeys.DATE, date),
      (AccidentKeys.DAY_OF_WEEK, dayOfWeek),
      (AccidentKeys.TIME, time),
      (AccidentKeys.ROAD_TYPE, roadType),
      (AccidentKeys.SPEED_LIMIT, speedLimit),
      (AccidentKeys.LIGHT_CONDITIONS, lightConditions),
      (AccidentKeys.WEATHER_CONDITIONS, weatherConditions),
      (AccidentKeys.ROAD_SURFACE_CONDITIONS, roadSurfaceConditions),
      (AccidentKeys.OFFICERS_ATTENDED, officersAttended))
  }
}