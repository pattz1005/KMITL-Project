public abstract class Tree {
	String description = "Unknown tree";
	boolean hasastar=false;
	  
		public String getDescription() {
			return description;
		}
		
		public boolean hasStar() {
			return hasastar;
		}
		
		public void setStar() {
			hasastar=true;  // through delegation these calls end up here and set boolean accordingly
		}
	 
		public abstract double cost();		
}
