package classic_patterns.mediator


class Person(var name: String) {
  var room: ChatRoomMediator = null
  private var chatLog = List[String]()

  def receive(sender: String, message: String): Unit = {
    val s = sender + ": '" + message + "'"
    System.out.println("[" + name + "'s chat session] " + s)
    chatLog ++= List(s)
  }

  def say(message: String): Unit = {
    room.broadcast(name, message)
  }

  def privateMessage(who: String, message: String): Unit = {
    room.message(name, who, message)
  }
}

class ChatRoomMediator {
  private var people = List[Person]()

  def broadcast(source: String, message: String): Unit = {

    for {
      person <- people
      if (person.name != source)
    } person.receive(source, message)
  }

  def join(p: Person): Unit = {
    val joinMsg = p.name + " joins the chat"
    broadcast("room", joinMsg)
    p.room = this
    people ++= List(p)
  }

  def message(source: String, destination: String, message: String) = {
    people.filter((p: Person) => p.name == destination).map((person: Person) => person.receive(source, message))
  }
}

object ChatRoomDemo {
  def main(args: Array[String]): Unit = {
    val room = new ChatRoomMediator
    val john = new Person("John")
    val jane = new Person("Jane")
    room.join(john) // no message here

    room.join(jane)
    john.say("hi room")
    jane.say("oh, hey john")
    val simon = new Person("Simon")
    room.join(simon)
    simon.say("hi everyone!")
    jane.privateMessage("Simon", "glad you could join us!")
  }
}