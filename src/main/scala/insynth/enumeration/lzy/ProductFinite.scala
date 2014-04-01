package insynth.enumeration
package lzy

import combinators.Product

import _root_.insynth.util
import util.Math._
import util.logging._

protected[enumeration] class ProductFinite[T, V]
	(override val left: Finite[T], override val right: Finite[V])
	extends Product[T, V] with Finite[(T, V)] with HasLogger {
  
  override def apply(ind: Int) = {
    val i1 = ind % left.size
    val i2 = ind / left.size
    (left(i1), right(i2))
  }
  
}

// optimization class
protected[enumeration] class ProductFiniteComb[T, V, U]
	(val left: Finite[T], val right: Finite[V])
  (combine: (T, V) => U)
  extends Finite[U] with HasLogger {
  
  override def size = left.size * right.size

  override def apply(ind: Int) = {
    val i1 = ind % left.size
    val i2 = ind / left.size
    combine(left(i1), right(i2))
  }
  
}

class ProductSingleton[T, V]
  (el: T, val right: Finite[V])
  extends Finite[(T, V)] with HasLogger {
  
  override def size = right.size
  
  def this(singleton: Singleton[T], right: Finite[V]) = this(singleton.el, right)
  
  override def apply(ind: Int) = {
    (el, right(ind))
  }
  
}