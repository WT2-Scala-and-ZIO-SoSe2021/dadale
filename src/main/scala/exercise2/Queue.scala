package exercise2

import scala.util.Try

trait QueueLike[T] {
  def enqueue(elem: T): QueueLike[T]

  def dequeue(): Try[QueueLike[T]]

  def front(): Option[T]

  def isEmpty: Boolean
}

class Queue[T](
    val in: Option[StackLike[T]] = Option(new Stack[T](None, None)),
    val out: Option[StackLike[T]] = Option(new Stack[T](None, None))
) extends QueueLike[T] {

  def enqueue(elem: T): QueueLike[T] = if (isEmpty)
    new Queue(in, Option(out.get.push(elem)))
  else new Queue(Option(in.get.push(elem)), out)

  def dequeue(): Try[QueueLike[T]] = {
    if (isEmpty) throw new Throwable("Queue is empty")
    if (out.get.pop.get.isEmpty)
      Try(new Queue(Option(new Stack[T](None, None)), Option(in.get.reverse)))
    else Try(new Queue(in, Option(out.get.pop.get)))
  }

  def front(): Option[T] = out.get.top

  def isEmpty: Boolean = in.get.isEmpty && out.get.isEmpty
}
