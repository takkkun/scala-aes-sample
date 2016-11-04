package org.usagram.aggregatesandeventsourcing.domain.task

import org.usagram.aggregatesandeventsourcing.domain._
import org.usagram.aggregatesandeventsourcing.domain.user._

class TaskState private (
    val subject:     Option[String] = None,
    val description: Option[String] = None,
    val assigneeId:  Option[UserId] = None
) extends State[TaskState] {
  def eventHandlers = {
    case TaskCreated(_subject, _description, _assigneeId) =>
      copy(subject = Some(_subject), description = _description, assigneeId = _assigneeId)
    case TaskAssigned(_, to) =>
      copy(assigneeId = Some(to))
    case TaskUnassigned(_) =>
      copy(assigneeId = None)
  }

  private def copy(
    subject:     Option[String] = this.subject,
    description: Option[String] = this.description,
    assigneeId:  Option[UserId] = this.assigneeId
  ): TaskState =
    new TaskState(subject, description, assigneeId)
}

object TaskState extends StateConstruction[TaskState] {
  def construct(events: Seq[Event]) =
    events.foldLeft(new TaskState) { (state, event) => state.apply(event) }
}
