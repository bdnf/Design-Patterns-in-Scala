package scala_patterns

//Encapsulation of Subject-Observer relationship in a single type
abstract class SelfType {
  //bounded abstact type members
  type S <: Subject
  type O <: Observer

  trait Subject {

    self: S =>   //additional for 'this' and alias as well
    private var observers: List[O] = List[O]()

    def addObserver(observer: O): Unit = {
      observers ::= observer
    }

    def notifyObservers(): Unit =
      observers.foreach(_.receiveUpdate(self))  //! self instead of this
  }

  trait Observer {
    def receiveUpdate(state: S): Unit
  }
}


object SelfTypeExample extends SelfType {
  type S = ExampleObservable
  type O = ExampleObserver

  class ExampleObservable(var label: String) extends Subject {
    protected var count = 0

    def inc(): Unit ={
      count +=1
      notifyObservers()
    }

  }

  trait ExampleObserver extends Observer{
    def receiveUpdate(state: ExampleObservable): Unit
  }
}



import scala.collection.mutable

class LabelObserver extends SelfTypeExample.ExampleObserver {
  private var counts = mutable.HashMap[String, Int]()
  def receiveUpdate(state: SelfTypeExample.ExampleObservable): Unit = {
    val count = counts.getOrElse(state.label, 0) +1
    counts.update(state.label, count)
    println(s"Received state update for : ${state.label}, count now is: ${counts.get(state.label)}")
  }
}

object SelfTypeDemo extends App {
  import scala_patterns.SelfTypeExample._

  val subjects = Vector(
    new ExampleObservable("first"),
    new ExampleObservable("second")
  )
  val observer = new LabelObserver

  subjects(0).inc()
  subjects(0).inc()
  subjects.foreach(_.addObserver(observer))
  subjects(1).inc()
  subjects(0).inc()
  subjects(0).inc()
  subjects(1).inc()
}
