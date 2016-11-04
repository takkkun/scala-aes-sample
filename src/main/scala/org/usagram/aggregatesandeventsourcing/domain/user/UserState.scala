package org.usagram.aggregatesandeventsourcing.domain.user

import org.usagram.aggregatesandeventsourcing.domain._

class UserState private (
    val name: Option[String] = None
) extends State[UserState] {
  def eventHandlers = {
    case UserRegistered(_name) =>
      copy(name = Some(_name))
  }

  private def copy(
    name: Option[String] = this.name
  ): UserState =
    new UserState(name)
}

object UserState extends StateConstruction[UserState] {
  def construct(events: Seq[Event]) =
    events.foldLeft(new UserState) { (state, event) => state.apply(event) }
}
