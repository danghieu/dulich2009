package model;

import java.util.ArrayList;

import view.ChuyenNganhResult;
import view.KetQuaAdvanced;
import view.LopResult;
import view.TaiLieuResult;
import view.TinhChatResult;

public class FrameWork {
	/**
	 * Kiem tra xem chuoi str co chua chuoi sub hay khong
	 * @param sub cuoi con 
	 * @param str chuoi can kiem tra xem co chua sub hay ko?
	 * @return true neu chua
	 */
	public boolean searchString(String sub,String str){
		boolean b=false;
		String usub=sub.toUpperCase().trim();
		String ustr=str.toUpperCase().trim();
		if(ustr.indexOf(usub) != -1){
			b=true;
		}
		else{
			b=false;
		}
		return b;
	}
	public String[] convertArrayListStr2StringArray(ArrayList<String> list){
		String[] strList=new String[list.size()];
		for(int i=0;i<list.size();i++){
			String str=(String)list.get(i);
			strList[i]=str;
		}
		return strList;
	}
	public String[] convertArrayListInd2StringArray(ArrayList<ChuyenNganhResult> list){
		String[] strList=new String[(list.size())*5];
		for(int i=0;i<list.size();i++){
			ChuyenNganhResult dtChuyenNganh=(ChuyenNganhResult)list.get(i);
			strList[i*5+0]=dtChuyenNganh.getStrIndividualName().toString();
			strList[i*5+1]=dtChuyenNganh.getStrTenGoi().toString();
			strList[i*5+2]=dtChuyenNganh.getStrMoTaNoiDung().toString();
			strList[i*5+3]=dtChuyenNganh.getStrLinhVucCoThuocTinh().toString();
			strList[i*5+4]=dtChuyenNganh.getstrTenThuocTinh().toString();
		}
		return strList;
	}
	public String[] convertArrayListLop2StringArray(ArrayList<LopResult> list){
		String[] strList=new String[(list.size())*3];
		for(int i=0;i<list.size();i++){
			LopResult dtLop=(LopResult)list.get(i);
			strList[i*3+0]=dtLop.getLopId();
			strList[i*3+1]=dtLop.getLabel();
			strList[i*3+2]=dtLop.getComment();
		}
		return strList;
	}
	public String[] convertArrayListTinhChat2StringArray(ArrayList<TinhChatResult> list){
		String[] strList=new String[(list.size())*3];
		for(int i=0;i<list.size();i++){
			TinhChatResult dtTinhChat=(TinhChatResult)list.get(i);
			strList[i*3+0]=dtTinhChat.getStrIndividualName();
			strList[i*3+1]=dtTinhChat.getStrTenGoi();
			strList[i*3+2]=dtTinhChat.getStrMoTaNoiDung();
		}
		return strList;
	}
	public String[] convertArrayListKetQua2StringArray(ArrayList<KetQuaAdvanced> list){
		int size=list.size();
		String[] strList=new String[size*8];
		for(int i=0;i<size;i++){
			KetQuaAdvanced dtKetQua=(KetQuaAdvanced) list.get(i);
			strList[i*8+0]=dtKetQua.getTailieuid();
			strList[i*8+1]=dtKetQua.getAnhminhhoa();
			strList[i*8+2]=dtKetQua.getNhande();
			strList[i*8+3]=dtKetQua.getMotanoidung();
			strList[i*8+4]=dtKetQua.getTentacgia();
			strList[i*8+5]=dtKetQua.getTotaldownload();
			strList[i*8+6]=dtKetQua.getDinhdang();
			float value=dtKetQua.getValue();
			int count=dtKetQua.getCount();
			int percent=(int)((value/count)*100);
			strList[i*8+7]=Integer.toString(percent)+"%";
		}
		return strList;
	}
	public String[] convertArrayListTaiLieu2StringArray(
			ArrayList<TaiLieuResult> list) {
		int size = list.size();
		String[] strList = new String[size * 19];
		for (int i = 0; i < size; i++) {
			TaiLieuResult dtTaiLieu = (TaiLieuResult) list.get(i);
			strList[i * 19 + 0] = dtTaiLieu.getId();
			strList[i * 19 + 1] = dtTaiLieu.getNhanDe();
			strList[i * 19 + 2] = dtTaiLieu.getBanQuyen();
			strList[i * 19 + 3] = dtTaiLieu.getNgayThang();
			strList[i * 19 + 4] = dtTaiLieu.getXuatBan();
			strList[i * 19 + 5] = dtTaiLieu.getDeMuc();
			strList[i * 19 + 6] = dtTaiLieu.getNgonNgu();
			strList[i * 19 + 7] = dtTaiLieu.getDinhDang();
			strList[i * 19 + 8] = dtTaiLieu.getNguonGoc();

			strList[i * 19 + 9] = dtTaiLieu.getMoTa();
			strList[i * 19 + 10] = dtTaiLieu.getNoiChua();
			strList[i * 19 + 11] = dtTaiLieu.getSoTrang();
			strList[i * 19 + 12] = dtTaiLieu.getAnhMinhHoa();

			strList[i * 19 + 13] = dtTaiLieu.getLienKet();
			strList[i * 19 + 14] = dtTaiLieu.getTenTacGia();
			strList[i * 19 + 15] = dtTaiLieu.getNguoiCongTac();
			strList[i * 19 + 16] = dtTaiLieu.getTenLinhVuc();
			strList[i * 19 + 17] = dtTaiLieu.getCoPhuongThuc();
			strList[i * 19 + 18] = dtTaiLieu.getTotalDownLoad();
		}
		return strList;
	}
}
