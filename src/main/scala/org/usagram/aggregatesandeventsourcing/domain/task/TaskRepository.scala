package org.usagram.aggregatesandeventsourcing.domain.task

import org.usagram.aggregatesandeventsourcing.domain._

class TaskRepository extends Repository[TaskId, TaskState, Task]
