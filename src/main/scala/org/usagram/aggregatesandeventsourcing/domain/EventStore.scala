package org.usagram.aggregatesandeventsourcing.domain

class EventStream(
    val version: Long,
    val events:  Seq[Event]
) extends Iterable[Event] {
  def iterator = events.iterator
}

trait EventStore {
  def loadEventStream(id: Identity[_]): EventStream

  def appendEventsToStream(id: Identity[_], version: Long, events: Seq[Event])
}

import java.sql.{Array => _, _}
import java.io._

class JDBCEventStore {
  def loadEventStream(id: Identity[_]) = {
    val key = identityToKey(id)

    connect { connection =>
      val statement = connection.prepareStatement(
        """
          |SELECT data
          |FROM events
          |WHERE key = ?
          |ORDER BY version
        """.stripMargin
      )

      statement.setString(1, key)

      val resultSet = statement.executeQuery()
      var events: List[Event] = List.empty

      while (resultSet.next()) {
        val data = resultSet.getBinaryStream(1)
        val ois = new ObjectInputStream(data)
        events ::= ois.readObject().asInstanceOf[Event]
        ois.close()
      }

      events
    }
  }

  def appendEventsToStream(id: Identity[_], version: Long, events: Seq[Event]) {
    if (events.isEmpty) return

    val key = identityToKey(id)

    connect { connection =>
      val statement = connection.prepareStatement(
        """
          |
        """.stripMargin
      )
    }
  }

  private def identityToKey(id: Identity[_]): String =
    s"${id.getClass.getName}:${id.value}"

  private def connect[A](process: Connection => A): A = {
    var connection: Option[Connection] = None

    try {
      connection = Some(DriverManager.getConnection("", "", ""))
      connection.map(process).get
    }
    finally {
      connection.foreach(_.close)
    }
  }
}
