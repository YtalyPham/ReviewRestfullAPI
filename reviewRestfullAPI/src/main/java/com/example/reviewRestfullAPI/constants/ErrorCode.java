package com.example.reviewRestfullAPI.constants;

public class ErrorCode {
    /** CONVERTER **/
    public static final String ERR_CONVERT_DTO_ENTITY_FAIL = "ERR_CONVERT_DTO_ENTITY_FAIL"; // Use in converter package

    /** KHACHHANG **/
    public static final String ERR_KHACHHANG_NOT_FOUND = "ERR_KHACHHANG_NOT_FOUND"; // Use in update, delete, getDetails KHACHHANG service of Admin, get details + Staff
    public static final String ERR_KHACHHANG_LOADED_FAIL = "ERR_KHACHHANG_LOADED_FAIL"; // Use in GET load KHACHHANG controller of Admin, Staff
    public static final String ERR_KHACHHANG_LIST_NOT_FOUND = "ERR_KHACHHANG_LIST_NOT_FOUND"; // Use in get list all KHACHHANG service of Admin
    public static final String ERR_KHACHHANG_LIST_LOADED_FAIL = "ERR_KHACHHANG_LIST_LOADED_FAIL"; // Use in GET load KHACHHANG list controller of Admin
    public static final String ERR_KHACHHANG_LIST_EMPTY = "ERR_KHACHHANG_LIST_EMPTY";
    public static final String ERR_KHACHHANG_INFORMATION_DUPLICATE = "ERR_KHACHHANG_INFORMATION_DUPLICATE"; // Use in create KHACHHANG service of Admin
    public static final String ERR_CREATE_KHACHHANG_FAIL = "ERR_CREATE_KHACHHANG_FAIL"; // Use in POST create KHACHHANG controller of Admin
    public static final String ERR_UPDATE_KHACHHANG_FAIL = "ERR_UPDATE_KHACHHANG_FAIL"; // Use in PUT update KHACHHANG controller of Admin
    public static final String ERR_DELETE_KHACHHANG_FAIL = "ERR_DELETE_KHACHHANG_FAIL"; // Use in PUT delete KHACHHANG controller of Admin
    public static final String ERR_UPDATE_PASSWORD_KHACHHANG_FAIL = "ERR_UPDATE_PASSWORD_KHACHHANG_FAIL"; // Use in PUT update password KHACHHANG controller of KHACHHANG
    public static final String ERR_UPDATE_JOINDATE_KHACHHANG_FAIL = "ERR_UPDATE_JOINDATE_KHACHHANG_FAIL";
    public static final String ERR_UPDATE_DOB_KHACHHANG_FAIL = "ERR_UPDATE_DOB_KHACHHANG_FAIL";

    /** MONAN **/
    public static final String ERR_MONAN_NOT_FOUND = "ERR_MONAN_NOT_FOUND";
    public static final String ERR_CREATE_MONAN_FAIL = "ERR_CREATE_MONAN_FAIL";
    public static final String ERR_UPDATE_MONAN_FAIL = "ERR_UPDATE_MONAN_FAIL";
    public static final String ERR_DELETE_MONAN_FAIL = "ERR_DELETE_MONAN_FAIL";
    public static final String ERR_MONAN_LOADED_FAIL = "ERR_MONAN_LOADED_FAIL";
    public static final String ERR_MONAN_LIST_NOT_FOUND = "ERR_MONAN_LIST_NOT_FOUND";
    public static final String ERR_MONAN_LIST_LOADED_FAIL = "ERR_MONAN_LIST_LOADED_FAIL";

    /** HOADON **/
    public static final String ERR_HOADON_NOT_FOUND = "ERR_HOADON_NOT_FOUND";
    public static final String ERR_CREATE_HOADON_FAIL = "ERR_CREATE_HOADON_FAIL";
    public static final String ERR_UPDATE_HOADON_FAIL = "ERR_UPDATE_HOADON_FAIL";
    public static final String ERR_DELETE_HOADON_FAIL = "ERR_DELETE_HOADON_FAIL";
    public static final String ERR_HOADON_LOADED_FAIL = "ERR_HOADON_LOADED_FAIL";
    public static final String ERR_HOADON_LIST_NOT_FOUND = "ERR_HOADON_LIST_NOT_FOUND";
    public static final String ERR_HOADON_LIST_LOADED_FAIL = "ERR_HOADON_LIST_LOADED_FAIL";

}
