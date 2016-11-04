package org.usagram.aggregatesandeventsourcing.domain.task

import org.usagram.aggregatesandeventsourcing.domain._

import java.util.UUID

case class TaskId(value: UUID) extends Identity[UUID]

object TaskId {
  def generate(): TaskId =
    TaskId(UUID.randomUUID)

  def fromString(string: String): TaskId =
    TaskId(UUID.fromString(string))
}
