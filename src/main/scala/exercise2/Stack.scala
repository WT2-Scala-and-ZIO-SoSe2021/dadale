package exercise2

import scala.util.Try

trait StackLike[T] {
  def push(elem: T): StackLike[T]

  def pop(): Try[StackLike[T]]

  def top(): Option[T]

  def isEmpty: Boolean

  def reverse: StackLike[T]
}

class Stack[T](val tail: Option[Stack[T]], val elem: Option[T])
    extends StackLike[T] {

  def push(elem: T): StackLike[T] = new Stack(Option(this), Option(elem))

  def pop(): Try[StackLike[T]] = if (isEmpty)
    throw new Throwable("Stack is empty")
  else Try(tail.getOrElse(new Stack[T](None, None)))

  def top(): Option[T] = elem

  def isEmpty: Boolean = tail.isEmpty && elem.isEmpty

  def reverse: StackLike[T] = if (tail.isEmpty) new Stack[T](None, elem)
  else tail.get.reverseHelper(new Stack(None, elem))

  def reverseHelper(stack: StackLike[T]): StackLike[T] = if (isEmpty) stack
  else if (tail.isEmpty) stack.push(elem.get)
  else
    tail.get.reverseHelper(stack.push(elem.get))
}
