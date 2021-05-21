package exercise2

import scala.util.Try

trait QueueLike[T] {
  def enqueue(elem: T): QueueLike[T]

  def dequeue(): Try[QueueLike[T]]

  def front(): Option[T]

  def isEmpty: Boolean
}

class Queue[T](
    val in: StackLike[T] = new Stack[T](),
    val out: StackLike[T] = new Stack[T]()
) extends QueueLike[T] {

  def enqueue(elem: T): QueueLike[T] = if (isEmpty)
    new Queue(in, out.push(elem))
  else new Queue(in.push(elem), out)

  def dequeue(): Try[QueueLike[T]] = {
    if (isEmpty) throw new Throwable("Queue is empty")
    if (out.pop.get.isEmpty)
      Try(new Queue(new Stack[T](), in.reverse))
    else Try(new Queue(in, out.pop.get))
  }

  def front(): Option[T] = out.top

  def isEmpty: Boolean = in.isEmpty && out.isEmpty
}
