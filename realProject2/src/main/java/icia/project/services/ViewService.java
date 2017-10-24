package icia.project.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.stereotype.Service;
 
@Service
public class ViewService{
 
    public List<String> getList() {
        List<String> list = new ArrayList<String>();
        File f = new File("E:\\eclipse\\test123456\\WebContent\\WEB-INF\\uploadFiles");
        //f에 모든 하위 디렉토리 및 파일이름 가져오기
        String[] names = f.list();
        //이름 들 중에서 . 이있는 이름만 list에 추가하기
        for(String sub : names){
            if(sub.indexOf(".") != -1){
                list.add(sub);
            }
        }
    
        System.out.println(list);
        return list;
    }
 
}


