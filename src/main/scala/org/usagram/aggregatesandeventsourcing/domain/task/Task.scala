package org.usagram.aggregatesandeventsourcing.domain.task

import org.usagram.aggregatesandeventsourcing.domain._
import org.usagram.aggregatesandeventsourcing.domain.user._

class Task private (
    val id:      TaskId,
    val state:   TaskState,
    val changes: Seq[Event]
) extends Aggregate[TaskId, TaskState, Task] {
  val subject = state.subject getOrElse {
    throw new Exception
  }

  val description = state.description

  val assigneeId = state.assigneeId

  def assign(assignee: User): Task =
    apply(TaskAssigned(state.assigneeId, assignee.id))

  def unassign(): Task = {
    val assigneeId = state.assigneeId getOrElse {
      throw new Exception("assignee is null")
    }

    apply(TaskUnassigned(assigneeId))
  }
}

object Task extends AggregateConstruction[TaskId, TaskState, Task] {
  def construct(id: TaskId, state: TaskState) =
    new Task(id, state, Seq.empty)

  protected[domain] def construct(id: TaskId, state: TaskState, changes: Seq[Event]) =
    new Task(id, state, changes)

  def create(subject: String, description: Option[String], assignee: Option[User]): Task = {
    val id = TaskId.generate()
    val initialEvents = Seq(TaskCreated(subject, description, assignee.map(_.id)))
    val state = TaskState.construct(initialEvents)
    construct(id, state, initialEvents)
  }
}
