package org.usagram.aggregatesandeventsourcing.domain

trait AggregateConstruction[ID <: Identity[_], S <: State[S], A <: Aggregate[ID, S, A]] {
  def construct(id: ID, state: S): A

  protected[domain] def construct(id: ID, state: S, changes: Seq[Event]): A
}
