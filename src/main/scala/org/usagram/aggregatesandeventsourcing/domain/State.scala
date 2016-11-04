package org.usagram.aggregatesandeventsourcing.domain

trait State[S <: State[S]] {
  def eventHandlers: PartialFunction[Event, S]

  def apply(event: Event): S =
    if (eventHandlers.isDefinedAt(event)) {
      eventHandlers(event)
    }
    else {
      throw new Exception(s"could not handle $event")
    }
}
