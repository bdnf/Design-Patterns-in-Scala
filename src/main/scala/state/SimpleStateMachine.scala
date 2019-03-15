package state


class CombinationLock(var combination: Array[Int]) {
  reset()
  var status: String = null
  private var digitsEntered = 0
  private var failed = false

  private def reset(): Unit = {
    status = "LOCKED"
    digitsEntered = 0
    failed = false
  }

  def enterDigit(digit: Int): Unit = {
    if (status == "LOCKED") status = ""
    status += digit
    if (combination(digitsEntered) != digit) failed = true
    digitsEntered += 1
    if (digitsEntered == combination.length) status = if (failed) "ERROR"
    else "OPEN"
  }
}
