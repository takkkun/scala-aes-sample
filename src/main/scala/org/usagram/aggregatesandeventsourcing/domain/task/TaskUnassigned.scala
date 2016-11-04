package org.usagram.aggregatesandeventsourcing.domain.task

import org.usagram.aggregatesandeventsourcing.domain._
import org.usagram.aggregatesandeventsourcing.domain.user._

case class TaskUnassigned(from: UserId) extends Event
