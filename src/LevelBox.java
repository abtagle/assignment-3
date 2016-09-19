package statsonly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LevelBox {
	
	private List<Integer> _levels;
	
	
	public LevelBox() {
		_levels = new ArrayList<Integer>();
		for (int i = 1; i < 11; i++) {
			_levels.add(i);
		}
	}
	
	//default level is 1
	public int getDefaultLevel() {
		return _levels.get(0);
	}
	
	//get all levels
	public ArrayList<Integer> getAllLevels() {
		return new ArrayList<Integer>(_levels);
	}
	
	//checks in given level is valid
	public int getLevel(int givenLevel) {
		int result = 0;
		boolean found = false;
		Iterator<Integer> i = _levels.iterator();

		while ((!found) && i.hasNext()) {
			int next = i.next();
			if (next == givenLevel) {
				found = true;
				result = next;
			}
		}
		return result;
	}
}
