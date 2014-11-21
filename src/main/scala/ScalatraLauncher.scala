import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext
import org.eclipse.jetty.server.ServerConnector
import org.scalatra.servlet.ScalatraListener

object ScalatraLauncher extends App {

  println("Launching Capability Measurements API.")

  val server = new Server
  val connector = new ServerConnector(server)
  connector.setPort(8080)
  server.addConnector(connector)

  val context = new WebAppContext
  context.setContextPath("/")
  context.setResourceBase("webapp")

  context.setEventListeners(Array(new ScalatraListener))
  server.setHandler(context)

  server.start
  server.join

}
