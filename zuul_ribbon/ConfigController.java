package com.hitdavid.service.gateway;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by David on 2017/6/2.
 */
@RestController
@RequestMapping("/file")
public class ConfigController {

    private static final Logger log = LoggerFactory.getLogger(ConfigController.class);

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = { "*/*" } )
    public String upload(
        @RequestParam(value = "filedata", required = true) MultipartFile filedata,
        @RequestParam(value = "name", required = true) String name
    ) {

        FilterLoader.getInstance().setCompiler(new GroovyCompiler());

        File f = new File("conf/"+name);

        try {

            FilterFileManager.setFilenameFilter(new GroovyFileFilter());
            InputStream is = filedata.getInputStream();


            OutputStream os = new FileOutputStream(f);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            is.close();

            String path = f.getAbsolutePath().substring(0, f.getAbsolutePath().lastIndexOf("/"));

            log.error(path);

            FilterFileManager.init(10, path);


            log.error("set ok!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f.getAbsolutePath();
    }

}
