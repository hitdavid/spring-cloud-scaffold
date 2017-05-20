package com.hitdavid.app;

import com.hitdavid.app.exception.JsonException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by David on 2017/5/19.
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index() throws Exception {
//        throw new Exception("发生错误");
        return "Hello World";
    }

    @RequestMapping("/json")
    public String json() throws JsonException {
//        throw new JsonException("发生错误2");
        return "";
    }

}
