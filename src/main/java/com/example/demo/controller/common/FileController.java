package com.example.demo.controller.common;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Controller
public class FileController {


    @RequestMapping(value = "download")
    @ResponseBody
    public void download(@RequestParam(value = "filepath") String filepath, HttpServletResponse response) {
// 后期改一下 需要获得参数 filepath
//        String filepath = request.getParameter("filepath");
//        filepath =  this.getClass().getResource("/static/file/conference.pdf").getPath();
        System.out.println("filepath:" + filepath);
//        获取文件名
        String filename = filepath.substring(filepath.lastIndexOf("/") + 37);
        System.out.println("filename:"+filename);

        try {
            File file1 = new File(filepath);    /*查找该文件*/
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file1));
            BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(filename.getBytes("UTF-8"), "ISO8859-1"));
            byte[] bt = new byte[(int) file1.length()];
            int i = 0;
            while ((i = inputStream.read(bt)) > -1) {
                outputStream.write(bt, 0, i);
            }
            outputStream.flush();
            inputStream.close();
            outputStream.close();
            System.out.println("下载成功了");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    上传论文文件
    @RequestMapping("/upload/paper")
    @ResponseBody
    String uploadPaper(@RequestParam(value = "file",required = false) MultipartFile file, HttpServletResponse response, HttpServletRequest request) throws IOException {
//        System.out.println("进来了");
        //获取项目的绝对路径
//        String path = System.getProperty("user.dir");
        System.out.println("1111111");
        System.out.println("文件是否为空："+file);
        if(file!=null){
//            String uploadPath ="E:/ConferenceSystem/src/main/resources/static/uploadPapers";
            // String resourcePath = this.getClass().getResource("/static").getPath();
            // 解决服务器文件上传问题
            ApplicationHome h = new ApplicationHome(getClass());
            File jarF = h.getSource();
            String resourcePath = jarF.getParentFile().toString();
            System.out.println("resourcePath:"+resourcePath);
            String uploadPath = resourcePath+"/uploadPapers";
            // 如果目录不存在则创建
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String OriginalFilename = file.getOriginalFilename();//获取原文件名
//            String suffixName = OriginalFilename.substring(OriginalFilename.lastIndexOf("."));//获取文件后缀名
            //重新随机生成名字
//            String filename = UUID.randomUUID().toString() +OriginalFilename+suffixName;
            String filename = UUID.randomUUID().toString() +OriginalFilename;
            File localFile = new File(uploadPath+"\\"+filename);
            try {
                // file.transferTo(localFile); //把上传的文件保存至本地
                /**
                 * 这里应该把filename保存到数据库,供前端访问时使用
                 */
                String _filename=uploadPath+"/"+filename;
                // 服务器上传文件
                File filePath = new File(_filename);
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
                outputStream.write(file.getBytes());
                outputStream.flush();
                outputStream.close();
                System.out.println("uploadPath:"+_filename);
                return _filename; //上传成功，返回保存的文件地址
            }catch (IOException e){
                e.printStackTrace();
//                System.out.println("上传失败");
                return "0";
            }
        }else{
//            System.out.println("文件为空");
            return "1";
        }

    }
}
