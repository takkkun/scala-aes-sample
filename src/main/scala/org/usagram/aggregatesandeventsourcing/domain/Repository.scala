package org.usagram.aggregatesandeventsourcing.domain

trait Repository[ID <: Identity[_], S <: State[S], A <: Aggregate[ID, S, A]] {
  val eventStore = new JDBCEventStore

  def resolve(id: ID)(implicit stateConstruction: StateConstruction[S], aggregateConstruction: AggregateConstruction[ID, S, A]): A = {
    // val eventStream = eventStore.loadEventStream(id)
    import org.usagram.aggregatesandeventsourcing.domain.user._
    import org.usagram.aggregatesandeventsourcing.domain.task._

    val kondo = User
      .register("Kondo")

    val ando = User
      .register("Ando")

    val eventStream = new EventStream(4, Seq(
      TaskCreated("Persisted task", None, None),
      TaskAssigned(None, kondo.id),
      TaskAssigned(Some(kondo.id), ando.id),
      TaskUnassigned(ando.id)
    ))

    val state = stateConstruction.construct(eventStream.events)
    aggregateConstruction.construct(id, state)
  }

  def store(aggregate: A) {
    eventStore.appendEventsToStream(aggregate.id, 1, aggregate.changes)
  }
}
