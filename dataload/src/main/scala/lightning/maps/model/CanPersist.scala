package lightning.maps.model

trait CanPersist {
  def vals: List[(String, Any)]
}