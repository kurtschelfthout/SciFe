package insynth
package enumeration
package reverse

import insynth.{ enumeration => e }

import scala.{ collection => col }
import scala.reflect._

class WrapArray[T](override val coll: Array[T]) extends e.WrapArray[T](coll) with Reverse[T] {
  override def size = coll.size
  
  val reverseMap: col.Map[T, Int] = coll.zipWithIndex.toMap
  
  override def reverse[V >: T](el: V) = {
    val revInd = reverseMap(el.asInstanceOf[T])
    new WrapArrayCont(coll, revInd)
  }
    
}

class WrapArrayCont[T](override val coll: Array[T], pos: Int) extends e.WrapArray[T](coll) {
  
  override def size = super.size - pos
  
  override def apply(ind: Int) = {
  	super.apply(ind + pos)
  }
    
}