package simpleBlockChain;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Blockchain {

	private ArrayList<Block> chain = new ArrayList<Block>();
	
	public void createGenesisBlock() throws NoSuchAlgorithmException {
		chain.add(new Block(0, "This is a genesis block", ""));
	}
	public Block getLatestBlock() {
		return chain.get(chain.size()-1); 
	}
	public void addBlock(String newBlockData) throws NoSuchAlgorithmException {
		chain.add(new Block(chain.size(), newBlockData, this.getLatestBlock().getHash()));
	}
	public void showBlockChain() {
		for(int n=0; n<chain.size();n++) {
			Block currentBlock = chain.get(n);
			System.out.println("{\n"+"\s"+"index: "+currentBlock.getIndex()+",");
			System.out.println("\s"+"previousHash: "+currentBlock.getPreviousHash()+",");
			System.out.println("\s"+"timestamp: "+currentBlock.getTimestamp()+",");
			System.out.println("\s"+"data: "+currentBlock.getData()+",");
			System.out.println("\s"+"hash: "+currentBlock.getHash()+",");
			System.out.println("\s"+"nunce: "+currentBlock.getNunce()+",");
			System.out.println("\s"+"difficulty: "+currentBlock.getDifficulty()+",\n"+"},");
		}
	}
}
