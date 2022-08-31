package simpleBlockChain;

import java.security.NoSuchAlgorithmException;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		Blockchain blockchain = new Blockchain();
		blockchain.createGenesisBlock();
		blockchain.addBlock("Teste 1");
		blockchain.addBlock("Teste 2");
		blockchain.addBlock("Teste 3");
		blockchain.addBlock("Teste 4");
		blockchain.addBlock("Teste 5");
		blockchain.addBlock("Teste 6");
		blockchain.addBlock("Teste 7");
		blockchain.addBlock("Teste 8");
		blockchain.addBlock("Teste 9");
		blockchain.addBlock("Teste 10");
		blockchain.showBlockChain();
	}
}
