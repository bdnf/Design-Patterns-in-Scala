package observer

class PropertyChangedEventArgs[T](var source: T, var propertyName: String, var newValue: Any)

// observes objects of type T
trait Observer[T] {
  def handle(args: PropertyChangedEventArgs[T]): Unit
}

// can be observed
class Observable[T] {
  private var observers = List.empty[Observer[T]]

  def subscribe(observer: Observer[T]): Unit = {
    observers :+= observer
  }

  protected def propertyChanged(source: T, propertyName: String, newValue: Any): Unit = {

    for (o: Observer[T] <- observers) {
      o.handle(new PropertyChangedEventArgs[T](source, propertyName, newValue))
    }
  }
}

class Person extends Observable[Person] {
  private var age = 0

  def getAge: Int = age

  def setAge(age: Int): Unit = {
    if (this.age == age) return
    this.age = age
    propertyChanged(this, "age", age)
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

  override def handle(args: PropertyChangedEventArgs[Person]): Unit = {
    println("Person's " + args.propertyName + " has been changed to " + args.newValue)
  }
}

