package icia.project.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.stereotype.Service;

import icia.project.bean.BoardBean;
import icia.project.bean.DbBoardBean;
 
@Service
public class ViewService{
 
    public List<String> getList(DbBoardBean bb) {
    	
        List<String> list = new ArrayList<String>();
        
        File f = new File(bb.getCutRoute());    	// 루트경로만
     
        String[] names = f.list();

        String fileName = bb.getCutContent();	// 파일이름만

        for(int i = 0; i < names.length; i++){
            if(names[i].lastIndexOf(fileName) == 0){	//	파일이름 추출
                list.add(names[i]);						// 파일담기
                
            }
        }

        return list;
    }
 
}


