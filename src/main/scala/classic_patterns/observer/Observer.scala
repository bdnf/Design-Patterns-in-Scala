package classic_patterns.observer

class State[T](var source: T, var propertyName: String, var newValue: Any)

// observes objects of type T
trait Observer[T] {
  def receiveUpdate(args: State[T]): Unit
}

// can be observed
class Observable[T] {
  private var observers = List.empty[Observer[T]]

  def subscribe(observer: Observer[T]): Unit = {
    observers :+= observer
  }

  protected def notifyObservers(source: T, propertyName: String, newValue: Any): Unit = {

    observers foreach {
      _.receiveUpdate(new State[T](source, propertyName, newValue))
    }
  }
}

class Person extends Observable[Person] {
  private var age = 0

  def getAge: Int = age

  def setAge(age: Int): Unit = {
    if (this.age == age) return
    this.age = age
    notifyObservers(this, "age", age)
  }
}

object ObserverInfrastructureDemo { // Observer<Foo>
  def main(args: Array[String]): Unit = {
    new ObserverInfrastructureDemo
  }
}

class ObserverInfrastructureDemo() extends Observer[Person] {
  val person = new Person
  person.subscribe(this)
  var i = 20
  while ( i < 24) {
    person.setAge(i)
    i += 1
  }

  override def receiveUpdate(args: State[Person]): Unit = {
    println("Person's " + args.propertyName + " has been changed to " + args.newValue)
  }
}

