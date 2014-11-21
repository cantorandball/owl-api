import com.cantorandball.owl.prototype.api._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {

  def servlet = new ScalatraServlet
                           with IndexEndpoint
                           with H2SlickDatabase
                           with H2SlickDriver

  override def init(context: ServletContext) {
    context.mount(servlet, "/*")
  }

}
