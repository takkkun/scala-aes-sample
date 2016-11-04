package org.usagram.aggregatesandeventsourcing.domain.user

import org.usagram.aggregatesandeventsourcing.domain._

case class UserRegistered(name: String) extends Event
