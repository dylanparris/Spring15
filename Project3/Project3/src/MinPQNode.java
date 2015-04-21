
public class MinPQNode implements Comparable<MinPQNode>{
	
	String name;
	float distance;
	
	static final int INFINITY = 2147483647;
	
	public MinPQNode(String a){
		name = a;
		distance = INFINITY;
	}

	@Override
	public int compareTo(MinPQNode node) {
		if(this.distance > node.distance){
			return 1;
		} else if(this.distance == node.distance){
			return 0;
		} else {
			return -1;
		}
	}
	
	public float getDistance(){
		return distance;
	}
	
	public void setDistance(float d){
		this.distance = d;
	}

}
