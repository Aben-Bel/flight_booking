class mock{
	static String[] getSeatList(){
		String[] a = {"aa", "bb"};
		return a;
	}
	static String[] getPlaneIDList(){
		return getSeatList();
	}
	static String[][] getFList(){
		String [][] a = {{"Addis Ababa", "London", "6:00", "13:35", "14", "78", "44"}, {"Hong Kong", "Cape Town", "3:20", "8:20", "36", "55", "21"}};
		return (a);
	}
}