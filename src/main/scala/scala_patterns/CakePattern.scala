package scala_patterns

trait Model {
  def startModelTier(): Unit
}
trait Controller {
  def startControllerTier(): Unit
}
trait View {
  def startViewTier(): Unit
}

trait Persistence extends Model {
  override def startModelTier(): Unit = println("Starting database ...")
}
trait Backend extends Controller{
  def startControllerTier(): Unit = println("Starting backend ...")
}
trait UI extends View{
  def startViewTier(): Unit = println("Starting UI ...")
}

trait MyApp { self: Persistence with Backend with UI =>

  def run() = {
    startControllerTier()
    startModelTier()
    startViewTier()
  }
}

case class CakePattern() extends MyApp with Persistence with Backend with UI

object Main extends App {
  CakePattern().run()
}
