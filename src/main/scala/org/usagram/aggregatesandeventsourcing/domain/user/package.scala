package org.usagram.aggregatesandeventsourcing.domain

package object user {
  implicit val userConstruction: AggregateConstruction[UserId, UserState, User] = User

  implicit val userStateConstruction: StateConstruction[UserState] = UserState
}
