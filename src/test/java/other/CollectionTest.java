package other;

import java.util.*;

/**
 * @program: springboot-api
 * @description:List的遍历
 * @author: wangchuan
 * @create: 2020-01-06
 */
public class CollectionTest {
    public static void main(String[] args) {
        List<String> sl=new ArrayList<>();
        sl.add("1");
        sl.add("1");
        sl.add("2");
        sl.add("3");

        String str[]=new String[]{"tom","joy","alen","tom"};
        Set<String> set=new HashSet(Arrays.asList(str));
        for(String item:set){
            System.out.println(item);
        }
        System.out.println("-----------");
        String[] newstr=set.toArray(new String[0]);
        for(String item:newstr){
            System.out.println(item);
        }



    }




}
