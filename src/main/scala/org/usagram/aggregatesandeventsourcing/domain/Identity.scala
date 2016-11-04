package org.usagram.aggregatesandeventsourcing.domain

trait Identity[+A] {
  val value: A
}
