package linkedlist;

//201302422_�Ž���
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		RecursionLinkedList list = new RecursionLinkedList();
		FileReader fr;
		try {
			fr = new FileReader("hw01.txt");
			BufferedReader br = new BufferedReader(fr);
			String inputString = br.readLine();
			for (int i = 0; i < inputString.length(); i++)
				list.add(inputString.charAt(i));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(list.toString());
		list.add(3, 'Z');
		System.out.println(list.toString());
		// list.reverse(); System.out.println(list.toString());
	}

}
