import java.util.List;
import java.util.Vector;

public class MapNode {
	public static final int MIN_TYPE = 1;
	
	public static final int MAX_TYPE = 10;


	private Coordinates location;
	private Vector<MapNode> neighbors;
	
	/**
	 * Constructor. Creates a map node to a location.
	 * @param location The location of the node.
	 */
	public MapNode(Coordinates location) {
		this.location = location;
		this.neighbors = new Vector<MapNode>();
		
	}

	/**
	 * Adds the node as this node's neighbour (unless the node is null)
	 * @param node The node to add or null for no action
	 */
	public void addNeighbor(MapNode node) {
		if (node == null) {
			return;
		}

		addToList(node);		// add the node to list
	}

	/**
	 * Adds the node to list of neighbours unless it is already there or
	 * "neighbour" is this node
	 * @param node
	 */
	private void addToList(MapNode node) {
		if (!this.neighbors.contains(node) && node != this) {
			this.neighbors.add(node);
		}
	}

	/**
	 * Returns the location of the node
	 * @return the location of the node
	 */
	public Coordinates getLocation() {
		return location;
	}

	/**
	 * Returns the neighbors of this node.
	 * @return the neighbors in a list
	 */
	public List<MapNode> getNeighbors() {
		return neighbors;
	}

	/**
	 * Returns a String representation of the map node
	 * @return a String representation of the map node
	 */
	public String toString() {
		return "N" + "@"+this.location.toString();
	}

	/**
	 * Compares two map nodes by their coordinates
	 * @param o The other MapNode
	 */
	public int compareTo(MapNode o) {
		return this.getLocation().compareTo((o).getLocation());
	}

}
