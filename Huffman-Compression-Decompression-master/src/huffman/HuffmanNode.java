package huffman;

public class HuffmanNode implements Comparable<HuffmanNode>{
	  int symbol;
	  int frequency;
	  String code = "";
	  HuffmanNode left;
	  HuffmanNode right;
	 
	 public HuffmanNode(int symbol,int frequency) {
		 this.symbol = symbol;
		 this.frequency = frequency;
	 }
	 public HuffmanNode(int frequency) {
		 this.frequency = frequency;
	 }
	public int getSymbol() {
		return symbol;
	}
	public void setSymbol(int symbol) {
		this.symbol = symbol;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public HuffmanNode getLeft() {
		return left;
	}
	public void setLeft(HuffmanNode left) {
		this.left = left;
	}
	public HuffmanNode getRight() {
		return right;
	}
	public void setRight(HuffmanNode right) {
		this.right = right;
	}
	@Override
	public int compareTo(HuffmanNode o) {
		// TODO Auto-generated method stub
		if(this.equals(o)) {
			return 1;
		}
		else if(this.getFrequency() > o.getFrequency()) {
			return 1;
		}
		else {
			return -1;
		}
	}

}
