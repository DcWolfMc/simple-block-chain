package simpleBlockChain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Block {

	
	private int index;
	private String data;
	private String timestamp;
	private String previousHash;
	private String hash;
	private int nunce;
	private String difficulty; 
	
	public Block(int index,String data, String previousHash) throws NoSuchAlgorithmException {
		this.index = index;
		this.data = data;
		this.difficulty = "0000";
		this.timestamp = LocalDateTime.now().toString();
		this.previousHash = previousHash;
		this.nunce = 0;
		this.hash= this.calculateHash(difficulty);
	}
	
	public String calculateHash() throws NoSuchAlgorithmException{
		String string = index+";"+previousHash+";"+data+";"+timestamp+";"+nunce;
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hashInBytes = digest.digest(string.getBytes(StandardCharsets.UTF_8));

		StringBuilder sb = new StringBuilder();
		for (byte b : hashInBytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	public String calculateHash(String rule) throws NoSuchAlgorithmException{
		String hashMined = "";
		while(!hashMined.startsWith(rule)) {
			nunce++;
			String string = index+";"+previousHash+";"+data+";"+timestamp+";"+nunce;
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashInBytes = digest.digest(string.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (byte b : hashInBytes) {
				sb.append(String.format("%02x", b));
			}
			hashMined = sb.toString();
		}
		return hashMined;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public String getHash() {
		return hash;
	}

	
	public String getDifficulty() {
		return difficulty;
	}


	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}


	public void setHash(String hash) {
		this.hash = hash;
	}

	public int getNunce() {
		return nunce;
	}

	public void setNunce(int nunce) {
		this.nunce = nunce;
	}
	
}
