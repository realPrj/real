package icia.project.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.stereotype.Service;

import icia.project.bean.DbBoardBean;
 
@Service
public class ViewService{
 
    public List<String> getList(DbBoardBean bb) {
       
        List<String> list = new ArrayList<String>();
        
        File f = new File(bb.getCutRoute());       // 저장위치에서 파일 찾기
        System.out.println(bb.getCutRoute());
        String[] names = f.list();

        String fileName = bb.getCutContent();   // 저장위치에서 파일이름저장
        
        for(int i = 0; i < names.length; i++){
            if(names[i].lastIndexOf(fileName) == 0){   // 저장위치에서 파일이름 찾기
                list.add(names[i]);                  // 이파일을 찾아서 담기
            }
        }

        return list;
    }
 
}
