package abstractMembers

import org.scalatest.{BeforeAndAfterAll, FunSuite}


class TestLazyVals extends FunSuite with BeforeAndAfterAll{

  test("Plu member Sku will be lazy loaded")
  {
    val plu = new LazyPlu("002")
    println("plu pluCode=" + plu.pluCode)
    println("plu sku="+plu.sku.skuCode)
  }

  class LazyPlu  (val pluCode: String)
  {
    println("new Plu")
    lazy val sku: Sku = lookUpSku(pluCode)
    def lookUpSku(plu: String): Sku = new Sku(pluCode,"2")
  }

  class Sku(val pluCode: String, val skuCode: String)
  {
    println("new Sku")
  }
}
