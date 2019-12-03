package com.onnoa.springboot.commons.exception;

/**
 * @Description: 异常枚举
 * @Author: onnoA
 * @Date: 2019/11/28 15:10
 */
public enum ExceptionEnums {

    //系统级异常
    UNKONW_EXCEPTION(99999, "未知异常"),
    SYSTEM_EXCEPTION(99998, "系统异常"),
    RPC_EXPETION(99997, "RPC调用失败【%s】"),
    DATABASE_PERSIS_ERROR(99992, "数据持久化出现错误"),

    // *************** 通用异常 **************

    // 参数校验异常
    INPUT_PARAMS_ERROR(99992, "参数校验失败,请重新输入"),
    PARAMETERS_NOT_EXIST_EXCEPTION(89998, "参数不存在异常【%s】"),
    EXCEL_CREATE_EXCEPTION(89995, "生成Excel文件失败"),
    PARAMETERS_EXCEPTION(89999, "参数异常【%s】"),
    REPETITION_OPERATION_EXCEPTION(89997, "请勿重复操作【%s】"),
    NOTNULL_EXCEPTION(89996, "非空异常【%s】"),

    //特定业务异常
    /*用户服务【user】
        以798开头，
        其他服务以798*往后面排，
        各自维护对应服务的异常类型
    */
    USER_GET_USERID_TOKEN(79899, "获取登录人uid失败：用户未登录：token=【%s】"),
    USER_GET_USERID_PARENTTOKEN(79898, "获取登录人uid失败：父令牌无效：parentToken=【%s】"),
    USER_GET_USERID_EXPIRETIME(79897, "获取登录人uid失败：令牌过期：expireTime=【%s】"),
    USER_GET_USERID_RENEWTIME(79898, "获取登录人uid失败：令牌需要续期：renewTime=【%s】"),
    USER_UNAUTHORIZED(79895, "未授权"),
    USER_UN_LOGIN(79894, "未登陆"),
    USER_INFO_EXCEPTION(79893, "用户信息获取异常"),
    USER_GET_USER_EXCEPTION(79892, "获取登录人信息异常：用户未登录请重新登录");

    // 其他业务异常



    ExceptionEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String msg) {
        this.msg = msg;
    }
}
