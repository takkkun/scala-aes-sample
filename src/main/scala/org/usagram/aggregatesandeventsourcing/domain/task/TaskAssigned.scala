package org.usagram.aggregatesandeventsourcing.domain.task

import org.usagram.aggregatesandeventsourcing.domain._
import org.usagram.aggregatesandeventsourcing.domain.user._

case class TaskAssigned(from: Option[UserId], to: UserId) extends Event
