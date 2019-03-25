package scala_patterns

trait Functor[Container[_]] {
  def map[T,Y](x: Container[T])(f:T => Y): Container[Y]
}

class ListFunctor extends Functor[List] {
  override def map[T, Y](x: List[T])(f: T => Y): List[Y] = x.map(f)
}

object FunctorDemo extends App {
  val listFunctor = new ListFunctor
  val numbers = List(1, 2, 3, 4, 5, 6)
  print(
    listFunctor.map(numbers)(i => i*2)
  )
}
