package org.encog.ca.program.conway;

import org.encog.ca.program.CAProgram;
import org.encog.ca.program.basic.BasicProgram;
import org.encog.ca.program.basic.Movement;
import org.encog.ca.universe.Universe;
import org.encog.ca.universe.UniverseCell;

public class ConwayProgram extends BasicProgram {
	
	public ConwayProgram(Universe theSourceUniverse) {
		super(Movement.MOVE_8WAY);
		this.sourceUniverse = theSourceUniverse;
	}

	private Universe sourceUniverse;
	private Universe targetUniverse;
	
	public void iteration() {
		int height = this.sourceUniverse.getRows();
		int width = this.targetUniverse.getColumns();
		
		for(int row = 0; row< height; row++ ) {
			for(int col = 0; col<width; col++) {
				processCell(row,col);
			}
		}
	}
	
	public void processCell(int row, int col) {
		Movement[] movements = getMovements();
		
		UniverseCell thisCell = this.sourceUniverse.get(row,col);
		UniverseCell targetCell = this.targetUniverse.get(row,col);
		
		int total = 0;
		for(Movement movement : movements ) {
			int otherRow = row+movement.getRowMovement();
			int otherCol = col+movement.getColumnmMovement();
			if( this.sourceUniverse.isValid(otherRow,otherCol) ) {
				UniverseCell otherCell = this.sourceUniverse.get(otherRow,otherCol);				
				if( ((int)otherCell.get(0))>0 ) {
					total++;
				}
			}
		}
		
		boolean alive = ((int)thisCell.get(0))>0;  
		
		
		if( alive  ) {		
			// 1. Any live cell with fewer than two live neighbors dies, as if caused by under-population.
			if( total<2 ) {
				alive = false;
			}
			// 2. Any live cell with two or three live neighbors lives on to the next generation. (not needed)
			// 3. Any live cell with more than three live neighbors dies, as if by overcrowding.
			if( alive && total>3 ) {
				alive = false;
			}
		} else {
			// 4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
			if( total==3 ) {
				alive = true;
			}	
		}
		
		
		
		
		if( alive ) {
			targetCell.set(0, 1.0);
		} else {
			targetCell.set(0, 0.0);
		}
		
	}

	@Override
	public void randomize() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the sourceUniverse
	 */
	public Universe getSourceUniverse() {
		return sourceUniverse;
	}

	/**
	 * @param sourceUniverse the sourceUniverse to set
	 */
	public void setSourceUniverse(Universe sourceUniverse) {
		this.sourceUniverse = sourceUniverse;
	}

	/**
	 * @return the targetUniverse
	 */
	public Universe getTargetUniverse() {
		return targetUniverse;
	}

	/**
	 * @param targetUniverse the targetUniverse to set
	 */
	public void setTargetUniverse(Universe targetUniverse) {
		this.targetUniverse = targetUniverse;
	}
	
	
}
