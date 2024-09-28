package com.dantalian.ojcodesandbox;

import com.dantalian.ojcodesandbox.model.ExecuteCodeRequest;
import com.dantalian.ojcodesandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
