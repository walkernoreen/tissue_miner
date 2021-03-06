package de.mpicbg.eaton.tissueminer

import de.mpicbg.eaton.tissueminer.Tables
import de.mpicbg.eaton.tissueminer.Tables.CellHistories
import de.mpicbg.eaton.tissueminer.TraversalUtils._
import slick.driver.SQLiteDriver.api._
import slick.lifted.TableQuery

import scala.concurrent.Await
import scala.concurrent.duration.Duration._

/**
  * Get the roots of the lineage trees
  *
  * @author Holger Brandl
  */
object LineageRoots extends App {

  val db = Database.forURL("jdbc:sqlite:/Users/brandl/Desktop/demo_ForTissueMiner.sqlite", driver = "org.sqlite.JDBC")
  val cellHistories = TableQuery[CellHistories]

  implicit val cells: Seq[Tables.CellHistoriesRow] = Await.result(db.run(cellHistories.result), Inf)


  // define a recursive function to trace back the lineage
  def findLineageRoot(cell: Tables.CellHistoriesRow): Tables.CellHistoriesRow = {
    cell.mother match {
      case Some(motherCell) => findLineageRoot(motherCell)
      case None => cell
    }
  }


  private val treeRoots = cells.map(findLineageRoot).distinct
}
