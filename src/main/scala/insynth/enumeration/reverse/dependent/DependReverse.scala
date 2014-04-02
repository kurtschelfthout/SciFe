package insynth.enumeration
package reverse
package dependent

import insynth.enumeration.dependent._

trait DependReverse[I, +O] extends DependFinite[I, O] {
  
  type EnumType <: Reverse[O]

  override def apply(parameter: I) = getEnum(parameter)

  override def getEnum(parameter: I): EnumType
  
}