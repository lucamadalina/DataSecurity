
public class Main {


	public static void main(String[] args) {
		AutoKey ak = new AutoKey();
		System.out.println(ak.encryptPlaintTextInput("ANA ARE MERE"));
		System.out.println(ak.decryptCipherTextInput("ANA ARE MERE"));

		Beaufort bf = new Beaufort();
		System.out.println(bf.encrypt("ANA", "ANA ARE MERE"));
		System.out.println(bf.decrypt("ANA", "AAAAWWOJJW"));
		
		FourSquare fs = new FourSquare();
		System.out.println(fs.encryptPlaintTextInput("ANA ARE MERE"));
		System.out.println(fs.decryptCipherTextInput("ANA ARE MERE"));
	}

}