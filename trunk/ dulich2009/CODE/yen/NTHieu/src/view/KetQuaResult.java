package view;

public class KetQuaResult {
	private String ketquaid;
	private String keyword;
	private float timdung;
	private String tailieuid;
	private String nhande;
	private String motanoidung;
	private String tentacgia;
	private String totaldownload;
	private String dinhdang;
	private String anhminhhoa;
	public String getAnhminhhoa() {
		return anhminhhoa;
	}
	public void setAnhminhhoa(String anhminhhoa) {
		this.anhminhhoa = anhminhhoa;
	}
	public KetQuaResult() {
		ketquaid="";
		keyword="";
		timdung=0;
		tailieuid="";
		nhande="";
		motanoidung="";
		tentacgia="";
		totaldownload="";
		dinhdang="";
		anhminhhoa="";
	}
	public String getNhande() {
		return nhande;
	}
	public void setNhande(String nhande) {
		this.nhande = nhande;
	}
	public String getMotanoidung() {
		return motanoidung;
	}
	public void setMotanoidung(String motanoidung) {
		this.motanoidung = motanoidung;
	}
	public String getTentacgia() {
		return tentacgia;
	}
	public void setTentacgia(String tentacgia) {
		this.tentacgia = tentacgia;
	}
	public String getTotaldownload() {
		return totaldownload;
	}
	public void setTotaldownload(String totaldownload) {
		this.totaldownload = totaldownload;
	}
	public String getDinhdang() {
		return dinhdang;
	}
	public void setDinhdang(String dinhdang) {
		this.dinhdang = dinhdang;
	}
	public String getKetquaid() {
		return ketquaid;
	}
	public void setKetquaid(String ketquaid) {
		this.ketquaid = ketquaid;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public float getTimdung() {
		return timdung;
	}
	public void setTimdung(float timdung) {
		this.timdung = timdung;
	}
	public String getTailieuid() {
		return tailieuid;
	}
	public void setTailieuid(String tailieuid) {
		this.tailieuid = tailieuid;
	}

}
