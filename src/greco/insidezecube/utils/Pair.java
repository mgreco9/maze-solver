package greco.insidezecube.utils;

public class Pair<T,Y>{
    public T first;
    public Y second;
    
    public Pair(T f, Y s){
        first = f;
        second = s;
    }
    
    @Override
    public boolean equals(Object other) { 
    	if(other instanceof Pair<?,?>) {
    		Pair<?,?> otherPair = (Pair<?,?>)other;
    		return (this.first.equals(otherPair.first) && this.second.equals(otherPair.second));
    	}
    	
    	return false;
    }
    
    @Override
    public String toString() {
    	String s ="";
    	
    	s += first.toString();
    	s += " ";
    	s += second.toString();
    	
    	return s;
    }
}