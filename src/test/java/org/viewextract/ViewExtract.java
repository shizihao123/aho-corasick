package org.viewextract;
import com.sun.xml.internal.ws.util.StringUtils;
import org.ahocorasick.interval.*;
import org.ahocorasick.trie.*;

import java.io.*;
import java.util.*;
//import org.apache.commons.lang.StringUtils.*;

/**
 * Created by jun on 16-12-4.
 */
public class ViewExtract {

    public static void main(String args[]) {
        Map invalidWords = new HashMap();
//        ArrayList<String> places = new ArrayList<String>(Arrays.asList("Buenos Aires", "Córdoba", "La Plata"));
        ArrayList<String> uselessWords = new ArrayList<String>(Arrays.asList("“", "”", "全新", "运动", "运动感", "原型车", "小汽车", "风景", "进口车", "电动汽车","ceo", "主席",
                "集团", "概念车", "发现", "概念", "车", "店", "元", "行车", "TSI", "现代五项","霸道",
                "货车", "电动车", "多", "功能", "丰富", "新能源", "制造商", "自行车集", "进口", "电动车窗",
                "汽车品牌", "品牌", "制造", "led", "中国女排", "汽车网", "制造理念", "理念", "L", "风景线",
                "新闻", "汽车新闻", "中华民族", "mm", "中国制造", "成功人士", "汽车集团", "城市道路", "制造业", "面包车", "澳大利亚",
                "货车", "拖车", "公车", "计程车", "露营车", "里程", "轿车", "厢型车", "掀背车", "斜背车", "电动机",
                "卡车", "韩国", "中国", "经典", "电动", "集团", "全球", "风尚", "总裁", "变形金刚", "韩国大宇",
                "旅行车", "高级车", "中级车", "得意", "国民车", "越野车", "商务车", "多功能", "休旅车", "厢式",
                "休旅车", "跑车", "成功", "经验", "mod", "城市", "道路", "集团", "4S", "优雅", "里程碑", "CD机", "客车",
                "敞篷车", "超级", "年", "小型车", "中型车", "汽车论坛", "大型车", "自行车", "旅行车", "运动版", "中华世纪坛",
                "4s店", "4s", "mpv", "MPV", "pro", "PRO", "suv", "SUV", "功能丰富", "级车", "型车", "篷车", "重庆",
                "一", "二", "三", "四", "五", "六", "七", "八", "九", "8888", ")", "(", "副总裁","北京","集团", "厂商","mm", "功能", "地区", "上海", "南京", "陕西", "重庆", "中华",
                "总裁"
        ));

        ArrayList<String> extraCars = new ArrayList<String>(Arrays.asList("尼桑", "幻速", "小宝","大宝", "野帝", "哈佛", "荣放", "凌度", "凌渡", "上海通用", "凌志", "速腾r-line", "速腾gli", "高尔夫gti", "奔弛",
                "马自达全球", "启程","逸杰", "普桑", "高尔夫7", "crosspolo", "cross polo", "cross", "polo", "神龙汽车", "神龙"));

        ArrayList<String> end2Words =  new ArrayList<String>(Arrays.asList("集团", "汽车","公司","客车","火车"));
        ArrayList<String> end1Words =  new ArrayList<String>(Arrays.asList("车", "厂","^","-"));


//        endOneNames = ["京", "型", "团", "厂", "站", "省", "街","市", "区", "国", "级", "集", "路", "版", "店", "式", "网", "感", "化", "款"]
//        endTwoNames = ["北京","集团", "厂商","mm", "功能", "地区", "上海", "南京", "陕西", "重庆", "中华", "总裁"]

        for(String words: uselessWords){
               invalidWords.put(words,true);
        }

        TrieConfig config = new TrieConfig();
        config.setOnlyWholeWords(false);
        config.setAllowOverlaps(false);
        config.setCaseInsensitive(false);
        Trie trie = new Trie(config, false);

        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            /* 读入TXT文件 */
            String pathname = "/home/jun/sbin/workspace/aho-corasick/src/test/java/org/viewextract/user.dict"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            line = br.readLine();
            int i = 0;
            while (line != null) {
                line = line.split(" ")[0];

//                int len = line.length();
//                if(len >= 2){
//                    for(String word: end2Words){
//                      if(line.substring(len-2,len).equalsIgnoreCase(word)){
//                            line = line.substring(0,len-2);
//                        }
//                    }
//                    len = line.length();
//                }
//                System.out.println(line);
//                if(len >= 1){
//                    for(String word: end1Words){
//                        if(line.substring(len-1,len).equalsIgnoreCase(word)){
//                            line = line.substring(0,len-1);
//                        }
//                    }
//                    len = line.length();
//                }
//                if(len >= 1) {
                trie.addKeyword(line);
//                }
//                i++;
                line = br.readLine(); // 一次读入一行数据
            }
            br.close();

            for(String car : extraCars){
                trie.addKeyword(car);
            }

            String pathname1 = "/home/jun/sbin/workspace/aho-corasick/src/test/java/org/viewextract/Test.csv"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename1 = new File(pathname1); // 要读取以上路径的input。txt文件
            InputStreamReader reader1 = new InputStreamReader(
                    new FileInputStream(filename1)); // 建立一个输入流对象reader
            BufferedReader br1 = new BufferedReader(reader1); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            line = br1.readLine();
            i = 0;
            while (line != null) {
                String sentence = line.split("\t")[1];
                String lineNum = line.split("\t")[0];
                Collection<Emit> emits = trie.parseText(sentence);
                Map view = new HashMap();
                for(Emit emit: emits){
                    String car = emit.getKeyword();
                    if(!invalidWords.containsKey(car)){
                        view.put(car, lineNum);
                    }
                }
                for(Object obj : view.keySet()){
                    System.out.println(lineNum + " " +obj.toString());
                    i++;
                }
                line = br1.readLine(); // 一次读入一行数据
            }
            System.out.println("total:" + i);


//            /* 写入Txt文件 */
//            File writename = new File(".\\result\\en\\output.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
//            writename.createNewFile(); // 创建新文件
//            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
//            out.write("我会写入文件啦\r\n"); // \r\n即为换行
//            out.flush(); // 把缓存区内容压入文件
//            out.close(); // 最后记得关闭文件

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
