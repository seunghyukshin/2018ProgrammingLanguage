package ant;

//201302422_�Ž���
public class Recursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(fibonacci(10));
		System.out.println(recursiveAnt(10));
	}

	public static int fibonacci(int n) {
		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;
		else
			return fibonacci(n - 1) + fibonacci(n - 2);
	}

	// ���� �������� n ��° ���� ��ȯ�Ѵ�.
	// �ش� �Լ� �ȿ��� n��° ���̼����� ���� ����� makeResult
	public static String recursiveAnt(int n) {
		// recursive(4)->1121
		if (n == 1)
			return "1";
		return makeResult(recursiveAnt(n - 1));
	}

	// �������� ������ ���� �̿��Ͽ� ���� ���̼����� ���� �����.
	public static String makeResult(String previous) {
		// previous=="1"->return "11"
		// previous=="11"->return "12"
		// previous=="12"->return "1121"

		int count = 1;
		String result = "";
		if (previous == "1")
			return "11";
		for (int i = 0; i <= previous.length() - 2; i++) {
			if (previous.charAt(i) != previous.charAt(i + 1)) {
				result += previous.charAt(i) + Integer.toString(count);
				if (i == previous.length() - 2)
					result += previous.charAt(i + 1) + Integer.toString(count);
				count = 1;
			} else {
				count++;
				if (i == previous.length() - 2)
					result += previous.charAt(i + 1) + Integer.toString(count);
			}
		}
		return result;
	}

}
