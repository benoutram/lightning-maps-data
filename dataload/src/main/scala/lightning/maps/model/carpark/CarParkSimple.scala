package lightning.maps.model.carpark

import lightning.maps.scalaxb._
import java.util.Date
import lightning.maps.model.CanPersist

class CarParkSimple(value: CarPark) extends CanPersist {
  val ref: String = value.CarParkRef.toString()
  val name: String = value.CarParkName.toString()
  val address: String = value.Address
  val postcode: String = value.Postcode
  val notes: String = value.Notes
  val stayType: String = value.StayType.toString()
  val lastUpdated: Date = value.DateRecordLastUpdated.toGregorianCalendar.getTime
  val location: Option[(String, String)] = accessPoint(value.AccessPoints)

  def accessPoint(value: Seq[AccessPoints]): Option[(String, String)] = {
    val it = value.iterator filter mapType map accessPointVal
    it.isEmpty match {
      case true => None
      case false => Some(it.buffered.head)
    }
  }

  def mapType(value: AccessPoints): Boolean = {
    !value.GeocodeType.equals("Map")
  }

  def accessPointVal(value: AccessPoints): (String, String) = {
    (value.Easting, value.Northing)
  }

  def vals: List[(String, Any)] = {
    List((CarParkKeys.REF, ref),
      (CarParkKeys.NAME, name),
      (CarParkKeys.ADDRESS, address),
      (CarParkKeys.POSTCODE, postcode),
      (CarParkKeys.NOTES, notes),
      (CarParkKeys.STAY_TYPE, stayType),
      (CarParkKeys.LOCATION, location),
      (CarParkKeys.LAST_UPDATED, lastUpdated))
  }
}