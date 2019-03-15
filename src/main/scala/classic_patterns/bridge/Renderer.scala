package classic_patterns.bridge

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

trait Renderer {
  def renderCircle(radius: Double): Unit
}

class VectorRenderer extends Renderer {
  override def renderCircle(radius: Double): Unit = {
    System.out.println("Drawing a circle of radius " + radius)
  }
}

class RasterRenderer extends Renderer {
  override def renderCircle(radius: Double): Unit = {
    System.out.println("Drawing pixels for a circle of radius " + radius)
  }
}

abstract class Shape(var renderer: Renderer) {
  def draw(): Unit
  def resize(factor: Double): Unit
}

class Circle @Inject()(renderer: Renderer) extends Shape(renderer) {
  var radius = 0.0

  def this(renderer: Renderer, radius: Double) = {
    this(renderer)
    this.radius = radius
  }

  override def draw(): Unit = {
    renderer.renderCircle(radius)
  }

  override def resize(factor: Double): Unit = {
    radius *= factor
  }
}

// Dependency injection with Guice
class ShapeModule extends AbstractModule {
  override protected def configure(): Unit = {
    bind(classOf[Renderer]).to(classOf[VectorRenderer])
  }
}

object BridgeDemo {
  def main(args: Array[String]): Unit = {

    //Manual Dependency Injection
    val rasterRenderer = new RasterRenderer()
    val vectorRenderer = new VectorRenderer()
    val circle = new Circle(vectorRenderer, 5)
    circle.draw()
    circle.resize(2)
    circle.draw()

    //Guice
    val injector = Guice.createInjector(new ShapeModule)
    val instance = injector.getInstance(classOf[Circle])
    instance.radius = 3
    instance.draw()
    instance.resize(2)
    instance.draw()
  }
}
