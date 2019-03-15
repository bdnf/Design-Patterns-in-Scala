package state

import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.config.StateMachineBuilder
import org.springframework.statemachine.state.State
import org.springframework.statemachine.transition.Transition
import javax.swing.plaf.nimbus.State
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util
import java.util.stream.Collectors

import state.Events.Events


// plural because SSM defines a class called State already
//object States extends Enumeration {
//  type States = Value
//  val OFF_HOOK, ON_HOOK, CONNECTING, CONNECTED, ON_HOLD = Value
//}

sealed class States
case object OFF_HOOK extends States
case object ON_HOOK extends States
case object CONNECTING extends States
case object CONNECTED extends States
case object ON_HOLD extends States


object Events extends Enumeration {
  type Events = Value
  val CALL_DIALED, HUNG_UP, CALL_CONNECTED, PLACED_ON_HOLD, TAKEN_OFF_HOLD, LEFT_MESSAGE, STOP_USING_PHONE = Value
}

object SpringStatemachineDemo {

  val set = Set(OFF_HOOK, ON_HOOK)
  @throws[Exception]
  def buildMachine: StateMachine[States, Events] = {
     val builder: StateMachineBuilder.Builder[States, Events] = StateMachineBuilder.builder[States, Events]
    builder.configureStates.withStates.initial(OFF_HOOK).states(set.asInstanceOf[util.Set[States]])//(States.OFF_HOOK, States.ON_HOOK, States.CONNECTING, States.CONNECTED, States.ON_HOLD))
    builder.configureTransitions.withExternal.source(OFF_HOOK).event(Events.CALL_DIALED).target(CONNECTING).and.withExternal.source(OFF_HOOK).event(Events.STOP_USING_PHONE).target(ON_HOOK).and.withExternal.source(CONNECTING).event(Events.HUNG_UP).target(OFF_HOOK).and.withExternal.source(CONNECTING).event(Events.CALL_CONNECTED).target(CONNECTED).and.withExternal.source(CONNECTED).event(Events.LEFT_MESSAGE).target(OFF_HOOK).and.withExternal.source(CONNECTED).event(Events.HUNG_UP).target(OFF_HOOK).and.withExternal.source(CONNECTED).event(Events.PLACED_ON_HOLD).target(OFF_HOOK).and.withExternal.source(ON_HOLD).event(Events.TAKEN_OFF_HOLD).target(CONNECTED).and.withExternal.source(ON_HOLD).event(Events.HUNG_UP).target(OFF_HOOK)
    builder.build
  }

  // requires org.springframework.statemachine
  @throws[Exception]
  def main(args: Array[String]): Unit = {
    val machine = buildMachine
    machine.start
    val exitState = ON_HOOK
    val console = new BufferedReader(new InputStreamReader(System.in))
    while ( {
      true
    }) {
      val state = machine.getState
      println("The phone is currently " + state.getId)
      println("Select a trigger:")
      val ts: util.List[Transition[States, Events]] = machine.getTransitions.stream.filter((t) => t.getSource eq state).collect(Collectors.toList())
      var i = 0
      while ( {
        i < ts.size
      }) {
        System.out.println("" + i + ". " + ts.get(i).getTrigger.getEvent)

        {
          i += 1;
          i
        }
      }
      var parseOK = false
      var choice = 0
      do try {
        println("Please enter your choice:")
        choice = console.readLine.toInt
        parseOK = choice >= 0 && choice < ts.size
      } catch {
        case e: Exception =>
          parseOK = false
      } while ( {
        !parseOK
      })
      // perform the transition
      machine.sendEvent(ts.get(choice).getTrigger.getEvent)
      if (machine.getState.getId eq exitState) return
      println("And we are done!")
    }
  }
}
