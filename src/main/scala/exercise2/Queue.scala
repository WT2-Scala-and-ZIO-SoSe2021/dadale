package exercise2

import scala.util.Try
import scala.util.Failure
import scala.util.Success

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


  def this(in: StackLike[T]) = this(new Stack[T](), in.reverse)

  def enqueue(elem: T): Queue[T] = if (isEmpty)
    new Queue(in, out.push(elem))
  else new Queue(in.push(elem), out)

  def dequeue(): Try[Queue[T]] = {
    if (isEmpty) new Failure(new Throwable("Queue is empty"))
    else if (out.pop.get.isEmpty)
      Try(new Queue(in))
    else Try(new Queue(in, out.pop.get))
  }

  def front(): Option[T] = out.top

  def isEmpty: Boolean = in.isEmpty && out.isEmpty
}
