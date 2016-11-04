package org.usagram.aggregatesandeventsourcing.domain.user

import org.usagram.aggregatesandeventsourcing.domain._

class UserRepository extends Repository[UserId, UserState, User]
