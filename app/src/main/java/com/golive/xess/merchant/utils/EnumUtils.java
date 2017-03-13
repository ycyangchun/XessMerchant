package com.golive.xess.merchant.utils;

/**
 * Created by YangChun .
 * on 2017/3/13.
 */
public enum EnumUtils {
    DOUBLE_COLOR_BALL("双色球", "1"),
    DA_LE_TOU("大乐透", "2"),
    KUAI_SAN("快三", "10412"),
    KUAI_SAN2("快三", "10406"),
    FOOTBALL_OUTCOME("足彩", "20201"),

    SINGLE("单式","10011071"),
    SINGLE2("单式","10021071"),

    DOUBLE("复式","10012071"),
    DOUBLE2("复式","10022071"),
    DAN("胆拖","10013071"),
    DAN2("胆拖","10023071"),

    WIN_STATE_HAD("已开奖", "10400"),
    WIN_STATE_HAD2("已开奖", "10401"),
    WIN_STATE_NOT("未开奖", "10402"),
    WIN_STATE_NOT2("未开奖", "10403"),

    ETWO("任二", "10061023"),
    ETHREE("任三", "10061033"),
    EFOUR("任四", "10061043"),
    EFIVE("任五", "10061053"),
    ESIX("任六", "10061063"),
    ESEVEN("任七", "10061073"),
    EEIGHT("任八", "10061083"),

    ETWO2("任二", "502"),
    ETHREE2("任三", "503"),
    EFOUR2("任四", "504"),
    EFIVE2("任五", "505"),
    ESIX2("任六", "506"),
    ESEVEN2("任七", "507"),
    EEIGHT2("任八", "508"),

    SAME_TWO_SINGLE("两同号单选", "10071034"),
    SAME_TWO_ALL("两同号通选", "10074014"),
    DIFF_TWO_SINGLE("两不同单选", "10071025"),
    //DIFF_TWO("两不同胆拖", "10073025"),//不支持
    SAME_THREE_SINHGLE("三同号单选", "10071036"),
    SAME_THREE_ALL("三同号通选", "10074036"),
    EVEN_THREE_SINGLE("三不同单选", "10071035"),
    EVEN_THREE_ALL("三连号通选", "10074037"),
    TOTAL("和值", "10071018");



    private String name;
    private String code;

    private EnumUtils(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static String getName(String code) {
        for (EnumUtils lid : EnumUtils.values()) {
            if (lid.getCode().equals(code)) {
                return lid.getName();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
