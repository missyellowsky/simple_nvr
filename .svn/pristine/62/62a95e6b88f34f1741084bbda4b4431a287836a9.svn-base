package com.recorder.recorder.beans;

import com.recorder.recorder.beans.constant.Constant;
import lombok.Data;

@Data
public class Result<T> extends BaseResult {
    private T obj;

    public Result(T obj) {
        this.obj = obj;
    }

    public Result() {

    }

    public Result(String result) {
        switch (result){
            case Constant.SUCCESS:
                this.msg = Constant.SUCCESS;
                this.code = Constant.SUCCESS_CODE;
                break;
            case Constant.FAIL:
                this.msg = Constant.FAIL;
                this.code = Constant.FAIL_CODE;
                break;
                default:
                    this.msg = Constant.SUCCESS;
                    this.code = Constant.SUCCESS_CODE;
        }
    }

    public static Result success(){
        return new Result(Constant.SUCCESS);
    }

    public static Result success(Object obj){
        Result result = new Result(Constant.SUCCESS);
        result.setObj(obj);
        return result;
    }

    public static Result fail(){
        return new Result(Constant.FAIL);
    }

    public static Result fail(Object obj){
        Result result = new Result(Constant.FAIL);
        result.setObj(obj);
        return result;
    }
}
