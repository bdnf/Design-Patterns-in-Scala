package classic_patterns.command

abstract class Command {
  def execute()
}
//Invoker
class Console( upCommand: Command,
               downCommand: Command,
               leftCommand: Command,
               rightCommand: Command) {


  def arrowUp() = upCommand.execute()
  def arrowDown() = downCommand.execute()
  def arrowLeft() = leftCommand.execute()
  def arrowRight() = rightCommand.execute()
}

class JumpCommand(character: Character) extends Command {
  override def execute(): Unit = character.moveUp()
}
class DownCommand(character: Character) extends Command {
  override def execute(): Unit = character.moveDown()
}
class LeftCommand(character: Character) extends Command {
  override def execute(): Unit = character.moveLeft()
}
class RightCommand(character: Character) extends Command {
  override def execute(): Unit = character.moveRight()
}

abstract class Character(name: String) {
  //Receiver
  def moveUp()
  def moveDown()
  def moveLeft()
  def moveRight()
}

class MarioReceiver(name: String) extends Character(name:String) {
  override def moveUp(): Unit = println("UP: jumping")

  override def moveDown(): Unit = println("DOWN: it's a 2D game. Just fall down")

  override def moveLeft(): Unit = println("LEFT: moving left")

  override def moveRight(): Unit = println("RIGHT: moving right")
}

object Main extends App {
  //Request
  println("placing Mario game cartridge")

  //Receiver
  val mario = new MarioReceiver("Mario")

  //Commands
  val moveMarioUp = new JumpCommand(mario)
  val moveMarioDown = new DownCommand(mario)
  val moveMarioLeft = new LeftCommand(mario)
  val moveMarioRight = new RightCommand(mario)

  //Invoker
  val oldGameBoy = new Console(moveMarioUp, moveMarioDown, moveMarioLeft, moveMarioRight)
  oldGameBoy.arrowDown()
  oldGameBoy.arrowRight()
  oldGameBoy.arrowRight()
  oldGameBoy.arrowUp()

}