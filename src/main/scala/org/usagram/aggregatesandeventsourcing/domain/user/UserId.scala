package org.usagram.aggregatesandeventsourcing.domain.user

import org.usagram.aggregatesandeventsourcing.domain._

import java.util.UUID

case class UserId(value: UUID) extends Identity[UUID]

object UserId {
  def generate(): UserId =
    UserId(UUID.randomUUID)

  def fromString(string: String): UserId =
    UserId(UUID.fromString(string))
}
