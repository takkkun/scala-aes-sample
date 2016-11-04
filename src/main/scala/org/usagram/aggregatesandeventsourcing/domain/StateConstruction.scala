package org.usagram.aggregatesandeventsourcing.domain

trait StateConstruction[S <: State[S]] {
  def construct(events: Seq[Event]): S
}
