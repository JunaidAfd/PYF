import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimMap implements Serializable{
	private Coordinates minBound;
	private Coordinates maxBound;
	/** list representation of the map for efficient list-returning */
	private ArrayList<MapNode> nodes;
	/** hash map presentation of the map for efficient finding node by coord */
	private Map<Coordinates, MapNode> nodesMap;
	/** offset of map translations */
	private Coordinates offset;
	
	/** is re-hash needed before using hash mode (some coordinates changed) */
	private boolean needsRehash = false;

	public SimMap(Map<Coordinates, MapNode> nodes) {
		this.offset = new Coordinates(0,0);
		this.nodes = new ArrayList<MapNode>(nodes.values());
		this.nodesMap = nodes;
		setBounds();
	}

	/**
	 * Returns all the map nodes in a list
	 * @return all the map nodes in a list
	 */
	public List<MapNode> getNodes() {
		
		return this.nodes;
	}

	/**
	 * Returns a MapNode at given coordinates or null if there's no MapNode
	 * in the location of the coordinate
	 * @param c The coordinate
	 * @return The map node in that location or null if it doesn't exist
	 */
	public MapNode getNodeByCoord(Coordinates c) {
		if (needsRehash) { // some coordinates have changed after creating hash
			nodesMap.clear();
			for (MapNode node : getNodes()) {
				nodesMap.put(node.getLocation(), node); // re-hash
			}
		}

		return nodesMap.get(c);
	}

	/**
	 * Returns the upper left corner coordinate of the map
	 * @return the upper left corner coordinate of the map
	 */
	public Coordinates getMinBound() {
		return this.minBound;
	}

	/**
	 * Returns the lower right corner coordinate of the map
	 * @return the lower right corner coordinate of the map
	 */
	public Coordinates getMaxBound() {
		return this.maxBound;
	}

	/**
	 * Returns the offset that has been caused by translates made to
	 * this map (does NOT take into account mirroring).
	 * @return The current offset
	 */
	public Coordinates getOffset() {
		return this.offset;
	}

	

	/**
	 * Translate whole map by dx and dy
	 * @param dx The amount to translate X coordinates
	 * @param dy the amount to translate Y coordinates
	 */
	public void translate(double dx, double dy) {
		for (MapNode n : nodes) {
			n.getLocation().translate(dx, dy);
		}

		minBound.translate(dx, dy);
		maxBound.translate(dx, dy);
		offset.translate(dx, dy);

		needsRehash = true;
	}

	

	/**
	 * Updates the min & max bounds to conform to the values of the map nodes.
	 */
	private void setBounds() {
		double minX, minY, maxX, maxY;
		Coordinates c;
		minX = minY = Double.MAX_VALUE;
		maxX = maxY = -Double.MAX_VALUE;

		for (MapNode n : nodes) {
			c = n.getLocation();
			if (c.getX() < minX) {
				minX = c.getX();
			}
			if (c.getX() > maxX) {
				maxX = c.getX();
			}
			if (c.getY() < minY) {
				minY = c.getY();
			}
			if (c.getY() > maxY) {
				maxY = c.getY();
			}
		}
		minBound = new Coordinates(minX, minY);
		maxBound = new Coordinates(maxX, maxY);
	}

	/**
	 * Returns a String representation of the map
	 * @return a String representation of the map
	 */
	public String toString() {
		return this.nodes.toString();
	}
}