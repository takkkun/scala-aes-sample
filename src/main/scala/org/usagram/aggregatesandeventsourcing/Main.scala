package org.usagram.aggregatesandeventsourcing

import org.usagram.aggregatesandeventsourcing.domain.user._
import org.usagram.aggregatesandeventsourcing.domain.task._

object Main {
  def main(args: Array[String]) {
    taskSample()
    taskRepoSample()
  }

  def taskSample() {
    val kondo = User.register("Kondo")

    val ando = User.register("Ando")

    val task = Task
      .create("task sample", None, None)
      .assign(kondo)
      .assign(ando)
      .unassign()

    println("# taskSample")
    printTask(task)
  }

  def taskRepoSample() {
    val taskRepo = new TaskRepository
    val task = taskRepo.resolve(TaskId.generate())

    println("# taskRepoSample")
    printTask(task)
  }

  def printTask(task: Task) {
    println(s"  id: ${task.id}")
    println(s"  subject: ${task.subject}")
    println(s"  description: ${task.description}")
    println(s"  assigneeId: ${task.assigneeId}")
    println("  changes:")

    task.changes.foreach { event =>
      println(s"    $event")
    }
  }
}
