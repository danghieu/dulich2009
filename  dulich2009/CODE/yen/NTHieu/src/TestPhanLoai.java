
public class TestPhanLoai {
	public static void main(String[] args) {
		PhanLoai pl=new PhanLoai();
		String s[]=pl.searchIndividual("CongNgheThongTin");
		for(int i=0;i<s.length;i++){
			System.out.println(s[i]);
		}
	}
}
