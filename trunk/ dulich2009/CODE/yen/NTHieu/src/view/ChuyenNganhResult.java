package view;

public class ChuyenNganhResult {
	private String strClassName;
	private String strIndividualName;
	private String strTenGoi;
	private String strCoTaiNguyen;
	private String strMoTaNoiDung;
	private String strLinhVucCoThuocTinh;
	private String strTenThuocTinh;

	public String getStrLinhVucCoThuocTinh() {
		return strLinhVucCoThuocTinh;
	}

	public void setStrLinhVucCoThuocTinh(String strLinhVucCoThuocTinh) {
		this.strLinhVucCoThuocTinh = strLinhVucCoThuocTinh;
	}

	public String getstrTenThuocTinh() {
		return strTenThuocTinh;
	}

	public void setstrTenThuocTinh(String strTenThuocTinh) {
		this.strTenThuocTinh = strTenThuocTinh;
	}

	public String getStrMoTaNoiDung() {
		return strMoTaNoiDung;
	}

	public void setStrMoTaNoiDung(String strMoTaNoiDung) {
		this.strMoTaNoiDung = strMoTaNoiDung;
	}

	public ChuyenNganhResult() {
		this.strClassName="";
		this.strIndividualName="";
		this.strTenGoi="";
		this.strCoTaiNguyen="";
		this.strLinhVucCoThuocTinh="";
		this.strTenThuocTinh="";
	}

	public String getStrClassName() {
		return strClassName;
	}

	public void setStrClassName(String strClassName) {
		this.strClassName = strClassName;
	}

	public String getStrIndividualName() {
		return strIndividualName;
	}

	public void setStrIndividualName(String strIndividualName) {
		this.strIndividualName = strIndividualName;
	}

	public String getStrTenGoi() {
		return strTenGoi;
	}

	public void setStrTenGoi(String strTenGoi) {
		this.strTenGoi = strTenGoi;
	}

	public String getStrCoTaiNguyen() {
		return strCoTaiNguyen;
	}

	public void setStrCoTaiNguyen(String strCoTaiNguyen) {
		this.strCoTaiNguyen = strCoTaiNguyen;
	}

}
