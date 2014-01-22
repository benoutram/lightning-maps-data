package lightning.maps.model.penalty

import lightning.maps.model.CanPersist

class PenaltyPostcode(district: String,
  points: Map[Int, Int]) extends CanPersist {

  def vals: List[(String, Any)] = {
    List((PenaltyPostcodeKeys.DISTRICT, district), (PenaltyPostcodeKeys.POINTS_FREQUENCY, for ((k, v) <- points) yield (k.toString(), v)))
  }
}
