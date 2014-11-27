import com.cantorandball.owl.prototype.api._
import org.scalatra._
import javax.servlet.ServletContext
import com.cantorandball.owl.prototype.api.video.VideoEndpoint
import com.cantorandball.owl.prototype.api.video.VideosDatabase
import com.cantorandball.owl.prototype.api.video.VideoJobEndpoint

class ScalatraBootstrap extends LifeCycle {

  def servlet = new ScalatraServlet
                           with IndexEndpoint
                           with VideoEndpoint
                           with VideoJobEndpoint
                           with H2SlickDatabase
                           with H2SlickDriver
                           with VideosDatabase

  override def init(context: ServletContext) {
    context.mount(servlet, "/*")
  }

}
