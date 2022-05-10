package com.example.faylyuklashyuklabolish.Cantroler;

import com.example.faylyuklashyuklabolish.Model.Fayl;
import com.example.faylyuklashyuklabolish.Model.FaylSourse;
import com.example.faylyuklashyuklabolish.Repazitori.FaylRepazitori;
import com.example.faylyuklashyuklabolish.Repazitori.FaylSourseRepazitori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/fayl")
public class FaylCantroler {
    @Autowired
    FaylSourseRepazitori faylSourseRepazitori;
    @Autowired
    FaylRepazitori faylRepazitori;
    @PostMapping(value = "/yuklash")
    public String FaylYuklash(MultipartHttpServletRequest request) throws IOException {
        System.out.println(System.currentTimeMillis());
        int vaqt= (int) System.currentTimeMillis();
        Iterator<String> stringIterator=request.getFileNames();
        MultipartFile multipartFile=request.getFile(stringIterator.next());
        if (multipartFile!=null){
            String faylnomi=multipartFile.getOriginalFilename();
            long faylhajmi=multipartFile.getSize();
            String turi=multipartFile.getContentType();
            byte[] faylbayt=multipartFile.getBytes();
            Fayl fayl=new Fayl();
            fayl.setArginalfaylnomi(faylnomi);
            fayl.setMalumotTuri(turi);
            fayl.setHajmi(faylhajmi);
            String yangnom=multipartFile.getOriginalFilename();
            fayl.setNewnomi(yangnom);
            Fayl faylsaqla=faylRepazitori.save(fayl);
            FaylSourse faylSourse=new FaylSourse();
            faylSourse.setBytes(faylbayt);
            faylSourse.setFayl(faylsaqla);
            faylSourseRepazitori.save(faylSourse);
            System.out.println(System.currentTimeMillis());
            int vaqt1= (int) System.currentTimeMillis();
            System.out.println(vaqt1-vaqt);
            return "Malumot Joylandi!";
        }
        return "Joylanmadi!";
    }
    @GetMapping("/yuklash/{id}")
    public void DBYuklash(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Optional<Fayl> optionalFayl=faylRepazitori.findById(id);
        if (optionalFayl.isPresent()){
            Fayl fayl=optionalFayl.get();
            Optional<FaylSourse> optionalFaylSourse=faylSourseRepazitori.findByFayl_Id(id);
            if (optionalFaylSourse.isPresent()){
                FaylSourse faylSourse=optionalFaylSourse.get();
                response.setContentType(fayl.getMalumotTuri());
                response.setHeader("Content-Disposition","attachment; faylname=\""+fayl.getArginalfaylnomi()+"\"");
                FileCopyUtils.copy(faylSourse.getBytes(),response.getOutputStream());
            }
        }
    }
    String mazil="baza";
    @PostMapping("/yuklash/folders")
    public String Papkajoylash(MultipartHttpServletRequest request) throws IOException {
        System.out.println(System.currentTimeMillis());
        int vaqt= (int) System.currentTimeMillis();
        Iterator<String> stringIterator=request.getFileNames();
        MultipartFile multipartFile=request.getFile(stringIterator.next());
        if (multipartFile!=null){
            Fayl fayl=new Fayl();
            fayl.setArginalfaylnomi(multipartFile.getOriginalFilename());
            fayl.setHajmi(multipartFile.getSize());
            fayl.setMalumotTuri(multipartFile.getContentType());
            String yangnom=multipartFile.getOriginalFilename();
            String[] split = yangnom.split("\\.");
            String s = UUID.randomUUID().toString()+"."+split[split.length-1];
            fayl.setNewnomi(s);
            faylRepazitori.save(fayl);
            Path path= Paths.get(mazil+"/"+s);
            Files.copy(multipartFile.getInputStream(),path);
            System.out.println(System.currentTimeMillis());
            int vaqt1= (int) System.currentTimeMillis();
            System.out.println(vaqt1-vaqt);
        return "Joylandi!";
        }
       return "joylanmadi!";
    }
    @GetMapping("/yuklabolish/{id}")
    public void papkayuklash(@PathVariable Integer id,HttpServletResponse response) throws IOException {
        Optional<Fayl> optionalFayl=faylRepazitori.findById(id);
        if (optionalFayl.isPresent()){
            Fayl fayl=optionalFayl.get();
            response.setContentType(fayl.getMalumotTuri());
            response.setHeader("Content-Disposition","attachment; faylname=\""+fayl.getArginalfaylnomi()+"\"");
            FileInputStream fileInputStream=new FileInputStream(mazil+"/"+fayl.getNewnomi());
            FileCopyUtils.copy(fileInputStream,response.getOutputStream());
        }
    }
    @PostMapping("/ikkisigahamjoylash")
    public String joy(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> stringIterator=request.getFileNames();
        MultipartFile multipartFile=request.getFile(stringIterator.next());
        if (multipartFile!=null){
            String faylnomi=multipartFile.getOriginalFilename();
            long faylhajmi=multipartFile.getSize();
            String turi=multipartFile.getContentType();
            byte[] faylbayt=multipartFile.getBytes();
            Fayl fayl=new Fayl();
            fayl.setArginalfaylnomi(faylnomi);
            fayl.setMalumotTuri(turi);
            fayl.setHajmi(faylhajmi);
            String yangnom=multipartFile.getOriginalFilename();
            fayl.setNewnomi(yangnom);
            Fayl faylsaqla=faylRepazitori.save(fayl);
            FaylSourse faylSourse=new FaylSourse();
            faylSourse.setBytes(faylbayt);
            faylSourse.setFayl(faylsaqla);
            faylSourseRepazitori.save(faylSourse);
            String[] split = yangnom.split("\\.");
            String s = UUID.randomUUID().toString()+"."+split[split.length-1];
            Path path= Paths.get(mazil+"/"+s);
            Files.copy(multipartFile.getInputStream(),path);
            return "Malumot Joylandi!";
        }
        return "Joylanmadi!";
    }
}
