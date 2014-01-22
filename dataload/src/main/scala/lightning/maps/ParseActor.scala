package lightning.maps

import akka.actor.Actor
import spray.routing._
import spray.http._
import spray.http.MediaTypes._
import lightning.maps.service.accident.AccidentDataService
import lightning.maps.service.carpark.CarParkDataService
import lightning.maps.service.penalty.PenaltyPostcodeDataService
import spray.httpx.marshalling.ToResponseMarshallable.isMarshallable
import spray.routing.Directive.pimpApply
import spray.routing.directives.FieldDefMagnet.apply
import spray.routing.directives.ParamDefMagnet.apply
import lightning.maps.service.DataService
import lightning.maps.service.DataService
import lightning.maps.service.DataService

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class ParseActor extends Actor with ParseActorService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}

// this trait defines our service behavior independently from the service actor
trait ParseActorService extends HttpService {

  def accidentDataService: DataService = new AccidentDataService
  def carParkDataService: DataService = new CarParkDataService
  def penaltyPostcodeDataService = new PenaltyPostcodeDataService

  val myRoute = {
    pathPrefix("import") {
      pathEnd {
        // method tunneling via query param
        //(put | parameter('method ! "put")) {
        post {
          // form extraction from multipart or www-url-encoded forms
          formFields("service".?, "filename".?) { (service, filename) =>
            complete {
              parseDataFile(service, filename)
              <html>
                <body>
                  <h1>Say hello to <i>spray-routing</i> on <i>spray-can</i></h1>
                </body>
              </html>
            }
          }
        }
      }
    }
  }

  def parseDataFile(service: Option[String], filename: Option[String]) {
    service match {
      case Some("accident") => parseDataFile(accidentDataService, filename)
      case Some("carPark") => parseDataFile(carParkDataService, filename)
      case Some("penaltyPostcode") => parseDataFile(penaltyPostcodeDataService, filename)
      case Some(s) => println("Unknown service: " + s)
      case None => println("No service specified!")
    }
  }

  def parseDataFile(service: DataService, filename: Option[String]) {
    filename match {
      case (Some(name)) => service process name
      case None => println("No filename specified!")
    }
  }
}