package org.usagram.aggregatesandeventsourcing.domain

package object task {
  implicit val taskConstruction: AggregateConstruction[TaskId, TaskState, Task] = Task

  implicit val taskStateConstruction: StateConstruction[TaskState] = TaskState
}
