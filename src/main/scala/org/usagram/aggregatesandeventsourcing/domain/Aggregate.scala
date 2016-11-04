package org.usagram.aggregatesandeventsourcing.domain

trait Aggregate[ID <: Identity[_], S <: State[S], A <: Aggregate[ID, S, A]] {
  val id: ID

  val state: S

  val changes: Seq[Event]

  protected def apply(event: Event)(implicit construction: AggregateConstruction[ID, S, A]): A =
    construction.construct(id, state(event), changes ++ Seq(event))
}
