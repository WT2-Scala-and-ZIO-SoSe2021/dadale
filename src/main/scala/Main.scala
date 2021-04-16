object Main extends App {
  def max(arr: Array[Int]): Int = arr.reduceLeft((x, y) => x max y);

  def min(arr: Array[Int]): Int = arr.reduceLeft((x, y) => x min y);

  def sum(arr: Array[Int]): Int = arr.reduceLeft((x, y) => x + y);
}
