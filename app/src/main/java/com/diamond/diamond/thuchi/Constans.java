package com.diamond.diamond.thuchi;

public class Constans {
    public static final boolean isDEBUG = true;

    //khai báo bảng khoản Thu
    public static final String THU_TABLE = "khoanthu";



    public static final String THU_MA = "Makthu";

    public static final String THU_NAME = "name";

    public static final String THU_DATE = "ngay";

    public static final String THU_TIEN = "sotien";
    public static final String THU_TEN_VI = "tenvi";
    public static final String THU_GHI_CHU = "ghichu";

    public static final String CREATE_THU_TABLE="CREATE TABLE "+ THU_TABLE +"(" +
            "" + THU_MA + " NVARCHAR(50)," +
            "" + THU_NAME + " NVARCHAR(100)," +
            "" + THU_DATE + " NVARCHAR(20)," +
            "" + THU_TIEN + " NCHAR(20)," +
            "" + THU_TEN_VI + " NVARCHAR(10)," +
            "" + THU_GHI_CHU + " NVARCHAR(200)" +
            ")";


    //khai báo bảng khoản Chi
    public static final String CHI_TABLE = "khoanchi";
    public static final String CHI_MA = "Makchi";

    public static final String CHI_NAME = "name";

    public static final String CHI_DATE = "ngay";
    public static final String CHI_TEN_VI = "tenvi";
    public static final String CHI_TIEN = "sotien";
    public static final String CHI_GHI_CHU = "ghichu";

    public static final String CREATE_CHI_TABLE="CREATE TABLE "+ CHI_TABLE +"(" +
            "" + CHI_MA + " NVARCHAR(50)," +
            "" + CHI_NAME + " NVARCHAR(100)," +
            "" + CHI_DATE + " NVARCHAR(20)," +
//            "" + CHI_TEN_VI + " NVARCHAR(20)," +
            "" + CHI_TIEN + " NCHAR(20)," +
            "" + CHI_GHI_CHU + " NVARCHAR(200)" +
            ")";



    //khai báo bảng ví
    public static final String VI_TABLE = "vi";



    public static final String VI_MA = "Mavi";

    public static final String VI_NAME = "viname";

    public static final String VI_TONG_CHI = "mathu";
    public static final String VI_TONG_THU = "machi";


    public static final String CREATE_VI_TABLE="CREATE TABLE "+ VI_TABLE +"(" +
            "" + VI_MA + " NVARCHAR(50)," +
            "" + VI_NAME + " NVARCHAR(20)," +
            "" + VI_TONG_CHI + " NVARCHAR(50)," +

            "" + VI_TONG_THU + " NVARCHAR(50)" +
            ")";


    //bảng ghi chú
    public static final String GHICHU_TABLE = "ghichu";
    public static final String GHICHU_TEN = "tenghichu";
    public static final String GHICHU_DATE = "tenghichu";

    public static final String GHICHU_NOIDUNG = "noidung";


    public static final String CREATE_GHICHU_TABLE="CREATE TABLE "+ GHICHU_TABLE +"(" +
            "" + GHICHU_TEN + " NVARCHAR(50)," +
            "" + GHICHU_NOIDUNG + " NVARCHAR(50)," +

            "" + GHICHU_DATE + " NVARCHAR(50)" +
            ")";

}
