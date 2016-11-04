package org.usagram.aggregatesandeventsourcing.domain.user

import org.usagram.aggregatesandeventsourcing.domain._

class User private (
    val id:      UserId,
    val state:   UserState,
    val changes: Seq[Event]
) extends Aggregate[UserId, UserState, User] {
  val name = state.name getOrElse {
    throw new Exception("")
  }
}

object User extends AggregateConstruction[UserId, UserState, User] {
  def construct(id: UserId, state: UserState) =
    new User(id, state, Seq.empty)

  protected[domain] def construct(id: UserId, state: UserState, changes: Seq[Event]) =
    new User(id, state, changes)

  def register(name: String): User = {
    val id = UserId.generate()
    val initialEvents = Seq(UserRegistered(name))
    val state = UserState.construct(initialEvents)
    construct(id, state, initialEvents)
  }
}
