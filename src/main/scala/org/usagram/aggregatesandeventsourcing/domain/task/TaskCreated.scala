package org.usagram.aggregatesandeventsourcing.domain.task

import org.usagram.aggregatesandeventsourcing.domain._
import org.usagram.aggregatesandeventsourcing.domain.user._

case class TaskCreated(subject: String, description: Option[String], assigneeId: Option[UserId]) extends Event
