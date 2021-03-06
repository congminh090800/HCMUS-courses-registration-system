/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants;

/**
 *
 * @author Admin
 */
public class Constants {
    public static String CA_1="7:30 - 9:30";
    public static String CA_2="9:30 - 11:30";
    public static String CA_3="13:30 - 15:30";
    public static String CA_4="15:30 - 17:30";
    public static String[] caHocStrings={CA_1,CA_2,CA_3,CA_4};
    public static String MON="thứ 2";
    public static String SAT="thứ 7";
    public static String TUE="thứ 3";
    public static String WED="thứ 4";
    public static String THU="thứ 5";
    public static String FRI="thứ 6";
    public static String SUN="chủ nhật";
    public static Integer GIOI_HAN_MON=8;
    public static String SUCCESS="Thành công";
    public static String FAIL="Thất bại";
    public static String ASK_FOR_COMFIRMATION = "Yêu cầu xác nhận";
    public static String WARNING = "Cảnh báo";
    public static String MISSING_PARAMS = "Thiếu thông tin hoặc thông tin không hợp lệ!";
    public static String UNAUTHORIZED = "Sai tài khoản hoặc mật khẩu";
    public static String[] giaoVuHeader = {"Mã","Họ tên","Địa chỉ", "Sđt", "Giới tính", "Ngày sinh","Ngày tạo"};
    public static String[] monHocHeader = {"Mã môn học","Tên môn học","Số tín chỉ"};
    public static String[] hocKiHeader = {"Tên học kì","Năm học","Ngày bắt đầu","Ngày kết thúc"};
    public static String[] lopHeader = {"Tên lớp","Số lượng sinh viên","Số nam","Số nữ"};
    public static String[] sinhVienHeader = {"Mã","Họ tên","Địa chỉ", "Sđt", "Giới tính", "Ngày sinh","Ngày tạo","Tên lớp"};
    public static String[] kiDKHPHeader = {"Ngày bắt đầu","Ngày kết thúc","Tên học kì", "Năm học"};
    public static String[] hocPhanHeader = {"Mã học phần", "Tên môn", "Số tín chỉ",
                                            "GV lý thuyết","Slot tối đa","Ngày học",
                                            "Ca học","Tên phòng học"};
    public static String[] svHpHeader = {
        "MSSV", "Họ tên", "Mã học phần", "Tên môn",
        "Giáo viên", "Ngày học", "Ca học", "Ngày đăng kí"
    };
    public static int ADD_MODE = 0;
    public static int UPDATE_MODE = 1;
}
